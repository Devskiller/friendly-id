package com.devskiller.friendly_id.sample.hateos;

import org.springframework.hateoas.mvc.ControllerLinkBuilderFactory;
import org.springframework.hateoas.mvc.IdentifiableResourceAssemblerSupport;

import com.devskiller.friendly_id.sample.hateos.domain.Bar;

public class BarResourceAssembler extends IdentifiableResourceAssemblerSupport<Bar, BarResource> {

	public BarResourceAssembler() {
		this(BarController.class);
	}

	public BarResourceAssembler(Class<?> controllerType) {
		super(controllerType, BarResource.class);
	}

	@Override
	public BarResource toResource(Bar entity) {
		BarResource resource = createResourceWithId(UuidHelper.toFriendlyId(entity), entity, UuidHelper.toFriendlyId(entity.getFoo()));
		resource.setName(entity.getName());
		ControllerLinkBuilderFactory factory = new ControllerLinkBuilderFactory();
		resource.add(factory.linkTo(FooController.class, UuidHelper.toFriendlyId(entity.getFoo())).withRel("foos"));
		return resource;
	}
}
