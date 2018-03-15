package com.devskiller.friendly_id.sample.hateos;

import java.util.UUID;

import org.springframework.hateoas.Identifiable;

import com.devskiller.friendly_id.FriendlyId;

public class UuidHelper {

	public static String toFriendlyId(Identifiable<UUID> entity) {
		return FriendlyId.encode(entity.getId());
	}
}
