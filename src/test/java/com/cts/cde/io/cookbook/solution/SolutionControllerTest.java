package com.cts.cde.io.cookbook.solution;

import com.cts.cde.io.cookbook.CookBookApplication;
import com.cts.cde.io.cookbook.challenge.Challenge;
import com.cts.cde.io.cookbook.challenge.elasticsearch.ChallengeDocumentService;
import com.cts.cde.io.cookbook.solution.elasticsearch.SolutionDocumentService;
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
@WebMvcTest(value = SolutionController.class, secure = false)
public class SolutionControllerTest {

	public static final String CONTENT_TYPE = "Content-type";
	public static final ResultMatcher OK = status().isOk();
	public static final ResultMatcher RESPONSE_FORMAT = content().contentType(MediaType.APPLICATION_JSON_UTF8);

	public static final String PATH = "/solution";
	private static final String SOME_STAGE_NAME = "some_stage_name";
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	SolutionController controller;

	@MockBean
	SolutionService service;
	
	@MockBean
	private SolutionDocumentService documentService;

	private String DESCRIPTION = "description";
	private String REFERENCE_LINK = "reference_link";
	private Solution solution = new Solution(1l, DESCRIPTION,REFERENCE_LINK);
	//private SubStageFilter sampleSubStageFilter = new SubStageFilter(1, SOME_NAME, new Stage());

	@Before
	public void setUp() throws Exception {
		//sampleSubStage.setId(1);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
		MockitoAnnotations.initMocks(this);
	}

//	@Test
//	public void thatGetListOfRolesWhenFetchRoleList() throws Exception {
//
//		List<Solution> userList = new ArrayList();
//		userList.add(solution);
//		given(service.findAll().willReturn(userList);
//
//		ResultMatcher hasRecord = jsonPath("$").isArray();
//		MvcResult result = this.mockMvc
//				.perform(get(PATH).accept(MediaType.APPLICATION_JSON).header(CONTENT_TYPE, MediaType.APPLICATION_JSON))
//				.andExpect(OK).andExpect(RESPONSE_FORMAT).andExpect(hasRecord).andReturn();
//
//		String jsonString = result.getResponse().getContentAsString();
//		DocumentContext context = JsonPath.parse(jsonString);
//		int length = context.read("$.length()");
//		assertThat(length, is(1));
//		assertThat(solution.getDescription(), is(notNullValue()));
//		assertThat(solution.getReference_link(), is(notNullValue()));
//	}

	@Test
	public void thatGetSolutionWhenPassinSolutionId() throws Exception {

		int SOME_Solution_ID = 1;
		given(service.findById(SOME_Solution_ID)).willReturn(solution);
		MvcResult result = this.mockMvc.perform(get(PATH + "/" + SOME_Solution_ID).accept(MediaType.APPLICATION_JSON)
				.header(CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(OK).andExpect(RESPONSE_FORMAT).andReturn();
		String jsonString = result.getResponse().getContentAsString();
		Solution solution = new Gson().fromJson(jsonString, Solution.class);
		assertThat(solution, is(notNullValue()));
	}

	@Test
	public void thatCreateNeSolutionWhenPassingNewSolutionInformation() throws Exception {
		given(service.createSolution(Mockito.any(Solution.class))).willReturn(solution);
		String content = new Gson().toJson(solution);
		this.mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(OK)
				.andExpect(jsonPath("$.description").value(DESCRIPTION)).andReturn();
	}

	@Test
	public void thatUpdateSolutionWhenPassingSolutionInfo() throws Exception {
		int SOME_Solution_ID = 1;

		String content = new Gson().toJson(solution);
		this.mockMvc.perform(put(PATH + "/" + SOME_Solution_ID).contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(OK).andReturn();
	}

	@Test
	public void thatDeleteSolutionWhenRequestDeleteSolution() throws Exception {
		int SOME_Solution_ID = 1;
		this.mockMvc.perform(delete(PATH + "/" + SOME_Solution_ID).contentType(MediaType.APPLICATION_JSON)).andExpect(OK)
				.andReturn();
	}
}
