package com.devskiller.friendly_id;

import java.util.UUID;

import io.vavr.test.Arbitrary;
import io.vavr.test.Gen;
import org.junit.Test;

import static io.vavr.test.Property.def;

public class Url64Test {

	@Test
	public void resultOfPairingIsPositiveAndCanBeInvertedWithUnpairing() throws Exception {
		Arbitrary<UUID> uuids = ignored -> {
			Gen<Long> longs = Gen.choose(Long.MIN_VALUE, Long.MAX_VALUE);
			return random -> new UUID(longs.apply(random), longs.apply(random));
		};

		def("Url64.decode(Url64.encode(uuid)).equals(uuid)")
				.forAll(uuids)
				.suchThat(uuid -> Url64.decode(Url64.encode(uuid)).equals(uuid))
				.check()
				.assertIsSatisfied();
	}

}