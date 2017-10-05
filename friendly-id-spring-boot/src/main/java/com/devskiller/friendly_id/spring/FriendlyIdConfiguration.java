package com.devskiller.friendly_id.spring;

import java.util.UUID;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.devskiller.friendly_id.Url62;
import com.devskiller.friendly_id.jackson.FriendlyIdModule;
import com.devskiller.friendly_id.jackson.FriendlyIdAnnotationIntrospector;

@Configuration
public class FriendlyIdConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToUuidConverter());
		super.addFormatters(registry);
	}

	@Bean
	public Module parameterNamesModule() {
		return new FriendlyIdModule();
	}

	@Bean
	ObjectMapper objectMapper() {
		return Jackson2ObjectMapperBuilder
				.json()
				.annotationIntrospector(new FriendlyIdAnnotationIntrospector())
				.build();
	}

	private class StringToUuidConverter implements Converter<String, UUID> {

		@Override
		public UUID convert(String id) {
			return Url62.decode(id);
		}
	}
}
