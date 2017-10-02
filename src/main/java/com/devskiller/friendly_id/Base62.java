package com.devskiller.friendly_id;

import java.math.BigInteger;

/**
 * Base62 encoder/decoder.
 * <p>
 * This is free and unencumbered public domain software
 *
 * @see https://github.com/opencoinage/opencoinage/blob/master/src/java/org/opencoinage/util/Base62.java
 */
public class Base62 {

	private static final BigInteger BASE = BigInteger.valueOf(62);
	private static final String DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	/**
	 * Encodes a number using Base62 encoding.
	 *
	 * @param number a positive integer
	 * @return a Base62 string
	 *
	 * @throws IllegalArgumentException if <code>number</code> is a negative integer
	 */
	static String encode(BigInteger number) {
		if (number.compareTo(BigInteger.ZERO) < 0) {
			throw new IllegalArgumentException("number must not be negative");
		}
		StringBuilder result = new StringBuilder();
		while (number.compareTo(BigInteger.ZERO) > 0) {
			BigInteger[] divmod = number.divideAndRemainder(BASE);
			number = divmod[0];
			int digit = divmod[1].intValue();
			result.insert(0, DIGITS.charAt(digit));
		}
		return (result.length() == 0) ? DIGITS.substring(0, 1) : result.toString();
	}

	/**
	 * Decodes a string using Base62 encoding.
	 *
	 * @param string a Base62 string
	 * @return a positive integer
	 *
	 * @throws IllegalArgumentException if <code>string</code> is empty
	 */
	static BigInteger decode(final String string) {
		if (string.length() == 0) {
			throw new IllegalArgumentException("string must not be empty");
		}
		BigInteger result = BigInteger.ZERO;
		int digits = string.length();
		for (int index = 0; index < digits; index++) {
			int digit = DIGITS.indexOf(string.charAt(digits - index - 1));
			result = result.add(BigInteger.valueOf(digit).multiply(BASE.pow(index)));
		}
		return result;
	}

}