package com.devskiller.friendly_id;

import java.math.BigInteger;

import org.junit.Test;

import static com.devskiller.friendly_id.ElegantPairing.pair;
import static com.devskiller.friendly_id.ElegantPairing.unpair;
import static io.vavr.test.Property.def;
import static java.math.BigInteger.valueOf;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


public class ElegantElegantPairingTest {

	@Test
	public void shouldPairTwoLongs() {
		long x = 1;
		long y = 2;

		BigInteger z = pair(valueOf(1), valueOf(2));

		assertThat(unpair(z)).contains(valueOf(x), valueOf(y));
	}

	/**
	 * Basing on information from
	 * https://stackoverflow.com/questions/919612/mapping-two-integers-to-one-in-a-unique-and-deterministic-way/13871379#13871379
	 */
	@Test
	public void shouldPairSampleValues() {

		assertThat(pair(valueOf(-32768), valueOf(-32768))).isEqualTo(4294967295L);
		assertThat(unpair(valueOf(4294967295L))).containsOnly(valueOf(-32768));

		assertThat(pair(valueOf(0), valueOf(0))).isEqualTo(0);
		assertThat(unpair(valueOf(0))).containsOnly(valueOf(0));
	}

	@Test
	public void resultOfPairingIsPositiveAndCanBeInvertedWithUnpairing() throws Exception {
		def("unpair(pair(long,long).contains(long,long)")
				.forAll(DataProvider.LONG_PAIRS)
				.suchThat(pair -> pair(valueOf(pair._1), valueOf(pair._2)).compareTo(BigInteger.ZERO) > 0)
				.implies(pair -> asList(unpair(pair(valueOf(pair._1), valueOf(pair._2))))
						.containsAll(asList(valueOf(pair._1), valueOf(pair._2))))
				.check()
				.assertIsSatisfied();
	}

}