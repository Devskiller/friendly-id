package com.devskiller.friendly_id.sample.hateos;

import com.devskiller.friendly_id.sample.hateos.domain.Bar;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilderFactory;

import static com.devskiller.friendly_id.FriendlyId.toFriendlyId;

public class BarResourceAssembler extends RepresentationModelAssemblerSupport<Bar, BarResource> {

	public BarResourceAssembler() {
		super(BarController.class, BarResource.class);
	}

	@Override
	public BarResource toModel(Bar entity) {
		BarResource resource = new BarResource(entity.getName());
		WebMvcLinkBuilderFactory factory = new WebMvcLinkBuilderFactory();
		resource.add(factory.linkTo(FooController.class).withRel("foos"));
		resource.add(factory.linkTo(BarController.class, toFriendlyId(entity.getFoo().getId())).slash(toFriendlyId(entity.getId())).withSelfRel());
		return resource;
	}

}
