package com.yun.sysoauth2.config;

import com.yun.project.constants.AuthConstant;
import com.yun.sysoauth2.entity.SecurityUser;
import com.yun.sysoauth2.service.impl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description JWT Token增强实现，将用户ID记录到Token里面
 * @auther j2-yizhiyang
 * @date 2023/3/21 20:22
 */

@Component
class JwtTokenEnhancer implements TokenEnhancer {
    //给token增加内容的
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Map<String,Object> info = new HashMap<>(1);
        //TODO 把用户ID设置到JWT中
        info.put("id", securityUser.getUser().getId());
        //给的参数是oAuth2的AccessToken，实现类是DefaultOAuth2AccessToken，
        //里面有个setAdditionalInformation方法添加自定义信息（Map类型）
        DefaultOAuth2AccessToken result = (DefaultOAuth2AccessToken) accessToken;
        result.setAdditionalInformation(info);
        return result;
    }
}
/**
 * 授权服务配置(这个单体应用好像写过)
 * */
@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenEnhancer jwtTokenEnhancer;
    /**
     *针对于第三方客户端配置（用来指定给哪一些进行授权，然后是怎么授权的）
     *指定哪些应用可以访问授权服务并颁发令牌
     * 访问模式是什么
     * */
    /*传来的参数clients是我们的应用，要去找授权服务器授权，授权完了之后会给我们授权码，我们
     * （client）拿着授权码再到授权服务器去获取令牌，获取到令牌之后拿着令牌去资源服务器获取资源*/
    public void config(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.inMemory()
                //配置客户端ID（管理端）
                .withClient(AuthConstant.CLIENT_MANAGER)
                //配置访问密码
                .secret(passwordEncoder.encode(AuthConstant.CLIENT_PASSWORD))
                //配置授权范围，客户端传递scope必须为下面的值或者不携带scope
                .scopes("all")
                //配置支持的授权模式，支持用户名密码认证和凭证刷新
                .authorizedGrantTypes("password", "refresh_token")
                //配置颁发令牌的有效时间，这里修改成了24小时
                .accessTokenValiditySeconds(3600 * 24)
                //配置颁发刷新令牌的有效时间，这里修改成了1周
                .refreshTokenValiditySeconds(3600 * 24 * 7)
                .and()
                //配置客户端ID（用户端，如app）
                .withClient(AuthConstant.CLIENT_APP)
                .secret(passwordEncoder.encode(AuthConstant.CLIENT_PASSWORD))
                .scopes("all")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(3600 * 24)
                .refreshTokenValiditySeconds(3600 * 24 * 7);
    }
    //这个是用来干什么的
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //定义JWT内置增强内容
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(accessTokenConverter());
        //配置JWT的内容增强器
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(delegates);
        //授权服务端点访问配置
        endpoints
                //配置授权管理器
                .authenticationManager(authenticationManager)
                //配置加载用户信息的服务
                .userDetailsService(userDetailsService)
                //设置凭证签名转换器
                .accessTokenConverter(accessTokenConverter())
                //设置凭证增强附加额外信息到凭证中
                .tokenEnhancer(enhancerChain);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // 访问安全性配置
        // 在BasicAuthenticationFilter之前添加ClientCredentialsTokenEndpointFilter,
        // 使用ClientDetailsUserDetailsService来进行登陆认证
        security.allowFormAuthenticationForClients();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        //使用非对称加密算法来对Token进行签名
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    @Bean
    public KeyPair keyPair() {
        //从classpath下的证书中获取秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
        return keyStoreKeyFactory.getKeyPair("01star", "123456".toCharArray());
    }




}
