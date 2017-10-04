package com.devskiller.friendly_id.jackson;

import java.util.UUID;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class FriendlyIdModule extends SimpleModule {

	public FriendlyIdModule() {
		addDeserializer(UUID.class, new FriendlyIdDeserializer(UUID.class));
		addSerializer(UUID.class, new FriendlyIdSerializer(UUID.class));
	}
}
