package com.devskiller.friendly_id;

import org.junit.Test;

import static io.vavr.test.Property.def;

public class Url64Test {

	@Test
	public void resultOfPairingIsPositiveAndCanBeInvertedWithUnpairing() {
		def("Url64.decode(Url64.encode(uuid)).equals(uuid)")
				.forAll(DataProvider.UUIDS)
				.suchThat(uuid -> Url64.decode(Url64.encode(uuid)).equals(uuid))
				.check()
				.assertIsSatisfied();
	}

}