package kr.co.iei;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Value(value = "${file.root}")
	private String root;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/", "classpath:/static/");

		registry.addResourceHandler("/photo/image/question/**").addResourceLocations("file:///" + root + "/question/");

		registry.addResourceHandler("/photo/image/review_party/**")
				.addResourceLocations("file:///" + root + "/review_party/");

		registry.addResourceHandler("/photo/image/review_blind/**")
				.addResourceLocations("file:///" + root + "/review_blind/");

		registry.addResourceHandler("/question/editor/**")
				.addResourceLocations("file:///" + root + "/question/editor/");

		registry.addResourceHandler("/review/editor/review_party/**")
				.addResourceLocations("file:///" + root + "/review_party/editor/");

		registry.addResourceHandler("/review/editor/review_blind/**")
				.addResourceLocations("file:///" + root + "/review_blind/editor/");

		registry.addResourceHandler("/photo/editor/question/**")
				.addResourceLocations("file:///" + root + "/question/editor/");

		registry.addResourceHandler("/photo/editor/review/**")
				.addResourceLocations("file:///" + root + "/review/editor/");

		registry.addResourceHandler("/partyImg/**").addResourceLocations("file:///C:/Temp/upload/party/");

		registry.addResourceHandler("/member/memberImg/**").addResourceLocations("file:///" + root + "/selfPhoto/");
	}
	
	
}
