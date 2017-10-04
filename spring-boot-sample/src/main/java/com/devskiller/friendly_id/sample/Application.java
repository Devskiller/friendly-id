package com.devskiller.friendly_id.sample;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PutMapping("/bars/{foo}")
	public void update(@PathVariable UUID foo) {
		System.out.println(foo);
	}

	@GetMapping("/bars/{foo}")
	public Foo getFoo(@PathVariable UUID foo) {
		System.out.println(foo);
		return new Foo(UUID.randomUUID());
	}

}
