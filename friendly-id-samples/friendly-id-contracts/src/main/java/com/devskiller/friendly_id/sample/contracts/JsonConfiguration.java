package com.devskiller.friendly_id.sample.contracts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JsonConfiguration implements WebMvcConfigurer {

	// This is declared as part of WebMVC slice, used in testing
	@Bean
	public FooResourceAssembler fooResourceAssembler() {
		return new FooResourceAssembler();
	}

	// This is declared as part of WebMVC slice, used in testing
	@Bean
	public BarResourceAssembler barResourceAssembler(LinkRelationProvider relProvider) {
		return new BarResourceAssembler(relProvider);
	}
}
