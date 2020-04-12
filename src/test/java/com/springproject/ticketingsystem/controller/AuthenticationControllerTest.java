package com.springproject.ticketingsystem.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AuthenticationControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void TestThatLoginPageIsReturnedWhenLoginGetControllerIsCalled() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(model().attributeExists("user"));
	}

	@Test
	public void TestThatLoginFailedPageIsReturnedAndReturnsTheCorrectObjectsWhenLoginFailedPageIsSentToTheUser()
			throws Exception {

		mockMvc.perform(get("/loginFailed")).andExpect(status().isOk()).andExpect(model().attributeExists("user"))
				.andExpect(model().attributeExists("error"));
	}
}
