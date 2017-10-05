package com.devskiller.friendly_id.sample;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarController {

	private final FooService fooService;

	public BarController(FooService fooService) {
		this.fooService = fooService;
	}

	@GetMapping("/bars/{bar}")
	public Bar getBar(@PathVariable UUID bar) {
		return fooService.getBar(bar);
	}
}
