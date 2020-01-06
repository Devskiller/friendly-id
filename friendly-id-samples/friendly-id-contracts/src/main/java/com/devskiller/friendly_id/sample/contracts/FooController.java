package com.devskiller.friendly_id.sample.contracts;

import com.devskiller.friendly_id.FriendlyId;
import com.devskiller.friendly_id.sample.contracts.domain.Foo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.UUID;


@RestController
@ExposesResourceFor(FooResource.class)
@RequestMapping("/foos")
public class FooController {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final EntityLinks entityLinks;
	private final FooResourceAssembler assembler;

	public FooController(EntityLinks entityLinks) {
		this.entityLinks = entityLinks;
		this.assembler = new FooResourceAssembler();
	}

	@GetMapping("/{id}")
	public FooResource get(@PathVariable UUID id) {
		log.info("Get {}", id);
		Foo foo = new Foo(id, "Foo");
		return assembler.toModel(foo);
	}

	@PutMapping("/{id}")
	public HttpEntity<FooResource> update(@PathVariable UUID id, @RequestBody FooResource fooResource) {
		log.info("Update {} : {}", id, fooResource);
		Foo entity = new Foo(fooResource.getUuid(), fooResource.getName());
		return ResponseEntity.ok(assembler.toModel(entity));
	}

	@PostMapping
	public HttpEntity<FooResource> create(@RequestBody FooResource fooResource) {
		HttpHeaders headers = new HttpHeaders();
		Foo entity = new Foo(fooResource.getUuid(), "Foo");

		// ...

		headers.setLocation(entityLinks.linkToItemResource(FooResource.class, FriendlyId.toFriendlyId(entity.getId())).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

}
