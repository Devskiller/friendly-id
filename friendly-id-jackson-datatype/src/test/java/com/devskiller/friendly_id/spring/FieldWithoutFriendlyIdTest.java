package com.devskiller.friendly_id.spring;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.Test;

import com.devskiller.friendly_id.jackson.FriendlyIdFormat;
import com.devskiller.friendly_id.jackson.IdFormat;

import static com.devskiller.friendly_id.spring.ObjectMapperConfiguration.mapper;
import static org.assertj.core.api.Assertions.assertThat;

public class FieldWithoutFriendlyIdTest {

	private UUID uuid = UUID.fromString("f088ce5b-9279-4cc3-946a-c15ad740dd6d");
	private ObjectMapper mapper = mapper();

	@Test
	public void shouldAllowToDoNotCodeUuidInDataObject() throws Exception {
		Foo foo = new Foo();
		foo.rawUuid = uuid;
		foo.friendlyId = uuid;

		String json = mapper.writeValueAsString(foo);

		assertThat(json).isEqualToIgnoringWhitespace(
				"{\"rawUuid\":\"f088ce5b-9279-4cc3-946a-c15ad740dd6d\",\"friendlyId\":\"5VFVlOFz9lugV5ypWqxdXG\"}"
		);

		Foo cloned = mapper.readValue(json, Foo.class);
		assertThat(cloned.getRawUuid()).isEqualTo(foo.getFriendlyId());
	}

	@Test
	public void shouldDeserializeUuidsInDataObject() throws Exception {
		String json = "{\"rawUuid\":\"f088ce5b-9279-4cc3-946a-c15ad740dd6d\",\"friendlyId\":\"5VFVlOFz9lugV5ypWqxdXG\"}";

		Foo cloned = mapper.readValue(json, Foo.class);
		assertThat(cloned.getRawUuid()).isEqualTo(uuid);
		assertThat(cloned.getFriendlyId()).isEqualTo(uuid);
	}

	@Test
	public void shouldSerializeUuidsInValueObject() throws Exception {
		mapper = mapper(new ParameterNamesModule());

		Bar bar = new Bar(uuid, uuid);

		String json = mapper.writeValueAsString(bar);

		assertThat(json).isEqualToIgnoringWhitespace(
				"{\"rawUuid\":\"f088ce5b-9279-4cc3-946a-c15ad740dd6d\",\"friendlyId\":\"5VFVlOFz9lugV5ypWqxdXG\"}"
		);
	}

	@Test
	public void shouldDeserializeUuuidsValueObject() throws Exception {
		mapper = mapper(new ParameterNamesModule());

		String json = "{\"rawUuid\":\"f088ce5b-9279-4cc3-946a-c15ad740dd6d\",\"friendlyId\":\"5VFVlOFz9lugV5ypWqxdXG\"}";

		Bar deserialized = mapper.readValue(json, Bar.class);

		assertThat(deserialized.getRawUuid()).isEqualTo(uuid);
		assertThat(deserialized.getFriendlyId()).isEqualTo(uuid);
	}

	public static class Foo {

		@IdFormat(FriendlyIdFormat.RAW)
		private UUID rawUuid;

		private UUID friendlyId;

		public UUID getRawUuid() {
			return rawUuid;
		}

		public void setRawUuid(UUID rawUuid) {
			this.rawUuid = rawUuid;
		}

		public UUID getFriendlyId() {
			return friendlyId;
		}

		public void setFriendlyId(UUID friendlyId) {
			this.friendlyId = friendlyId;
		}
	}

	public static class Bar {

		@IdFormat(FriendlyIdFormat.RAW)
		private final UUID rawUuid;

		private final UUID friendlyId;

		public Bar(UUID rawUuid, UUID friendlyId) {
			this.rawUuid = rawUuid;
			this.friendlyId = friendlyId;
		}

		public UUID getRawUuid() {
			return rawUuid;
		}

		public UUID getFriendlyId() {
			return friendlyId;
		}


	}

}
