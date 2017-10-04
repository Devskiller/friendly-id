package com.devskiller.friendly_id.spring;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.devskiller.friendly_id.jackson.FriendlyIdModule;

public class ModuleTestBase {

	protected ObjectMapper mapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new FriendlyIdModule());
		return mapper;
	}
}
