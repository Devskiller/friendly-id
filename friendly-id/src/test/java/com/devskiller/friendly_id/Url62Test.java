package com.devskiller.friendly_id;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Url62Test {

	Url62 url62 = new Url62(new UuidConverter(new ShiftingPairing()));

	@Test
	public void shouldExplodeWhenContainsIllegalCharacters() {
		assertThatThrownBy(() -> url62.decode("Foo Bar"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("contains illegal characters");
	}

	@Test
	public void shouldFaildOnEmptyString() {
		assertThatThrownBy(() -> url62.decode(""))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("must not be empty");
	}

	@Test
	public void shouldFailsOnNullString() {
		assertThatThrownBy(() -> url62.decode(null))
				.isInstanceOf(NullPointerException.class)
				.hasMessageContaining("must not be null");
	}

	@Test
	public void shouldFailsWhenStringContainsMoreThan128bitInformation() {
		assertThatThrownBy(() -> url62.decode("7NLCAyd6sKR7kDHxgAWFPas"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("more than 128bit information");
	}
}