package com.devskiller.friendly_id;

import org.junit.Test;

import static com.devskiller.friendly_id.IdUtil.areEqualIgnoringLeadingZeros;
import static io.vavr.test.Property.def;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.util.Objects.areEqual;

public class Url62Test {

	@Test
	public void encodingUuidShouldBeReversible() {
		def("areEqual(Url62.decode(Url62.encode(uuid)), uuid)")
				.forAll(DataProvider.UUIDS)
				.suchThat(uuid -> areEqual(Url62.decode(Url62.encode(uuid)), uuid))
				.check(-1, 1000000)
				.assertIsSatisfied();
	}

	@Test
	public void decodingIdShouldBeReversible() {
		def("areEqualIgnoringLeadingZeros(Url62.encode(Url62.decode(id)), id)")
				.forAll(DataProvider.FRIENDLY_IDS)
				.suchThat(id -> areEqualIgnoringLeadingZeros(Url62.encode(Url62.decode(id)), id))
				.check(100, 100000)
				.assertIsSatisfied();
	}

	@Test
	public void shouldExplodeWhenContainsIllegalCharacters() {
		assertThatThrownBy(() -> Url62.decode("Foo Bar"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("contains illegal characters");
	}

	@Test
	public void shouldFaildOnEmpyString() {
		assertThatThrownBy(() -> Url62.decode(""))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("must not be empty");
	}

	@Test
	public void shouldFaildOnNullString() {
		assertThatThrownBy(() -> Url62.decode(null))
				.isInstanceOf(NullPointerException.class)
				.hasMessageContaining("must not be null");
	}

	@Test
	public void shouldFaildWhenStringContainsMoreThan128bitInformation() {
		assertThatThrownBy(() -> Url62.decode("7NLCAyd6sKR7kDHxgAWFPas"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("more than 128bit information");
	}

}