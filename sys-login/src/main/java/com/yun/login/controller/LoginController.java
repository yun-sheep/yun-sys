package com.yun.login.controller;


import com.anji.captcha.service.CaptchaService;
import com.yun.login.service.ISysRoleService;
import com.yun.login.service.ISysUserService;
import com.yun.login.service.OauthService;
import com.yun.project.components.user.UserHolder;
import com.yun.project.constants.TokenParams;
import com.yun.project.dto.login.LoginDTO;
import com.yun.project.dto.login.Oauth2TokenDTO;
import com.yun.project.login.LoginApis;
import com.yun.project.utils.redis.RedisUtils;
import com.yun.project.vo.JsonVO;
import com.yun.project.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static com.yun.project.vo.JsonVO.fail;

/**
 * @Description 实现登录功能接口
 * @auther j2-yizhiyang
 * @date 2023/3/19 20:31
 */
@Slf4j
@RestController
@RequestMapping("login")
@Api(tags = "login")
public class LoginController implements LoginApis {

    @Resource
    private CaptchaService captchaService;

    @Resource
    private UserHolder userHolder;
    @Resource
    ISysUserService userService;
    @Resource
    ISysRoleService roleService;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    OauthService oAuthService;

    /**
     * 实现授权登录
     * 使用的是密码模式
     * */
    @PostMapping("auth-login")
    @Override
    @ApiOperation(value = "授权登录")
    public JsonVO<Oauth2TokenDTO> authLogin(LoginDTO loginDTO) {
        //处理验证码
        /*CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(loginDTO.getCode());
        ResponseModel responseModel = captchaService.verification(captchaVO);
        //验证码验证失败(给前端返回信息）
        if(!responseModel.isSuccess()){
            JsonVO<Oauth2TokenDTO> fail = fail(null);
            fail.setMessage(responseModel.getRepCode() + responseModel.getRepMsg());
            return fail;
        }*/
        if(!loginDTO.getCode().equals("999818")){
            JsonVO<Oauth2TokenDTO> fail = fail(null);
            fail.setMessage("验证码失败");
            return fail;
        }

        //账号密码认证(其实我觉得最好用对象进行传递）
        Map<String,String> params = new HashMap<>(TokenParams.PARAMS_COUNT);
        params.put(TokenParams.GRANT_TYPE,"password");
        params.put(TokenParams.CLIENT_ID,loginDTO.getClientId());
        params.put(TokenParams.CLIENT_SECRET,"123456");
        params.put(TokenParams.USERNAME,loginDTO.getUsername());
        params.put(TokenParams.PASSWORD,loginDTO.getPassword());
        //TODO 3、远程调用Oauth模块来获取token
        //姐做出来了，嘻嘻，开心死谁了
        JsonVO<Oauth2TokenDTO> oauth2Token = oAuthService.postAccessToken(params);
        //TODO 4、将授权的token存储Redis中，记录登录状态
        if(oauth2Token.getData() == null){
            System.out.println("完了");
        }
        else {
            System.out.println(oauth2Token.getData());
        }
        //TODO 5、返回结果token
        /*String userTokenKey = CommonUtils.generateRedisTokenKey(oauth2Token.getData().getToken());
        if (redisUtils.add(userTokenKey, 1, 1L, TimeUnit.HOURS) < 0) {
            return fail(oauth2Token.getData(), ResultStatus.SERVER_ERROR);
        }

        // 5. 返回结果token
        return oauth2Token;*/
        //log.info("LoginDTO",loginDTO);
        /*System.out.println(loginDTO.getClientId()+loginDTO.getUsername());
        System.out.println(params);*/
        //String res = oAuthService.test2(params);
        //System.out.println(res);
        return  null;
    }

    @Override
    public JsonVO<Oauth2TokenDTO> refreshToken(Oauth2TokenDTO oauth2TokenDTO) {
        return null;
    }

    @Override
    public JsonVO<LoginVO> getCurrUser() {
        return null;
    }

    @Override
    public JsonVO<String> logout() {
        return null;
    }

}
