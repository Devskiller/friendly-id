package com.devskiller.friendly_id.sample;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
class FooService {

	Bar find(UUID uuid) {
		System.out.println("find: " + uuid);
		return new Bar(uuid, uuid);
	}

	void update(UUID id, Bar bar) {
		System.out.println("update: " + id + ":" + bar);
	}
}
