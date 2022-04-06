package br.com.luis.apifilmes.config;

import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("br.com.luis.apifilmes.controllers"))
					.paths(PathSelectors.any())
					.build()
					.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
					.title("Movies API")
					.description("API para os filmes que eu vi desde 2021")
					.version("1.0")
					.contact(contact())
					.build();
	}
	
	private Contact contact() {
		return new Contact("Luis Miguel", "https://github.com/luismiguwl/api-filmes-spring-boot", "luisbwrb@gmail.com");
	}
}
