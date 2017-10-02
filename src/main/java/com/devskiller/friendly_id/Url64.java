package com.devskiller.friendly_id;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

/**
 * Class to convert uuid to url friendly IDs basing on base64
 */
public class Url64 {

	private static final String BASE64_SUFFIX = "==";

	public static String create() {
		return encode(UUID.randomUUID());
	}

	public static String encode(UUID uuid) {
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(uuid.getMostSignificantBits());
		bb.putLong(uuid.getLeastSignificantBits());
		return new String(Base64.getUrlEncoder().encode(bb.array()), 0, 22);
	}

	public static UUID decode(String str) {
		byte[] bytes = Base64.getUrlDecoder().decode(str + BASE64_SUFFIX);
		ByteBuffer bb = ByteBuffer.wrap(bytes);
		return new UUID(bb.getLong(), bb.getLong());
	}

}
