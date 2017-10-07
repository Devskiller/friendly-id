package com.devskiller.friendly_id.sample;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@Configuration
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class JsonConfiguration {

	@Bean
	public Module parameterNamesModule() {
		return new ParameterNamesModule(JsonCreator.Mode.PROPERTIES);
	}

}
