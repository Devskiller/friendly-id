package com.devskiller.friendly_id.sample.hateos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Value;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.EmbeddedWrappers;
import org.springframework.hateoas.core.Relation;

public class FooResource extends ResourceSupport {

	private  UUID uuid;
	private  String name;

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

	@Override
	public String toString() {
		return "FooResource{" +
				"uuid=" + uuid +
				", name='" + name + '\'' +
				'}';
	}
}
