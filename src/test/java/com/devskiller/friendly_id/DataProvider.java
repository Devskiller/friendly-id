package com.devskiller.friendly_id;

import java.util.UUID;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.test.Arbitrary;
import io.vavr.test.Gen;
import org.assertj.core.util.Strings;

public class DataProvider {

	public static Arbitrary<UUID> UUIDS = ignored -> {
		Gen<Long> longs = Gen.choose(Long.MIN_VALUE, Long.MAX_VALUE);
		return random -> new UUID(longs.apply(random), longs.apply(random));
	};

	public static Arbitrary<String> CODES = Arbitrary.string(
			Gen.frequency(
					Tuple.of(1, Gen.choose('A', 'Z')),
					Tuple.of(1, Gen.choose('a', 'z')),
					Tuple.of(1, Gen.choose('0', '9')))
	).filter(code -> !Strings.isNullOrEmpty(code) && Base62.decode(code, -1).bitLength() <= 128);

	public static Arbitrary<Tuple2<Long, Long>> LONG_PAIRS = ignored -> {
		Gen<Long> longs = Gen.choose(Long.MIN_VALUE, Long.MAX_VALUE);
		return random -> Tuple.of(longs.apply(random), longs.apply(random));
	};


	public static String removeLeadingZeros(String string) {
		return string.replaceFirst("^0+(?!$)", "");
	}
}

