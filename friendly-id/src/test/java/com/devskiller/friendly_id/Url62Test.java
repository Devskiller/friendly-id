package com.devskiller.friendly_id;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Url62Test {

	@Test
	public void shouldExplodeWhenContainsIllegalCharacters() {
		assertThatThrownBy(() -> Url62.decode("Foo Bar"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("contains illegal characters");
	}

	@Test
	public void shouldFaildOnEmptyString() {
		assertThatThrownBy(() -> Url62.decode(""))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("must not be empty");
	}

	@Test
	public void shouldFailsOnNullString() {
		assertThatThrownBy(() -> Url62.decode(null))
				.isInstanceOf(NullPointerException.class)
				.hasMessageContaining("must not be null");
	}

	@Test
	public void shouldFailsWhenStringContainsMoreThan128bitInformation() {
		assertThatThrownBy(() -> Url62.decode("7NLCAyd6sKR7kDHxgAWFPas"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("more than 128bit information");
	}
}