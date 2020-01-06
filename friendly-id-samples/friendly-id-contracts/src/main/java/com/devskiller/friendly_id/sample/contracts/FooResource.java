package com.devskiller.friendly_id.sample.contracts;

import lombok.Value;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Relation(value = "foos")
@Value
public class FooResource extends RepresentationModel<FooResource> {

	private final UUID uuid;
	private final String name;

}
