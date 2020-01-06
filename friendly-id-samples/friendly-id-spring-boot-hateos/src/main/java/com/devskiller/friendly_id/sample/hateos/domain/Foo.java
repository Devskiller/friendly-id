package com.devskiller.friendly_id.sample.hateos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Foo {

	private UUID id;
	private String name;

}
