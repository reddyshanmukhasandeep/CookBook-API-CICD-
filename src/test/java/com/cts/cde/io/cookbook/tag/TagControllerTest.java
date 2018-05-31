package com.cts.cde.io.cookbook.tag;

import com.cts.cde.io.cookbook.CookBookApplication;
import com.cts.cde.io.cookbook.programminglanguage.ProgrammingLanguage;
import com.cts.cde.io.cookbook.programminglanguage.ProgrammingLanguageController;
import com.cts.cde.io.cookbook.programminglanguage.ProgrammingLanguageService;
import com.google.gson.Gson;
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
@WebMvcTest(value = TagController.class, secure = false)
public class TagControllerTest {

	public static final String CONTENT_TYPE = "Content-type";
	public static final ResultMatcher OK = status().isOk();
	public static final ResultMatcher RESPONSE_FORMAT = content().contentType(MediaType.APPLICATION_JSON_UTF8);

	public static final String PATH = "/tag";

	private static final String NAME = "tag";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	TagController controller;

	@MockBean
	TagService service;

	Tag tag = new Tag(1l, NAME);

	

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
		MockitoAnnotations.initMocks(this);
	}

	private Tag getTag() {

		Tag src = new Tag(1l, NAME);
		src.setName(NAME);
		return src;
	}

	@Test
	public void thatGetListOfTagWhenRequestAllTag() throws Exception {

		List<Tag> userList = new ArrayList();
		userList.add(tag);
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
	public void thatGetTagModelWhenPassingTagId() throws Exception {

		int SOME_TAG_ID = 1;
		given(service.findById(SOME_TAG_ID)).willReturn(tag);
		MvcResult result = this.mockMvc.perform(get(PATH + "/" + SOME_TAG_ID).accept(MediaType.APPLICATION_JSON)
				.header(CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(OK).andExpect(RESPONSE_FORMAT).andReturn();
		String jsonString = result.getResponse().getContentAsString();
		Tag response = new Gson().fromJson(jsonString, Tag.class);

		assertThat(response, is(notNullValue()));
	}

	@Test
       public void thatCreateNewTagWhenPassingNewTagInformation() throws Exception {
           given(service.createTag(Mockito.any(Tag.class))).willReturn(tag);
           String tag = new Gson().toJson(getTag());
           this.mockMvc.perform(post(PATH)
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(tag))
                   .andExpect(OK)
                   .andExpect(jsonPath("$.name").value(NAME))
                   .andReturn();
       }

	@Test
	public void thatDeleteTagWhenRequestDeleteTag() throws Exception {
		int SOME_ID = 1;
		this.mockMvc.perform(delete(PATH + "/" + SOME_ID).contentType(MediaType.APPLICATION_JSON)).andExpect(OK)
				.andReturn();
	}

	@Test
       public void thatUpdateContentWhenPassingTagInfo() throws Exception {
           int SOME_ID = 1;
           given(service.updateTag(Mockito.any(Tag.class), Mockito.anyInt()))
                   .willReturn(tag);
           Tag tag = getTag();
           assertThat(tag.toString(), is(notNullValue()));
          

           String content = new Gson().toJson(tag);
           this.mockMvc.perform(put(PATH + "/" + SOME_ID)
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(content))
                   .andExpect(OK)
                   .andExpect(jsonPath("$.name").value(NAME))
                   .andReturn();
       }
}
