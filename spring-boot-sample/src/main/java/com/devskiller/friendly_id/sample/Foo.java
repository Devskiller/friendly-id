package com.devskiller.friendly_id.sample;

import java.util.UUID;

public class Foo {

	private final UUID id;

	public Foo(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}
}
