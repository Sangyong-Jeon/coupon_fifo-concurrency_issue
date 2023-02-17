package com.hexagonal.coupon.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
class SwaggerConfig {

    @Bean
    Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hexagonal.coupon.adapter.in.web"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("선착순쿠폰발급 API 명세서")
                .description("헥사고날 아키텍처 기반 선착순쿠폰발급 API 명세서")
                .version("1.0")
                .contact(new Contact("myomyo", "", "wjstkddyd420@gmail.com"))
                .build();
    }
}
