package com.chitkara.bfhl.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class BfhlControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Test
	void postBfhlReturnsExampleA() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		mockMvc.perform(post("/bfhl")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"data\": [\"a\", \"1\", \"334\", \"4\", \"R\", \"$\"]}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.is_success").value(true))
				.andExpect(jsonPath("$.user_id").value("shreeya_02082005"))
				.andExpect(jsonPath("$.odd_numbers[0]").value("1"))
				.andExpect(jsonPath("$.even_numbers[0]").value("334"))
				.andExpect(jsonPath("$.sum").value("339"))
				.andExpect(jsonPath("$.concat_string").value("Ra"));
	}
}
