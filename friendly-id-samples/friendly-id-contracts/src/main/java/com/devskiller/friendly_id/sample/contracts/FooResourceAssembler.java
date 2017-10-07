package com.devskiller.friendly_id.sample.contracts;

import org.springframework.hateoas.mvc.IdentifiableResourceAssemblerSupport;

import com.devskiller.friendly_id.sample.contracts.domain.Foo;

import static com.devskiller.friendly_id.sample.contracts.UuidHelper.toFriendlyId;

public class FooResourceAssembler extends IdentifiableResourceAssemblerSupport<Foo, FooResource> {

	public FooResourceAssembler() {
		this(FooController.class);
	}

	public FooResourceAssembler(Class<?> controllerType) {
		super(controllerType, FooResource.class);
	}

	@Override
	public FooResource toResource(Foo entity) {
		return createResourceWithId(toFriendlyId(entity), entity);
	}

	@Override
	protected FooResource instantiateResource(Foo entity) {
		return new FooResource(entity.getId(), entity.getName());
	}
}
