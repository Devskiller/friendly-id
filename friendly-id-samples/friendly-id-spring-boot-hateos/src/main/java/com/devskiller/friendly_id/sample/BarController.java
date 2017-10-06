package com.devskiller.friendly_id.sample;

import java.util.UUID;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devskiller.friendly_id.Url62;

@RestController
@ExposesResourceFor(Bar.class)
@RequestMapping("/bars")
public class BarController {

	private final FooService fooService;

	private final EntityLinks entityLinks;

	public BarController(FooService fooService, EntityLinks entityLinks) {
		this.fooService = fooService;
		this.entityLinks = entityLinks;
	}

	@GetMapping("/{id}")
	public Bar getBar(@PathVariable UUID id) {
		Bar resource = fooService.find(id);
		resource.add(entityLinks.linkForSingleResource(Bar.class, Url62.encode(id)).withSelfRel());
		return resource;
	}

	@PutMapping("/{id}")
	public void getBar(@PathVariable UUID id, @RequestBody Bar body) {
		fooService.update(id, body);
	}
}
