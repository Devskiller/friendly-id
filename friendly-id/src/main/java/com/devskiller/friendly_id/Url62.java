package com.devskiller.friendly_id;

import java.math.BigInteger;
import java.util.UUID;

/**
 * Class to convert UUID to Url62 IDs
 */
class Url62 {

	private final UuidConverter converter;

	Url62(UuidConverter converter) {
		this.converter = converter;
	}

	/**
	 * Encode UUID to Url62 id
	 *
	 * @param uuid UUID to be encoded
	 * @return url62 encoded UUID
	 */
	String encode(UUID uuid) {
		BigInteger pair = converter.toBigInteger(uuid);
		return Base62.encode(pair);
	}

	/**
	 * Decode url62 id to UUID
	 *
	 * @param id url62 encoded id
	 * @return decoded UUID
	 */
	UUID decode(String id) {
		BigInteger decoded = Base62.decode(id);
		return converter.toUuid(decoded);
	}

}
