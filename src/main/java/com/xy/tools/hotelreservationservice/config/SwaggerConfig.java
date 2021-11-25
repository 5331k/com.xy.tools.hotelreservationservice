package com.xy.tools.hotelreservationservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Value("${application.description}")
	private String applicationDescription;
	@Value("${application.version}")
	private String applicationVersion;
	@Value("${application.title}")
	private String title;

	public static final Contact DEFAULT_CONTACT = new Contact().name("Safdar Dabeer Khan")
			.email("safdar.dabeer@gmail.com");

	@Bean
	public OpenAPI customOpenApi() {

		Info defaultInfo = new Info().contact(DEFAULT_CONTACT).description(applicationDescription).title(title)
				.version(applicationVersion);

		OpenAPI openAPI = new OpenAPI();
		openAPI.info(defaultInfo);

		return openAPI;
	}
}
