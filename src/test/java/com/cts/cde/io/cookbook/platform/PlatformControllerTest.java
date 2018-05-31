package com.cts.cde.io.cookbook.platform;

import com.cts.cde.io.cookbook.CookBookApplication;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.google.gson.Gson;
import javax.validation.constraints.NotNull;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CookBookApplication.class)
@WebMvcTest(value = PlatformController.class, secure = false)
public class PlatformControllerTest {

	public static final String CONTENT_TYPE = "Content-type";
	public static final ResultMatcher OK = status().isOk();
	public static final ResultMatcher RESPONSE_FORMAT = content().contentType(MediaType.APPLICATION_JSON_UTF8);

	public static final String PATH = "/platform";
	private static final String NAME = "Java";
	// private String SOME_NAME = "some_name";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	PlatformController controller;

	@MockBean
	PlatformServiceImpl service;

	Platform platform = new Platform(1l, NAME);

	/*
	 * List<Platform> list = new ArrayList() { { add(platform); } };
	 */

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
		MockitoAnnotations.initMocks(this);
	}

	private Platform getPlatform() {

		Platform src = new Platform(1l, NAME);
		src.setName(NAME);
		src.setId(1l);
		return src;
	}

	@Test
	public void thatGetListOfPlatformWhenRequestAllContent() throws Exception {

		List<Platform> userList = new ArrayList();
		userList.add(platform);
		given(service.findAll()).willReturn(userList);

		ResultMatcher hasRecord = jsonPath("$").isArray();
		MvcResult result = this.mockMvc
				.perform(get(PATH).accept(MediaType.APPLICATION_JSON).header(CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(OK).andExpect(RESPONSE_FORMAT).andExpect(hasRecord).andReturn();

		String jsonString = result.getResponse().getContentAsString();
		DocumentContext context = JsonPath.parse(jsonString);
		int length = context.read("$.length()");
		assertThat(length, org.hamcrest.CoreMatchers.is(1));
	}

	@Test
	public void thatGetPlatformModelWhenPassingPlatformId() throws Exception {

		int SOME_PLATFORM_ID = 1;
		given(service.findById(SOME_PLATFORM_ID)).willReturn(platform);
		MvcResult result = this.mockMvc.perform(get(PATH + "/" + SOME_PLATFORM_ID).accept(MediaType.APPLICATION_JSON)
				.header(CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(OK).andExpect(RESPONSE_FORMAT).andReturn();
		String jsonString = result.getResponse().getContentAsString();
		Platform response = new Gson().fromJson(jsonString, Platform.class);

		assertThat(response, is(notNullValue()));
	}

	@Test
	public void thatCreateNewPlatformWhenPassingNewPlatformInformation() throws Exception {
		given(service.createPlatform(Mockito.any(Platform.class))).willReturn(platform);
		String p = new Gson().toJson(getPlatform());
		this.mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON).content(p)).andExpect(OK)
				.andExpect(jsonPath("$.name").value(NAME)).andReturn();
	}

	@Test
	public void thatDeletePlatformWhenRequestDeletePlatform() throws Exception {
		int SOME_ID = 1;
		this.mockMvc.perform(delete(PATH + "/" + SOME_ID).contentType(MediaType.APPLICATION_JSON)).andExpect(OK)
				.andReturn();
	}

	@Test
	public void thatUpdatePlatformWhenPassingPlatformInfo() throws Exception {
		int SOME_ID = 1;
		given(service.updatePlatform(Mockito.any(Platform.class), Mockito.anyInt())).willReturn(platform);
		Platform platform = getPlatform();
		assertThat(platform.toString(), is(notNullValue()));

		String content = new Gson().toJson(platform);
		this.mockMvc.perform(put(PATH + "/" + SOME_ID).contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(OK).andExpect(jsonPath("$.name").value(NAME)).andReturn();
	}

}
