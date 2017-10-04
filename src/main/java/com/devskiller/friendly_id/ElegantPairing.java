package com.devskiller.friendly_id;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;

/**
 * https://stackoverflow.com/questions/919612/mapping-two-integers-to-one-in-a-unique-and-deterministic-way/13871379#13871379
 */
class ElegantPairing {

	private static final BigInteger TWO = new BigInteger("2");

	static BigInteger pair(BigInteger first, BigInteger second) {
		BigInteger a = first.signum() >= 0 ? TWO.multiply(first) : TWO.negate().multiply(first).subtract(ONE);
		BigInteger b = second.signum() >= 0 ? TWO.multiply(second) : TWO.negate().multiply(second).subtract(ONE);
		if (a.compareTo(b) >= 0) {
			return a.multiply(a).add(a).add(b);
		} else {
			return b.multiply(b).add(a);
		}
	}

	static BigInteger[] unpair(BigInteger value) {
		BigInteger x = sqrt(value);
		BigInteger y = value.subtract(x.multiply(x));
		return x.compareTo(y) > 0 ?
				new BigInteger[]{recoverSignedValue(y), recoverSignedValue(x)} :
				new BigInteger[]{recoverSignedValue(x), recoverSignedValue(y.subtract(x))};
	}

	private static BigInteger recoverSignedValue(BigInteger value) {
		return value.testBit(0) ? value.divide(TWO).negate().subtract(ONE) : value.divide(TWO);
	}

	/**
	 * Source: https://stackoverflow.com/a/36187890/516167
	 */
	private static BigInteger sqrt(BigInteger n) {
		BigInteger a = BigInteger.ONE;
		BigInteger b = n.shiftRight(1).add(TWO); // (n >> 1) + 2 (ensure 0 doesn't show up)
		while (b.compareTo(a) >= 0) {
			BigInteger mid = a.add(b).shiftRight(1); // (a+b) >> 1
			if (mid.multiply(mid).compareTo(n) > 0)
				b = mid.subtract(BigInteger.ONE);
			else
				a = mid.add(BigInteger.ONE);
		}
		return a.subtract(BigInteger.ONE);
	}

}
