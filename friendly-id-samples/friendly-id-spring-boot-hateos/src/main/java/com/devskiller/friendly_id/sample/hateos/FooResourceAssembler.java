package com.devskiller.friendly_id.sample.hateos;

import java.util.Arrays;
import java.util.List;

import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.EmbeddedWrappers;
import org.springframework.hateoas.mvc.IdentifiableResourceAssemblerSupport;

import com.devskiller.friendly_id.FriendlyId;
import com.devskiller.friendly_id.sample.hateos.domain.Bar;
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
		return createResourceWithId(UuidHelper.toFriendlyId(entity), entity);
	}

	@Override
	protected FooResource instantiateResource(Foo entity) {

		BarResource bar1 = new BarResourceAssembler().toResource(new Bar(FriendlyId.toUuid("bar1"), "bar one", entity));
		BarResource bar2 = new BarResourceAssembler().toResource(new Bar(FriendlyId.toUuid("bar2"), "bar two", entity));

		EmbeddedWrappers wrappers = new EmbeddedWrappers(true);
		List<EmbeddedWrapper> embeddeds = Arrays.asList(wrappers.wrap(bar1), wrappers.wrap(bar2));
		FooResource fooResource = new FooResource(entity.getId(), entity.getName());
		fooResource.setEmbeddeds(new Resources(embeddeds));
		return fooResource;
	}
}
