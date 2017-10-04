package com.devskiller.friendly_id;

import java.math.BigInteger;
import java.util.Arrays;

import io.vavr.Tuple2;
import org.junit.Test;

import static com.devskiller.friendly_id.ElegantPairing.unpair;
import static io.vavr.test.Property.def;
import static java.math.BigInteger.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class ElegantPairingTest {

	@Test
	public void shouldPairTwoLongs() {
		long x = 1;
		long y = 2;

		BigInteger z = ElegantPairing.pair(valueOf(1), valueOf(2));

		assertThat(unpair(z)).contains(valueOf(x), valueOf(y));
	}

	/**
	 * Basing on information from
	 * https://stackoverflow.com/questions/919612/mapping-two-integers-to-one-in-a-unique-and-deterministic-way/13871379#13871379
	 */
	@Test
	public void shouldPairSampleValues() {

		assertThat(ElegantPairing.pair(valueOf(-32768), valueOf(-32768))).isEqualTo(4294967295L);
		assertThat(unpair(valueOf(4294967295L))).containsOnly(valueOf(-32768));

		assertThat(ElegantPairing.pair(valueOf(0), valueOf(0))).isEqualTo(0);
		assertThat(unpair(valueOf(0))).containsOnly(valueOf(0));
	}

	@Test
	public void resultOfPairingShouldBePositive() {
		def("pair(longs).signum() > 0")
				.forAll(DataProvider.LONG_PAIRS)
				.suchThat(longs -> pair(longs).signum() > 0)
				.check()
				.assertIsSatisfied();
	}

	@Test
	public void pairingLongsShouldBeReversible() {
		def("Arrays.equals(unpair(pair(longs)), asArray(longs))")
				.forAll(DataProvider.LONG_PAIRS)
				.suchThat(longs -> Arrays.equals(unpair(pair(longs)), asArray(longs)))
				.check()
				.assertIsSatisfied();
	}

	private BigInteger pair(Tuple2<Long, Long> longs) {
		return longs.apply((x, y) -> ElegantPairing.pair(valueOf(x), valueOf(y)));
	}

	private BigInteger[] asArray(Tuple2<Long, Long> longsPair) {
		return longsPair.apply((x, y) -> new BigInteger[]{valueOf(x), valueOf(y)});
	}

}