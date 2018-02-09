package com.devskiller.friendly_id.sample.customized;

import java.util.UUID;

import lombok.Value;

import com.devskiller.friendly_id.jackson.IdFormat;

import static com.devskiller.friendly_id.jackson.FriendlyIdFormat.RAW;

@Value
class Bar {

	private final UUID friendlyId;

	@IdFormat(RAW)
	private final UUID uuid;

	public Bar(UUID friendlyId, @IdFormat(RAW) UUID uuid) {
		this.friendlyId = friendlyId;
		this.uuid = uuid;
	}
}
