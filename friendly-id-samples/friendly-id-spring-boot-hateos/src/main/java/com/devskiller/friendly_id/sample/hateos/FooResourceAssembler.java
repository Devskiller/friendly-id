package com.devskiller.friendly_id.sample.hateos;

import org.springframework.hateoas.mvc.IdentifiableResourceAssemblerSupport;

import com.devskiller.friendly_id.sample.hateos.domain.Foo;

public class FooResourceAssembler extends IdentifiableResourceAssemblerSupport<Foo, FooResource> {

	public FooResourceAssembler() {
		this(FooController.class);
	}

	public FooResourceAssembler(Class<?> controllerType) {
		super(controllerType, FooResource.class);
	}

	@Override
	public FooResource toResource(Foo entity) {
		FooResource resource = createResourceWithId(UuidHelper.toFriendlyId(entity), entity);

		resource.setUuid(entity.getId());
		resource.setName(entity.getName());
		return resource;
	}

//	@Override
//	protected FooResource instantiateResource(Foo entity) {
//				return new FooResource(entity.getId(), entity.getName());
//	}
}
