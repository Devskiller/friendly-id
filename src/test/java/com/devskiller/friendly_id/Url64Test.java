package com.devskiller.friendly_id;

import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;

import static io.vavr.test.Property.def;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Objects.areEqual;

public class Url64Test {

	@Test
	public void encodingUuidShouldBeReversible() {
		def("areEqual(Url64.decode(Url64.encode(uuid)), uuid)")
				.forAll(DataProvider.UUIDS)
				.suchThat(uuid -> areEqual(Url64.decode(Url64.encode(uuid)), uuid))
				.check(-1, 1000000)
				.assertIsSatisfied();
	}

	@Test
	@Ignore
	public void decodingIdShouldBeReversible() {
		def("areEqual(Url64.encode(Url64.decode(id)), id)")
				.forAll(DataProvider.FRIENDLY_IDS.filter(id -> id.length() == 22).peek(System.out::println))
				.suchThat(id -> areEqual(Url64.encode(Url64.decode(id)), id))
				.check(24, 100000)
				.assertIsSatisfied();
	}

	@Test
	@Ignore
	public void base64Fails() {
		assertThat(Url64.encode(UUID.fromString("dbb5794b-b8f7-b795-5d25-8036cb4e84bd")))
				.isEqualTo("27V5S7j3t5VdJYA2y06EvQ");
		assertThat(Url64.decode("27V5S7j3t5VdJYA2y06EvX"))
				.isNotEqualByComparingTo(Url64.decode("27V5S7j3t5VdJYA2y06EvQ"));
	}

}