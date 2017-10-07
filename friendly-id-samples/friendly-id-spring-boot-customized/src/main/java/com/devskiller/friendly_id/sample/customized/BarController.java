package com.devskiller.friendly_id.sample.customized;

import java.lang.invoke.MethodHandles;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bars")
public class BarController {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final FooService fooService;

	public BarController(FooService fooService) {
		this.fooService = fooService;
	}

	@GetMapping("/{id}")
	public Bar get(@PathVariable UUID id) {
		log.info("get {}", id);
		return fooService.find(id);
	}

	@PutMapping("/{id}")
	public void getBar(@PathVariable UUID id, @RequestBody Bar body) {
		fooService.update(id, body);
	}
}
