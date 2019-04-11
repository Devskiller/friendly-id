package com.devskiller.friendly_id;

import java.math.BigInteger;
import java.util.UUID;

class UuidConverter {

	private final BigIntegerPairing pairing;

	UuidConverter(BigIntegerPairing pairing) {
		this.pairing = pairing;
	}

	BigInteger toBigInteger(UUID uuid) {
		return pairing.pair(
				BigInteger.valueOf(uuid.getMostSignificantBits()),
				BigInteger.valueOf(uuid.getLeastSignificantBits())
		);
	}

	UUID toUuid(BigInteger value) {
		BigInteger[] unpaired = pairing.unpair(value);
		return new UUID(unpaired[0].longValueExact(), unpaired[1].longValueExact());
	}

}
