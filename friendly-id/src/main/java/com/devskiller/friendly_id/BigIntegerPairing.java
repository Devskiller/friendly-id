package com.devskiller.friendly_id;

import java.math.BigInteger;

public interface BigIntegerPairing {

	BigInteger pair(BigInteger first, BigInteger second);

	BigInteger[] unpair(BigInteger value);
}
