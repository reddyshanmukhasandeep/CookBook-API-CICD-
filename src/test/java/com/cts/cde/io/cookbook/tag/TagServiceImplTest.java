package com.cts.cde.io.cookbook.tag;



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

public class TagServiceImplTest {

	private String SOME_NAME = "somename";
	private static final Long SOME_ID = 1l;
	@InjectMocks
	TagServiceImpl tagServiceImpl;

	@Mock
	TagRepository tagRepository;

	private Tag tag = new Tag(1l, SOME_NAME);

	@Before
	public void setup() {
		tag.setName(SOME_NAME);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void thatGetListOfTagWhenRequestAllTag() {
		// Given
		List<Tag> mockData = new ArrayList<>();
		mockData.add(tag);
		when(tagRepository.findAll()).thenReturn(mockData);
		List<Tag> list = tagServiceImpl.findAll();
		assertEquals(1, list.size());
	}


	@Test
	public void thatGetParticularTagWhenRequestByTagId() {

		when(tagRepository.findOne(SOME_ID)).thenReturn(tag);
		Tag client = tagServiceImpl.findById(SOME_ID);
		assertEquals(client.getName(), SOME_NAME);
	}

	@Test
	public void thatCreateTagWhenPassingNewTagInfo() {
		when(tagRepository.save(tag)).thenReturn(tag);
		Tag p = tagServiceImpl.createTag(tag);
		assertEquals(p.getName(), SOME_NAME);
	}

	@Test
	public void thatUpdateTagWhenPassingUpdatedTagInfo() {
		when(tagRepository.save(tag)).thenReturn(tag);
		Tag client = tagServiceImpl.updateTag(tag, 1);
		assertEquals(client.getName(), SOME_NAME);
	}

	@Test
	public void thatDeleteTagWhenPassingTagId() {
		tagServiceImpl.deleteTag(1);
		assertTrue(true);
	}

}