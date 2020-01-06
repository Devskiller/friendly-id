package com.devskiller.friendly_id.sample.contracts;

import com.devskiller.friendly_id.sample.contracts.domain.Bar;
import com.devskiller.friendly_id.sample.contracts.domain.Foo;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@ExposesResourceFor(BarResource.class)
@RequestMapping("/foos/{fooId}/bars")
public class BarController {

	private final BarResourceAssembler assembler;

	public BarController(BarResourceAssembler assembler) {
		this.assembler = assembler;
	}

	@GetMapping("/{id}")
	public BarResource getBar(@PathVariable UUID fooId, @PathVariable UUID id) {
		return assembler.toModel(new Bar(id, "Bar", new Foo(fooId, "Root Foo")));
	}

}
