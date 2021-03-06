package net.uniquewholesale.unique.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	
	public static final Contact CONTACT = new Contact("YLICEA INC", "https://www.ylicea.com/", "ylicea@gmail.com");
	//public static final Ap HOST = new Contact("YLICEA INC", "https://www.ylicea.com/", "ylicea@gmail.com");
    public static final ApiInfo APIINFO = new ApiInfo("API Testing Sprin-boot Documentation", "Swagger documentation of the YLICEA INC", "1.1 version", "Term of Serv",
           CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.10", new ArrayList<>());

    @Bean
	public Docket api() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(APIINFO)
				.select()
				.apis(RequestHandlerSelectors.basePackage("net.uniquewholesale.unique.rest"))
				.paths(PathSelectors.any())
				.build()
				;
		//docket.apiInfo(APIINFO);
		return docket;
	}

}
