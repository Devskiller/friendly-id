package com.devskiller.friendly_id.sample.contracts;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.devskiller.friendly_id.spring.EnableFriendlyId;

@RunWith(SpringRunner.class)
@WebMvcTest
@EnableFriendlyId
public abstract class ContractVerifierBase {

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() {
		RestAssuredMockMvc.webAppContextSetup(context);
	}

}
