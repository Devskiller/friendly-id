package com.devskiller.friendly_id;

import java.math.BigInteger;
import java.util.UUID;

/**
 * Class to convert uuid to url friendly IDs basing on base62
 */
public class Url62 {

	/**
	 * Create url62 id
	 *
	 * @return url62 encoded id
	 */
	public static String create() {
		return encode(UUID.randomUUID());
	}

	/**
	 * Encode UUID to url62 id
	 *
	 * @param uuid UUID to be encoded
	 *
	 * @return url62 encoded uuid
	 */
	public static String encode(UUID uuid) {
		BigInteger pair = ElegantPairing.pair(
				BigInteger.valueOf(uuid.getMostSignificantBits()),
				BigInteger.valueOf(uuid.getLeastSignificantBits())
		);
		return Base62.encode(pair);
	}

	/**
	 * Decode url62 id to UUID
	 *
	 * @param id url62 encoded id
	 *
	 * @return decoded UUID
	 */
	public static UUID decode(String id) {
		BigInteger decoded = Base62.decode(id);
		BigInteger[] unpaired = ElegantPairing.unpair(decoded);
		return new UUID(unpaired[0].longValue(), unpaired[1].longValue());
	}

}
