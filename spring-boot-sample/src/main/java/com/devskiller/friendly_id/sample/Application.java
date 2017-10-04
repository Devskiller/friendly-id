package com.devskiller.friendly_id.sample;

import java.util.UUID;

import lombok.Value;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/bars/{bar}")
	public Bar getBar(@PathVariable UUID bar) {
		return new Bar(UUID.randomUUID());
	}

	@Value
	class Bar {
		private final UUID id;
	}

}
