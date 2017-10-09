package com.devskiller.friendly_id.sample.simple;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

	@Autowired
	private TestRestTemplate restTemplate;


	@Test
	public void shouldSerialize() throws Exception {

		// given
		UUID uuid = UUID.randomUUID();

		// expect
		Bar entity = restTemplate.getForEntity("/bars/{id}", Bar.class, uuid).getBody();

		then(entity.getId()).isEqualTo(uuid);

	}
}
