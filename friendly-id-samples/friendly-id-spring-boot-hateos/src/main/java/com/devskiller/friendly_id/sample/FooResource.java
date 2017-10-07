package com.devskiller.friendly_id.sample;

import java.util.UUID;

import org.springframework.hateoas.ResourceSupport;

public class FooResource extends ResourceSupport {

	private UUID uuid;
	private String name;

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
