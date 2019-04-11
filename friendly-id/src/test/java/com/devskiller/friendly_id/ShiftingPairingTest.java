package com.devskiller.friendly_id;

import java.math.BigInteger;
import java.util.Arrays;

import io.vavr.Tuple2;
import org.junit.Test;

import static io.vavr.test.Property.def;
import static java.math.BigInteger.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class ShiftingPairingTest {

	BigIntegerPairing pairing = new ShiftingPairing();

	@Test
	public void shouldPairTwoLongs() {
		long x = 1;
		long y = 2;

		BigInteger z = pairing.pair(valueOf(1), valueOf(2));

		assertThat(pairing.unpair(z)).contains(valueOf(x), valueOf(y));
	}

	@Test
	public void resultOfPairingShouldBePositive() {
		def("pair(longs).signum() > 0")
				.forAll(DataProvider.LONG_PAIRS)
				.suchThat(longs -> makePair(longs).signum() > 0)
				.check(-1, 100_000)
				.assertIsSatisfied();
	}

	private BigInteger makePair(Tuple2<Long, Long> longs) {
		return longs.apply((x, y) -> pairing.pair(valueOf(x), valueOf(y)));
	}

	@Test
	public void pairingLongsShouldBeReversible() {
		def("Arrays.equals(unpair(pair(longs)), asArray(longs))")
				.forAll(DataProvider.LONG_PAIRS)
				.suchThat(longs -> Arrays.equals(pairing.unpair(makePair(longs)), asArray(longs)))
				.check(-1, 100_000)
				.assertIsSatisfied();
	}

	private BigInteger[] asArray(Tuple2<Long, Long> longsPair) {
		return longsPair.apply((x, y) -> new BigInteger[]{valueOf(x), valueOf(y)});
	}

}