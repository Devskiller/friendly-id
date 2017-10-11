package com.devskiller.friendly_id.sample.hateos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.RelProvider;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class JsonConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public Module parameterNamesModule() {
		return new ParameterNamesModule(JsonCreator.Mode.PROPERTIES);
	}

	// This is declared as part of WebMVC slice, used in testing
	@Bean
	public FooResourceAssembler fooResourceAssembler() {
		return new FooResourceAssembler();
	}

	// This is declared as part of WebMVC slice, used in testing
	@Bean
	public BarResourceAssembler barResourceAssembler() {
		return new BarResourceAssembler();
	}

}
