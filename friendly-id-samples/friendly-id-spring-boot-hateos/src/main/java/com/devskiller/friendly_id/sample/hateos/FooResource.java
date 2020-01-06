package com.devskiller.friendly_id.sample.hateos;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Relation(value = "foos")
@Value
public class FooResource extends RepresentationModel<FooResource> {

	private final UUID uuid;
	private final String name;
	@JsonUnwrapped
	private final CollectionModel<BarResource> embeddeds;

}
