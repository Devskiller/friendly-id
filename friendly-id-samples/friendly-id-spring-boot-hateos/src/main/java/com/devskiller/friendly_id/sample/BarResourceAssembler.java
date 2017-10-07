package com.devskiller.friendly_id.sample;

import java.util.UUID;

import org.springframework.hateoas.mvc.ControllerLinkBuilderFactory;
import org.springframework.hateoas.mvc.IdentifiableResourceAssemblerSupport;

import com.devskiller.friendly_id.Url62;
import com.devskiller.friendly_id.sample.domain.Bar;

public class BarResourceAssembler extends IdentifiableResourceAssemblerSupport<Bar, BarResource> {

	public BarResourceAssembler() {
		this(BarController.class);
	}

	public BarResourceAssembler(Class<?> controllerType) {
		super(controllerType, BarResource.class);
	}

	@Override
	public BarResource toResource(Bar entity) {
		BarResource resource = createResourceWithId(Url62.encode(entity.getId()),
				entity, Url62.encode(entity.getFoo().getId()));
		resource.setName(entity.getName());
		ControllerLinkBuilderFactory linkBuilderFactory = new ControllerLinkBuilderFactory();
		resource.add(linkBuilderFactory.linkTo(FooController.class, Url62.encode(entity.getFoo().getId()))
				.withRel("foos"));
		return resource;
	}
}
