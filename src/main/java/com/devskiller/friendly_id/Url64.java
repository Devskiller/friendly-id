package com.devskiller.friendly_id;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

/**
 * Class to convert uuid to url friendly IDs basing on base64
 */
@Deprecated
public class Url64 {

	private static final String BASE64_SUFFIX = "===";

	public static String create() {
		return encode(UUID.randomUUID());
	}

	public static String encode(UUID uuid) {
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(uuid.getMostSignificantBits());
		bb.putLong(uuid.getLeastSignificantBits());
		byte[] bytes = Base64.getUrlEncoder().encode(bb.array());
		return new String(bytes, 0, bytes.length - 2);
	}

	public static UUID decode(String string) {
		byte[] bytes = Base64.getUrlDecoder().decode(string + (string.length() % 4 > 0 ? BASE64_SUFFIX.substring(0, 4 - string.length() % 4) : ""));
		if (bytes.length != Long.SIZE / 8 * 2) {
			throw new IllegalArgumentException(String.format("Specified '%s' does not contains enough information to construct UUID: %s != %s",
					string, Long.SIZE / 8 * 2, bytes.length));
		}
		ByteBuffer bb = ByteBuffer.wrap(bytes);
		return new UUID(bb.getLong(), bb.getLong());
	}

}
