package com.devskiller.friendly_id;

import static org.assertj.core.util.Objects.areEqual;

public class IdUtil {

	static boolean areEqualIgnoringLeadingZeros(String code1, String code2) {
		return areEqual(removeLeadingZeros(code1), removeLeadingZeros(code2));
	}

	private static String removeLeadingZeros(String string) {
		return string.replaceFirst("^0+(?!$)", "");
	}

}
