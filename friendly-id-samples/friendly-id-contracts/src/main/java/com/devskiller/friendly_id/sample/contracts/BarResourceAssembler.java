package com.devskiller.friendly_id.sample.contracts;

import com.devskiller.friendly_id.FriendlyId;
import com.devskiller.friendly_id.sample.contracts.domain.Bar;
import com.devskiller.friendly_id.sample.contracts.domain.Foo;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilderFactory;

public class BarResourceAssembler extends RepresentationModelAssemblerSupport<Bar, BarResource> {

	LinkRelationProvider relProvider;

	public BarResourceAssembler() {
		super(BarController.class, BarResource.class);
	}

	public BarResourceAssembler(LinkRelationProvider relProvider) {
		super(BarController.class, BarResource.class);
		this.relProvider = relProvider;
	}

	@Override
	public BarResource toModel(Bar entity) {
		BarResource resource = new BarResource(entity.getName());
		WebMvcLinkBuilderFactory factory = new WebMvcLinkBuilderFactory();
		resource.add(factory.linkTo(FooController.class, FriendlyId.toFriendlyId(entity.getFoo().getId()))
				.withRel(relProvider.getCollectionResourceRelFor(Foo.class)));
		resource.add(factory.linkTo(BarController.class, FriendlyId.toFriendlyId(entity.getFoo().getId())).slash(FriendlyId.toFriendlyId(entity.getId())).withSelfRel());
		return resource;
	}

}
