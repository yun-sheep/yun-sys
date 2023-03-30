package com.yun.project.config.swagger;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * @Description Swagger工具包
 * @auther j2-yizhiyang
 * @date 2023/3/27 13:41
 */
public class SwaggerCore {
    /**
     * 默认Docket构建器
     *
     * @param projectName    项目名称
     * @param apiBasePackage API扫描基础包
     * @param groupName      API分组名称
     * @return Docket对象
     */
    public static Docket defaultDocketBuilder(String projectName, String apiBasePackage, String groupName) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(apiBasePackage))
                .paths(PathSelectors.any())
                .build()
                .groupName(groupName)
                .apiInfo(createApiInfo(projectName))
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    /**
     * 构建文档说明对象
     *
     * @param projectName 项目名称
     * @return ApiInfo对象
     */
    private static ApiInfo createApiInfo(String projectName) {
        return new ApiInfoBuilder()
                .title(projectName + " API接口服务")
                .description("用于" + projectName + "前后端交互，提供一套API说明")
                .version("1.0.0")
                .license("Apache 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }

    /**
     * 构建权限协议列表
     *
     * @return 认证协议列表
     */
    private static List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(
                new ApiKey("Authorization", "Authorization", "header"));
    }

    /**
     * 构建权限上下文列表
     *
     * @return 认证上下文列表
     */
    private static List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .build()
        );
    }

    /**
     * 构建默认认证作用域列表
     *
     * @return 认证作用域列表
     */
    private static List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("Authorization", authorizationScopes));
    }
}
