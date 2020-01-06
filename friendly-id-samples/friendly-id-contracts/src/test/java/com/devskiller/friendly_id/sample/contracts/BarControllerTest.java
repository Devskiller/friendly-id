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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BarController.class)
@EnableFriendlyId
public class BarControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void shouldGet() throws Exception {
		mockMvc.perform(get("/foos/{fooId}/bars/{barId}", "foo", "bar"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/hal+json"))
				.andExpect(jsonPath("$.name", is("Bar")))
				.andExpect(jsonPath("$._links.self.href", is("http://localhost/foos/foo/bars/bar")))
				.andExpect(jsonPath("$._links.foos.href", is("http://localhost/foos")));
	}

}
