package com.devskiller.friendly_id;

import java.util.UUID;

/**
 * Class to convert UUID to url Friendly IDs basing on Url62
 */
public class FriendlyId {

	/**
	 * Create FriendlyId id
	 *
	 * @return Friendly Id encoded UUID
	 */
	public static String createFriendlyId() {
		return Url62.encode(UUID.randomUUID());
	}

	/**
	 * Encode UUID to FriendlyId id
	 *
	 * @param uuid UUID to be encoded
	 * @return Friendly Id encoded UUID
	 */
	public static String toFriendlyId(UUID uuid) {
		return Url62.encode(uuid);
	}

	/**
	 * Decode Friendly Id to UUID
	 *
	 * @param friendlyId encoded UUID
	 * @return decoded UUID
	 */
	public static UUID toUuid(String friendlyId) {
		return Url62.decode(friendlyId);
	}

}
