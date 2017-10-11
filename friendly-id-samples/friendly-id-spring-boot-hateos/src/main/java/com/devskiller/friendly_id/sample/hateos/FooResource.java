package com.devskiller.friendly_id.sample.hateos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.Relation;

@Relation(value = "profiles")
public class FooResource extends ResourceSupport {

	private UUID uuid;
	private String name;
	@JsonUnwrapped
	private Resources<EmbeddedWrapper> embeddeds;

	public FooResource() {
	}

	public FooResource(UUID uuid, String name) {
		this.uuid = uuid;
		this.name = name;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Resources<EmbeddedWrapper> getEmbeddeds() {
		return embeddeds;
	}

	public void setEmbeddeds(Resources<EmbeddedWrapper> embeddeds) {
		this.embeddeds = embeddeds;
	}
}
