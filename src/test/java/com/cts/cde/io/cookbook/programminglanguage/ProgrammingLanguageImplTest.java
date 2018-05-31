package com.cts.cde.io.cookbook.programminglanguage;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProgrammingLanguageImplTest {

	private String SOME_NAME = "somename";
	private static final Long SOME_ID = 1l;
	@InjectMocks
	ProgrammingLanguageServiceImpl programmingLanguageServiceImpl;

	@Mock
	ProgrammingLanguageRepository programmingLanguageRepository;

	private ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(1l, SOME_NAME);

	@Before
	public void setup() {
		programmingLanguage.setLanguage(SOME_NAME);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void thatGetListOfLanguagesWhenRequestAllLanguages() {
		// Given
		List<ProgrammingLanguage> mockData = new ArrayList<>();
		mockData.add(programmingLanguage);
		when(programmingLanguageRepository.findAll()).thenReturn(mockData);
		List<ProgrammingLanguage> list = programmingLanguageServiceImpl.findAll();
		assertEquals(1, list.size());
	}


	@Test
	public void thatGetParticularLanguageWhenRequestByLanguagesId() {

		when(programmingLanguageRepository.findOne(SOME_ID)).thenReturn(programmingLanguage);
		ProgrammingLanguage client = programmingLanguageServiceImpl.findById(SOME_ID);
		assertEquals(client.getLanguage(), SOME_NAME);
	}

	@Test
	public void thatCreateLanguagesWhenPassingLanguagesInfo() {
		when(programmingLanguageRepository.save(programmingLanguage)).thenReturn(programmingLanguage);
		ProgrammingLanguage p = programmingLanguageServiceImpl.createLanguage(programmingLanguage);
		assertEquals(p.getLanguage(), SOME_NAME);
	}

	@Test
	public void thatUpdateLanguagesWhenPassingUpdatedLanguagesInfo() {
		when(programmingLanguageRepository.save(programmingLanguage)).thenReturn(programmingLanguage);
		ProgrammingLanguage client = programmingLanguageServiceImpl.updateLanguage(programmingLanguage, 1);
		assertEquals(client.getLanguage(), SOME_NAME);
	}

	@Test
	public void thatDeleteLanguagesWhenPassingLanguagesId() {
		programmingLanguageServiceImpl.deleteLanguage(1);
		assertTrue(true);
	}

}