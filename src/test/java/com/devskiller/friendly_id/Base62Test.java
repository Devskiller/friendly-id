package com.devskiller.friendly_id;

import org.junit.Test;

import static io.vavr.test.Property.def;

public class Base62Test {

	@Test
	public void shouldDecodeEncodedCode() throws Exception {
		def("Base62.encode(Base62.decode(code)).equals(removeLeadingZeros(code))")
				.forAll(DataProvider.CODES)
				.suchThat(code -> Base62.encode(Base62.decode(code)).equals(DataProvider.removeLeadingZeros(code)))
				.check(24, 100000)
				.assertIsSatisfied();
	}


}
