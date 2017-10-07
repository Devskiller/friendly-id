package com.devskiller.friendly_id.sample;

import org.springframework.hateoas.mvc.IdentifiableResourceAssemblerSupport;

import com.devskiller.friendly_id.Url62;
import com.devskiller.friendly_id.sample.domain.Foo;

public class FooResourceAssembler extends IdentifiableResourceAssemblerSupport<Foo, FooResource> {

	public FooResourceAssembler() {
		this(FooController.class);
	}

	public FooResourceAssembler(Class<?> controllerType) {
		super(controllerType, FooResource.class);
	}

	@Override
	public FooResource toResource(Foo entity) {
		FooResource resource = createResourceWithId(Url62.encode(entity.getId()), entity);
		resource.setName(entity.getName());
		resource.setUuid(entity.getId());
		return resource;
	}
}
