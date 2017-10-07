package com.devskiller.friendly_id.sample.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.hateoas.Identifiable;

@AllArgsConstructor
public class Bar implements Identifiable<UUID> {

	private UUID id;
	private String name;
	private Foo foo;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Foo getFoo() {
		return foo;
	}

	public void setFoo(Foo foo) {
		this.foo = foo;
	}
}
