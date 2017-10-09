package com.devskiller.friendly_id.sample.contracts;

import java.util.UUID;

import org.springframework.hateoas.Identifiable;

import com.devskiller.friendly_id.Url62;

public class UuidHelper {

	public static String toFriendlyId(Identifiable<UUID> entity) {
		return Url62.encode(entity.getId());
	}
}
