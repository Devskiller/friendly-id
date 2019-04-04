package com.devskiller.friendly_id.sample.hateos.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.hateoas.Identifiable;

@Data
@AllArgsConstructor
public class Bar implements Identifiable<UUID> {

	private UUID id;
	private String name;

	private Foo foo;

	@Override
	public UUID getId() {
		return id;
	}

}
