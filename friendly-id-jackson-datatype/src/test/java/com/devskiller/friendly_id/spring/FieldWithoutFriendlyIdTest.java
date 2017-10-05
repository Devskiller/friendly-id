package com.devskiller.friendly_id.spring;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import com.devskiller.friendly_id.jackson.FriendlyIdFormat;
import com.devskiller.friendly_id.jackson.IdFormat;

import static com.devskiller.friendly_id.spring.ObjectMapperConfiguration.mapper;
import static org.assertj.core.api.Assertions.assertThat;

public class FieldWithoutFriendlyIdTest {

	@Test
	public void shouldAllowToDoNotCodeUuid() throws Exception {
		ObjectMapper mapper = mapper();

		UUID uuid = UUID.fromString("f088ce5b-9279-4cc3-946a-c15ad740dd6d");
		Foo foo = new Foo();
		foo.visibleUuid = uuid;
		foo.friendlyId = uuid;
		String json = mapper.writeValueAsString(foo);

		assertThat(json)
				.isEqualToIgnoringWhitespace("{\"visibleUuid\":\"f088ce5b-9279-4cc3-946a-c15ad740dd6d\",\"friendlyId\":\"5VFVlOFz9lugV5ypWqxdXG\"}");

		Foo cloned = mapper.readValue(json, Foo.class);
		assertThat(cloned.getVisibleUuid()).isEqualTo(foo.getFriendlyId());
	}

	public static class Foo {

		@IdFormat(FriendlyIdFormat.RAW)
		private UUID visibleUuid;

		private UUID friendlyId;

		public UUID getVisibleUuid() {
			return visibleUuid;
		}

		public void setVisibleUuid(UUID visibleUuid) {
			this.visibleUuid = visibleUuid;
		}

		public UUID getFriendlyId() {
			return friendlyId;
		}

		public void setFriendlyId(UUID friendlyId) {
			this.friendlyId = friendlyId;
		}
	}

}
