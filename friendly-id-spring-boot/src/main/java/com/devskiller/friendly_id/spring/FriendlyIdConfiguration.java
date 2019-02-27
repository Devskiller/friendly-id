package com.devskiller.friendly_id.spring;

import java.util.UUID;

import com.fasterxml.jackson.databind.Module;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.devskiller.friendly_id.FriendlyId;
import com.devskiller.friendly_id.jackson.FriendlyIdModule;

@Configuration
public class FriendlyIdConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToUuidConverter());
		registry.addConverter(new UuidToStringConverter());
		super.addFormatters(registry);
	}

	@Bean
	public Module friendlyIdModule() {
		return new FriendlyIdModule();
	}

	//FIXME: make this public
	public static class StringToUuidConverter implements Converter<String, UUID> {

		@Override
		public UUID convert(String id) {
			return FriendlyId.toUuid(id);
		}
	}


	public static class UuidToStringConverter implements Converter<UUID, String> {

		@Override
		public String convert(UUID id) {
			return FriendlyId.toFriendlyId(id);
		}
	}
}
