package com.devskiller.friendly_id.sample.contracts;

import org.springframework.hateoas.RelProvider;
import org.springframework.hateoas.mvc.ControllerLinkBuilderFactory;
import org.springframework.hateoas.mvc.IdentifiableResourceAssemblerSupport;

import com.devskiller.friendly_id.sample.contracts.domain.Bar;
import com.devskiller.friendly_id.sample.contracts.domain.Foo;

import static com.devskiller.friendly_id.sample.contracts.UuidHelper.toFriendlyId;

public class BarResourceAssembler extends IdentifiableResourceAssemblerSupport<Bar, BarResource> {

	private final RelProvider relProvider;

	public BarResourceAssembler(RelProvider relProvider) {
		this(BarController.class, relProvider);
	}

	public BarResourceAssembler(Class<?> controllerType, RelProvider relProvider) {
		super(controllerType, BarResource.class);
		this.relProvider = relProvider;
	}

	@Override
	public BarResource toResource(Bar entity) {
		BarResource resource = createResourceWithId(toFriendlyId(entity), entity, toFriendlyId(entity.getFoo()));
		ControllerLinkBuilderFactory factory = new ControllerLinkBuilderFactory();
		resource.add(factory.linkTo(FooController.class, toFriendlyId(entity.getFoo()))
				.withRel(relProvider.getCollectionResourceRelFor(Foo.class)));
		return resource;
	}

	@Override
	protected BarResource instantiateResource(Bar entity) {
		return new BarResource(entity.getName());
	}

}
