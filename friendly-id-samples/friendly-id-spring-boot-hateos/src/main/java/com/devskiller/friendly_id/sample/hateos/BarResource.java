package com.devskiller.friendly_id.sample.hateos;

import lombok.Value;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(value = "bar", collectionRelation = "bars")
@Value
class BarResource extends ResourceSupport {

	private String name;

}
