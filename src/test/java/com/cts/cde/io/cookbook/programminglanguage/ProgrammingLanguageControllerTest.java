package com.cts.cde.io.cookbook.programminglanguage;


import com.cts.cde.io.cookbook.CookBookApplication;
import com.cts.cde.io.cookbook.platform.Platform;
import com.cts.cde.io.cookbook.platform.PlatformController;
import com.cts.cde.io.cookbook.platform.PlatformService;
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
@WebMvcTest(value = ProgrammingLanguageController.class, secure = false)
public class ProgrammingLanguageControllerTest {

    public static final String CONTENT_TYPE = "Content-type";
    public static final ResultMatcher OK = status().isOk();
    public static final ResultMatcher RESPONSE_FORMAT = content().contentType(MediaType.APPLICATION_JSON_UTF8);

    public static final String PATH = "/language";
   
    private static final String NAME = "java";


	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ProgrammingLanguageController controller;

	@MockBean
	ProgrammingLanguageService service;

	ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(1L, NAME);

/*	List<ProgrammingLanguage> list = new ArrayList() {
		{
			add(programmingLanguage);
		}
	};*/

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
		MockitoAnnotations.initMocks(this);
	}



	private ProgrammingLanguage getProgrammingLanguage() {

		ProgrammingLanguage src = new ProgrammingLanguage(1l, NAME);
		src.setId(1l);
		src.setLanguage(NAME);
		return src;
	}
	@Test
    public void thatGetListOfPlatformWhenRequestAllContent() throws Exception {


        List<ProgrammingLanguage> userList = new ArrayList();
        userList.add(programmingLanguage);
        given(service.findAll()).willReturn(userList);

        ResultMatcher hasRecord = jsonPath("$").isArray();
        MvcResult result = this.mockMvc.perform(get(PATH)
                .accept(MediaType.APPLICATION_JSON).header(CONTENT_TYPE,
                        MediaType.APPLICATION_JSON))
                .andExpect(OK).andExpect(RESPONSE_FORMAT)
                .andExpect(hasRecord).andReturn();

        String jsonString = result.getResponse().getContentAsString();
        DocumentContext context = JsonPath.parse(jsonString);
        int length = context.read("$.length()");
        assertThat(length, org.hamcrest.CoreMatchers.is(1));
    }
	
	  @Test
	    public void thatGetPlatformModelWhenPassingProgrammingLangugeId() throws Exception {

	        int SOME_PROGRAMMING_LANGUAGE_ID = 1;
	        given(service.findById(SOME_PROGRAMMING_LANGUAGE_ID)).willReturn(programmingLanguage);
	        MvcResult result = this.mockMvc.perform(get(PATH + "/" + SOME_PROGRAMMING_LANGUAGE_ID)
	                .accept(MediaType.APPLICATION_JSON)
	                .header(CONTENT_TYPE, MediaType.APPLICATION_JSON))
	                .andExpect(OK).andExpect(RESPONSE_FORMAT)
	                .andReturn();
	        String jsonString = result.getResponse().getContentAsString();
	        ProgrammingLanguage response = new Gson().fromJson(jsonString, ProgrammingLanguage.class);

	        assertThat(response, is(notNullValue()));
	    }

	@Test
    public void thatCreateNewProgrammingLanguageWhenPassingNewProgrammingLanguageInformation() throws Exception {
        given(service.createLanguage(Mockito.any(ProgrammingLanguage.class))).willReturn(programmingLanguage);
        String programminglanguage = new Gson().toJson(getProgrammingLanguage());
        this.mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(programminglanguage))
                .andExpect(OK)
                .andExpect(jsonPath("$.language").value(NAME))
                .andReturn();
    }
	
    @Test
    public void thatDeleteProgrammingLanguageWhenRequestDeleteProgrammingLanguage() throws Exception {
        int SOME_ID = 1;
        this.mockMvc.perform(delete(PATH + "/" + SOME_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(OK).andReturn();
    }
    
    @Test
    public void thatUpdateContentWhenPassingProgrammingLanguageInfo() throws Exception {
        int SOME_ID = 1;
        given(service.updateLanguage(Mockito.any(ProgrammingLanguage.class), Mockito.anyInt()))
                .willReturn(programmingLanguage);
        ProgrammingLanguage programmingLanguage = getProgrammingLanguage();
        assertThat(programmingLanguage.toString(), is(notNullValue()));
       

        String content = new Gson().toJson(programmingLanguage);
        this.mockMvc.perform(put(PATH + "/" + SOME_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(OK)
                .andExpect(jsonPath("$.language").value(NAME))
                .andReturn();
    }


}