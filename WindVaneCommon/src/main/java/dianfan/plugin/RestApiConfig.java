/**  
* @Title: RestApiConfig.java
* @Package dianfan.plugin
* @Description: TODO
* @author Administrator
* @date 2018年9月4日 下午4:54:49
* @version V1.0  
*/
package dianfan.plugin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Title: RestApiConfig.java
 * @Package dianfan.plugin
 * @Description: TODO
 * @author Administrator
 * @date 2018年9月4日 下午4:54:49
 * @version V1.0
 */
/*
 * Restful API 访问路径: http://IP:port/{context-path}/swagger-ui.html
 * eg:http://localhost:8080/vrworldapi/api/swagger-ui.html
 */

@EnableWebMvc
@EnableSwagger2
@Configuration
public class RestApiConfig extends WebMvcConfigurerAdapter {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()// 选择哪些路径和API会生成document
				.apis(RequestHandlerSelectors.any())// 对所有api进行监控
				.paths(PathSelectors.any())// 对所有路径进行监控
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("windvane").version("2.0").build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
