package com.devskiller.friendly_id;

import java.util.UUID;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
@Warmup(iterations = 3)
@Measurement(iterations = 10)
@Fork(2)
public class FriendlyIdBenchmark {

	static final int SIZE = 1_000_000;

	UUID[] uuids;
	String[] ids;

	@Setup
	public void setup() {
		uuids = new UUID[SIZE];
		ids = new String[SIZE];
		for (int i = 0; i < SIZE; i++) {
			uuids[i] = UUID.randomUUID();
			ids[i] = FriendlyId.toFriendlyId(uuids[i]);
		}
	}

	@Benchmark
	@OperationsPerInvocation(SIZE)
	public void serializeUuid(Blackhole blackhole) {
		for (int i = 0; i < SIZE; i++) {
			blackhole.consume(FriendlyId.toFriendlyId(uuids[i]));
		}
	}

	@Benchmark
	@OperationsPerInvocation(SIZE)
	public void deserializeId(Blackhole blackhole) {
		for (int i = 0; i < SIZE; i++) {
			blackhole.consume(FriendlyId.toUuid(ids[i]));
		}
	}

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(FriendlyIdBenchmark.class.getSimpleName())
				.build();

		new Runner(opt).run();
	}
}
