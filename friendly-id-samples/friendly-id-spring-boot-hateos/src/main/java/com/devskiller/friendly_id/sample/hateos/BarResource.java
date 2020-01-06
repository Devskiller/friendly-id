package com.devskiller.friendly_id.sample.hateos;

import lombok.Value;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(value = "bar", collectionRelation = "bars")
@Value
class BarResource extends RepresentationModel<BarResource> {

	private String name;

}
