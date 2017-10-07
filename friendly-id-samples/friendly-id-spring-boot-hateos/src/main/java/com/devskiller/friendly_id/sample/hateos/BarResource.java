package com.devskiller.friendly_id.sample.hateos;

import org.springframework.hateoas.ResourceSupport;

class BarResource extends ResourceSupport {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
