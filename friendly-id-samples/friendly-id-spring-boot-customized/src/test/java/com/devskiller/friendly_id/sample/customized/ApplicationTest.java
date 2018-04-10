package com.devskiller.friendly_id.sample.customized;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.devskiller.friendly_id.FriendlyId;
import com.devskiller.friendly_id.spring.EnableFriendlyId;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BarController.class)
@EnableFriendlyId
public class ApplicationTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	FooService fooService;

	@Test
	public void shouldSerialize() throws Exception {

		// given
		UUID uuid = UUID.randomUUID();
		given(fooService.find(uuid)).willReturn(new Bar(uuid, uuid));

		// expect
		mockMvc.perform(get("/bars/{id}", FriendlyId.toFriendlyId(uuid)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.friendlyId", is(FriendlyId.toFriendlyId(uuid))))
				.andExpect(jsonPath("$.uuid", is(uuid.toString())));
	}

	@Test
	public void shouldDeserialize() throws Exception {

		// given
		UUID uuid = UUID.randomUUID();
		String json = "{\"friendlyId\":\"" + FriendlyId.toFriendlyId(uuid) + "\",\"uuid\":\"" + uuid + "\"}";

		// when
		mockMvc.perform(put("/bars/{id}", FriendlyId.toFriendlyId(uuid))
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());

		// then
		then(fooService)
				.should().update(uuid, new Bar(uuid, uuid));
	}
}
