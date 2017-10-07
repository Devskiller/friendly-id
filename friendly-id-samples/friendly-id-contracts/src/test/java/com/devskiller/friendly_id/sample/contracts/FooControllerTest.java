package com.devskiller.friendly_id.sample.contracts;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.devskiller.friendly_id.spring.EnableFriendlyId;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FooController.class)
@EnableFriendlyId
public class FooControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void shouldGet() throws Exception {
		mockMvc.perform(get("/foos/{id}", "cafe"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/hal+json;charset=UTF-8"))
				.andExpect(jsonPath("$.uuid", is("cafe")))
				.andExpect(jsonPath("$._links.self.href", is("http://localhost/foos/cafe")));
	}

	@Test
	public void shouldCreate() throws Exception {
		mockMvc.perform(post("/foos/")
				.content("{\"uuid\":\"newFoo\",\"name\":\"Very New Foo\"}")
				.contentType("application/hal+json;charset=UTF-8"))
				.andDo(print())
				.andExpect(header().string("Location", "http://localhost/foos/newFoo"))
				.andExpect(status().isCreated());
	}

	@Test
	public void update() throws Exception {
		mockMvc.perform(put("/foos/{id}", "foo")
				.content("{\"uuid\":\"foo\",\"name\":\"Sample Foo\"}")
				.contentType("application/hal+json;charset=UTF-8"))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
