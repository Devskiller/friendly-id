package com.devskiller.friendly_id;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnalyzeGeneratedIds {

	private List<String> ids = new ArrayList<>();

	@Test
	public void analyzeGeneratedValueStatistics() {
		for (int i = 0; i < 1_000_000; i++) {
			System.out.print(".");
			this.ids.add(Base62.encode(UuidConverter.convertToBigInteger(UUID.randomUUID())));
		}
		IntSummaryStatistics elegantStats = ids.stream()
				.map(String::length).mapToInt(Integer::intValue).summaryStatistics();

		System.out.println("\nResults:");
		System.out.println("Min: " + elegantStats.getMin());
		System.out.println("Max: " + elegantStats.getMax());
		System.out.println("Avg: " + elegantStats.getAverage());
		System.out.println("Count: " + elegantStats.getCount());
		System.out.println("Sample: " + ids.stream().limit(100).collect(Collectors.joining("\n")));

		assertThat(elegantStats.getMax()).isEqualTo(22);
		assertThat(elegantStats.getMin()).isEqualTo(22);
		assertThat(elegantStats.getAverage()).isEqualTo(22);
	}


}