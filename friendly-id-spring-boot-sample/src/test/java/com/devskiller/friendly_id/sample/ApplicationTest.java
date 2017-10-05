package com.devskiller.friendly_id.sample;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.devskiller.friendly_id.Url62;
import com.devskiller.friendly_id.spring.FriendlyIdConfiguration;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BarController.class)
@Import(FriendlyIdConfiguration.class)
public class ApplicationTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	FooService fooService;

	@Test
	public void shouldReturnFriendlyId() throws Exception {

		UUID uuid = UUID.randomUUID();
		given(fooService.getBar(uuid)).willReturn(new Bar(uuid, uuid));

		mockMvc.perform(get("/bars/" + uuid))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.friendlyId", is(Url62.encode(uuid))))
				.andExpect(jsonPath("$.uuid", is(uuid.toString())));
	}
}
