package com.devskiller.friendly_id;

import java.util.UUID;

import io.vavr.Tuple;
import io.vavr.test.Arbitrary;
import io.vavr.test.Gen;
import io.vavr.test.Property;
import org.junit.Test;

import static io.vavr.test.Property.def;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Url62Test {


	@Test
	public void decodedCodeShouldBeEncodedToTheSameCode() throws Exception {
		def("Url62.encode(Url62.decode(code)).equals(DataProvider.removeLeadingZeros(code))")
				.forAll(DataProvider.CODES)
				.suchThat(code -> Url62.encode(Url62.decode(code)).equals(DataProvider.removeLeadingZeros(code)))
				.check(100, 100000)
				.assertIsSatisfied();
	}

	@Test
	public void encodedUUIDShouldBeDecodedToTheSameUUID() {
		def("Url62.decode(Url62.encode(uuid)).equals(uuid)")
				.forAll(DataProvider.UUIDS)
				.suchThat(uuid -> Url62.decode(Url62.encode(uuid)).equals(uuid))
				.check(-1, 1000000)
				.assertIsSatisfied();
	}

	@Test
	public void shouldExplodeWhenContainsIllegalCharacters() throws Exception {
		assertThatThrownBy(() -> Url62.decode("Foo Bar"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("contains illegal characters");
	}

	@Test
	public void shouldFaildOnEmpyString() throws Exception {
		assertThatThrownBy(() -> Url62.decode(""))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("must not be empty");
	}

	@Test
	public void shouldFaildOnNullString() throws Exception {
		assertThatThrownBy(() -> Url62.decode(null))
				.isInstanceOf(NullPointerException.class)
				.hasMessageContaining("must not be null");
	}

	@Test
	public void shouldFaildWhenStringContainsMoreThan128bitInformation() throws Exception {
		assertThatThrownBy(() -> Url62.decode("7NLCAyd6sKR7kDHxgAWFPas"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("more than 128bit information");
	}


}