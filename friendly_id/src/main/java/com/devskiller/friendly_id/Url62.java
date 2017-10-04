package com.devskiller.friendly_id;

import java.math.BigInteger;
import java.util.UUID;

/**
 * Class to convert uuid to url friendly IDs basing on base62
 */
public class Url62 {

	public static String create() {
		return encode(UUID.randomUUID());
	}

	public static String encode(UUID uuid) {
		BigInteger pair = ElegantPairing.pair(
				BigInteger.valueOf(uuid.getMostSignificantBits()),
				BigInteger.valueOf(uuid.getLeastSignificantBits())
		);
		return Base62.encode(pair);
	}

	public static UUID decode(String id) {
		BigInteger decoded = Base62.decode(id);
		BigInteger[] unpaired = ElegantPairing.unpair(decoded);
		return new UUID(unpaired[0].longValue(), unpaired[1].longValue());
	}

}
