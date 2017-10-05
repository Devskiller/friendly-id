package com.devskiller.friendly_id.jackson;

import java.util.UUID;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class FriendlyIdModule extends SimpleModule {

	private FriendlyIdAnnotationIntrospector introspector;

	public FriendlyIdModule() {
		introspector = new FriendlyIdAnnotationIntrospector();
		addDeserializer(UUID.class, new FriendlyIdDeserializer());
		addSerializer(UUID.class, new FriendlyIdSerializer());
	}

	@Override
	public void setupModule(SetupContext context) {
		context.insertAnnotationIntrospector(introspector);
	}
}
