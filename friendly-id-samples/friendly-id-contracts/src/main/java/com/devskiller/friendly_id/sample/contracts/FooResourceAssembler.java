package com.devskiller.friendly_id.sample.contracts;

import com.devskiller.friendly_id.FriendlyId;
import com.devskiller.friendly_id.sample.contracts.domain.Foo;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilderFactory;

public class FooResourceAssembler extends RepresentationModelAssemblerSupport<Foo, FooResource> {

	public FooResourceAssembler() {
		super(FooController.class, FooResource.class);
	}

	@Override
	public FooResource toModel(Foo entity) {
		WebMvcLinkBuilderFactory factory = new WebMvcLinkBuilderFactory();
		FooResource resource = new FooResource(entity.getId(), entity.getName());
		resource.add(factory.linkTo(FooController.class).slash(FriendlyId.toFriendlyId(entity.getId())).withSelfRel());
		return resource;
	}
}
