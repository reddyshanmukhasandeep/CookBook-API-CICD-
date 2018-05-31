package com.cts.cde.io.cookbook.challenge;

import com.cts.cde.io.cookbook.CookBookApplication;
import com.cts.cde.io.cookbook.challenge.elasticsearch.ChallengeDocument;
import com.cts.cde.io.cookbook.challenge.elasticsearch.ChallengeDocumentService;
import com.google.gson.Gson;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CookBookApplication.class)
@WebMvcTest(value = ChallengeController.class, secure = false)
public class ChallengeControllerTest {

	public static final String CONTENT_TYPE = "Content-type";
	public static final ResultMatcher OK = status().isOk();
	public static final ResultMatcher RESPONSE_FORMAT = content().contentType(MediaType.APPLICATION_JSON_UTF8);

	public static final String PATH = "/challenge";
	private String TITLE = "searchtext";
	private String DESCRIPTION = "someDescription";
	private Long PLATFORM_ID = 1l;
	private Long LANGUAGE_ID = 1l;
	private String TAGS = "tags";
	private String SERACH_TEXT = "searchtext";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ChallengeController controller;

	@MockBean
	ChallengeService service;

	@MockBean
	private ChallengeDocumentService challengeDocumentService;

	private Challenge challenge = new Challenge(TITLE, DESCRIPTION, PLATFORM_ID, LANGUAGE_ID, TAGS);
	
	@Before
	public void setUp() throws Exception {
		challenge.setId(1l);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void thatGetListOfChallengesWhenFetchChallengeList() throws Exception {

		List<Challenge> userList = new ArrayList();
		userList.add(challenge);
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
	public void thatGetChallengeWhenPassinChallengeId() throws Exception {

		int SOME_Challenge_ID = 1;
		given(service.findById(SOME_Challenge_ID)).willReturn(challenge);
		MvcResult result = this.mockMvc.perform(get(PATH + "/" + SOME_Challenge_ID).accept(MediaType.APPLICATION_JSON)
				.header(CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(OK).andExpect(RESPONSE_FORMAT).andReturn();
		String jsonString = result.getResponse().getContentAsString();
		Challenge role = new Gson().fromJson(jsonString, Challenge.class);
		assertThat(role, org.hamcrest.CoreMatchers.is(notNullValue()));
	}

	@Test
	public void thatCreateNewChallengeWhenPassingNewChallengeInformation() throws Exception {
		given(service.saveChallenge(Mockito.any(Challenge.class))).willReturn(challenge);
		String content = new Gson().toJson(challenge);
		this.mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(OK)
				.andExpect(jsonPath("$.title").value(TITLE)).andReturn();
		}

	@Test
	public void thatUpdateChallengeWhenPassingChallengeInfo() throws Exception {
		int SOME_Challenge_ID = 1;
		given(service.updateChallenge(Mockito.any(Challenge.class), Mockito.anyInt())).willReturn(challenge);
		String content = new Gson().toJson(challenge);
		this.mockMvc
				.perform(put(PATH + "/" + SOME_Challenge_ID).contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(OK).andExpect(jsonPath("$.title").value(TITLE)).andReturn();
	}

	@Test
	public void thatDeleteChallengeWhenRequestDeleteChallenge() throws Exception {
		int SOME_Challenge_ID = 1;
		this.mockMvc.perform(delete(PATH + "/" + SOME_Challenge_ID).contentType(MediaType.APPLICATION_JSON))
				.andExpect(OK).andReturn();
	}

	/*@Test
	public void thatGetByTitle() throws Exception {
		challengeDocumentService.searchwithFullText(SERACH_TEXT);
		assertThat(byTitle.size(), is(1));
		int SOME_Challenge_ID = 1;
		given(service.findById(SOME_Challenge_ID)).willReturn(challenge);
		MvcResult result = this.mockMvc.perform(get(PATH + "/" + SOME_Challenge_ID).accept(MediaType.APPLICATION_JSON)
				.header(CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(OK).andExpect(RESPONSE_FORMAT).andReturn();
		String jsonString = result.getResponse().getContentAsString();
		Challenge role = new Gson().fromJson(jsonString, Challenge.class);
		assertThat(role, org.hamcrest.CoreMatchers.is(notNullValue()));

	}*/

}