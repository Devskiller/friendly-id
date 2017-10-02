package com.devskiller.friendly_id;

import java.math.BigInteger;
import java.math.RoundingMode;

import com.google.common.math.BigIntegerMath;

/**
 * https://stackoverflow.com/questions/919612/mapping-two-integers-to-one-in-a-unique-and-deterministic-way/13871379#13871379
 */
class ElegantPairing {

	private static final BigInteger TWO = new BigInteger("2");

	static BigInteger pair(BigInteger first, BigInteger second) {
		BigInteger a = first.compareTo(BigInteger.ZERO) >= 0 ? TWO.multiply(first) :
				TWO.negate().multiply(first).subtract(BigInteger.ONE);
		BigInteger b = second.compareTo(BigInteger.ZERO) >= 0 ? TWO.multiply(second) :
				TWO.negate().multiply(second).subtract(BigInteger.ONE);
		if (a.compareTo(b) >= 0) {
			return a.multiply(a).add(a).add(b);
		} else {
			return b.multiply(b).add(a);
		}
	}

	static BigInteger[] unpair(BigInteger value) {
		BigInteger x = BigIntegerMath.sqrt(value, RoundingMode.FLOOR);
		BigInteger y = value.subtract(x.multiply(x));
		return x.compareTo(y) > 0 ?
				new BigInteger[]{recoverSignedValue(y), recoverSignedValue(x)} :
				new BigInteger[]{recoverSignedValue(x), recoverSignedValue(y.subtract(x))};
	}

	private static BigInteger recoverSignedValue(BigInteger value) {
		return value.testBit(0) ? value.divide(TWO).negate().subtract(BigInteger.ONE) : value.divide(TWO);
	}

}
