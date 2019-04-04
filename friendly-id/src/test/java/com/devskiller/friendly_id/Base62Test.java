package com.devskiller.friendly_id;

import org.junit.Test;

import static com.devskiller.friendly_id.IdUtil.areEqualIgnoringLeadingZeros;
import static io.vavr.test.Property.def;
import static org.assertj.core.util.Objects.areEqual;

public class Base62Test {

	@Test
	public void decodingIdShouldBeReversible() {
		def("areEqualIgnoringLeadingZeros(Base62.toFriendlyId(Base62.toUuid(id)), id)")
				.forAll(DataProvider.FRIENDLY_IDS)
				.suchThat(id -> areEqualIgnoringLeadingZeros(Base62.encode(Base62.decode(id)), id))
				.check(24, 100_000)
				.assertIsSatisfied();
	}

	@Test
	public void encodingNumberShouldBeReversible() {
		def("areEqualIgnoringLeadingZeros(Base62.toFriendlyId(Base62.toUuid(id)), id)")
				.forAll(DataProvider.POSITIVE_BIG_INTEGERS)
				.suchThat(bigInteger -> areEqual(Base62.decode(Base62.encode(bigInteger)), bigInteger)
				)
				.check(-1, 100_000)
				.assertIsSatisfied();
	}

}
