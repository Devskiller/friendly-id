package com.devskiller.friendly_id.sample;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
class FooService {

	Bar getBar(UUID uuid) {
		System.out.println("getBar: " + uuid);
		return new Bar(uuid, uuid);
	}
}
