package com.devskiller.friendly_id.spring;

import java.util.UUID;

import org.junit.Test;

import com.devskiller.friendly_id.Url62;

import static org.assertj.core.api.Assertions.assertThat;

public class FriendlyIdDeserializerTest extends ModuleTestBase {

	@Test
	public void shouldSerializeFriendlyId() throws Exception {
		UUID uuid = UUID.randomUUID();
		String json = mapper().writeValueAsString(uuid);
		System.out.println(json);
		assertThat(json).contains(Url62.encode(uuid));
	}

	@Test
	public void shouldDeserializeFriendlyId() throws Exception {
		String friendlyId = "2YSfgVHnEYbYgfFKhEX3Sz";
		UUID uuid = mapper().readValue("\"" + friendlyId + "\"", UUID.class);
		assertThat(uuid).isEqualByComparingTo(Url62.decode(friendlyId));
	}
}
