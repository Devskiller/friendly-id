package com.devskiller.friendly_id.sample.contracts;

import java.util.UUID;

import lombok.Value;

import org.springframework.hateoas.ResourceSupport;

@Value
public class FooResource extends ResourceSupport {

	private final UUID uuid;
	private final String name;

}
