package com.example;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration 
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	@Bean
	public Docket api() {                
//	    return new Docket(DocumentationType.SWAGGER_2)          
   	    return new Docket(DocumentationType.OAS_30)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.example"))
	      .paths(PathSelectors.ant("/**"))
	      .build()
	      .apiInfo(new ApiInfoBuilder()
	                .title("Microservicio: Demos del curso")
	                .description("Ejemplo de Microservicio utilizando la base de datos Sakila.")
	                .version("1.0")
	                .license("Apache License Version 2.0")
	                .contact(new Contact("Yo Mismo", "http://www.example.com", "myeaddress@example.com"))
	                .build());
	}
	
	@Bean 
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
            .addResourceLocations("/public", "classpath:/static/")
            .setCacheControl(CacheControl.maxAge(Duration.ofDays(365)));
    }
	
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//            .allowedOrigins("*")
//            .allowedMethods("GET", "POST", "PUT", "DELETE")
//            .allowedHeaders("origin", "content-type", "accept", "authorization")
//            .maxAge(3600);
//    }

}
