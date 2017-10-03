package com.devskiller.friendly_id;

import java.util.UUID;

import io.vavr.test.Arbitrary;
import io.vavr.test.Gen;
import org.junit.Test;

import static io.vavr.test.Property.def;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Url62Test {

	Arbitrary<UUID> uuids = ignored -> {
		Gen<Long> longs = Gen.choose(Long.MIN_VALUE, Long.MAX_VALUE);
		return random -> new UUID(longs.apply(random), longs.apply(random));
	};

	@Test
	public void resultOfPairingIsPositiveAndCanBeInvertedWithUnpairing() {
		def("Url62.decode(Url62.encode(uuid)).equals(uuid)")
				.forAll(uuids)
				.suchThat(uuid -> Url62.decode(Url62.encode(uuid)).equals(uuid))
				.check()
				.assertIsSatisfied();
	}

	@Test
	public void resultOfPairingIsPositiveAndCanBeInvertedWithUnpairing2() {
		def("Url62.decode(Url62.encode(uuid)).equals(uuid)")
				.forAll(uuids)
				.suchThat(uuid -> Url62.encode(uuid).length() <= 22)
				.check()
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
}