package com.uchain.drugtracesystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket createRestApi() {
        //添加head参数start
        ParameterBuilder token = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        token.name("Authorization").description("返回的token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        //根据每个方法名也知道当前方法在设置什么参数
        pars.add(token.build());

        //添加head参数end
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //指定接口的位置
                .apis(RequestHandlerSelectors.basePackage("com.uchain.drugtracesystem.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SWPU-UChain 药品溯源系统后台接口文档")
                .description("前端根据接口进行测试")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}