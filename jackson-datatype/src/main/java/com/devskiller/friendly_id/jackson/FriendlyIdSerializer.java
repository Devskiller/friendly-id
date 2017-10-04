package com.devskiller.friendly_id.jackson;

import java.io.IOException;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import com.devskiller.friendly_id.Url62;

public class FriendlyIdSerializer extends StdSerializer<UUID> {

	protected FriendlyIdSerializer(Class<UUID> t) {
		super(t);
	}

	@Override
	public void serialize(UUID uuid, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(Url62.encode(uuid));
	}
}
