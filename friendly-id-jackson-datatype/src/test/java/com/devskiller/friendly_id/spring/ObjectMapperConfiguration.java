package com.devskiller.friendly_id.spring;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.devskiller.friendly_id.jackson.FriendlyIdModule;
import com.devskiller.friendly_id.jackson.FriendlyIdAnnotationIntrospector;

public class ObjectMapperConfiguration {

	protected static ObjectMapper mapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new FriendlyIdModule());
		mapper.setAnnotationIntrospector(new FriendlyIdAnnotationIntrospector());
		return mapper;
	}
}
