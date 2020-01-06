package com.devskiller.friendly_id.sample.hateos;

import com.devskiller.friendly_id.sample.hateos.domain.Foo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.UUID;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@ExposesResourceFor(FooResource.class)
@RequestMapping("/foos")
public class FooController {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final FooResourceAssembler assembler;

	public FooController(FooResourceAssembler assembler) {
		this.assembler = assembler;
	}

	@GetMapping("/{id}")
	public HttpEntity<FooResource> get(@PathVariable UUID id) {
		log.info("Get {}", id);
		Foo foo = new Foo(id, "Foo");

		FooResource fooResource = assembler.toModel(foo);
		return ResponseEntity.ok(fooResource);
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
		URI location = MvcUriComponentsBuilder.fromMethodCall(on(getClass())
				.get(fooResource.getUuid()))
				.buildAndExpand()
				.toUri();

		headers.setLocation(location);

		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

}
