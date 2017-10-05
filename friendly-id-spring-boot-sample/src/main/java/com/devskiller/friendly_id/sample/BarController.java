package com.devskiller.friendly_id.sample;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarController {

	private final FooService fooService;

	public BarController(FooService fooService) {
		this.fooService = fooService;
	}

	@GetMapping("/bars/{bar}")
	public Bar getBar(@PathVariable UUID bar) {
		return fooService.find(bar);
	}

	@PutMapping("/bars/{bar}")
	public void getBar(@PathVariable UUID bar, @RequestBody Bar body) {
		fooService.update(bar, body);
	}
}
