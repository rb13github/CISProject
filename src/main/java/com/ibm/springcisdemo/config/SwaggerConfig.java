/**
 * 
 */
package com.ibm.springcisdemo.config;

/**
 * @author RITESHBHONDWE
 *
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.ibm.springcisdemo.api"))
               // .paths(regex("/cis/customer.*"))
                //.paths( PathSelectors.any())
               // .paths(PathSelectors.ant("/cis/customer/*"))
                //.paths(PathSelectors.regex("/cis/customer.*"))
                .paths(PathSelectors.regex("/customer.*"))
                .build()
                .apiInfo(metaData());
    }
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("CIS- Customer API")
                .description("\"REST API for CIS(Customer Information System Application\"")
                .version("1.0.0")
                .license("IBM")
                .licenseUrl("https://ibm.com")
                .contact(new Contact("Ritesh Bhondwe", "https://ibm.com", "rbhondwe@in.ibm.com"))
                .build();
    }
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
}
