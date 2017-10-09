package com.devskiller.friendly_id.sample.contracts;

import lombok.Value;

import org.springframework.hateoas.ResourceSupport;

@Value
class BarResource extends ResourceSupport {

	private String name;

}
