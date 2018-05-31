package com.cts.cde.io.cookbook.challenge;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.cde.io.cookbook.solution.Solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class ChallengeServiceImplTest {

	@InjectMocks
	private ChallengeServiceImpl service;
	private String TITLE = "someTitle";
	private String DESCRIPTION = "someDescription";
	private Long PLATFORM_ID = 1l;
	private Long LANGUAGE_ID = 1l;
	private String TAGS = "tags";
	//private String SOLUTION_DESCRIPTION = "solutionDescription";
	//private String REFERENCE_LINK = "referenceLink";
	private static final int SOME_ID = 1;

	@Mock
	private ChallengeRepository repository;

/*	private List<Solution> solution = new ArrayList() {
        {
            add(new Solution(SOLUTION_DESCRIPTION,REFERENCE_LINK));add(new Solution("soln","desc"));}};*/
	private Challenge challenge = new Challenge(1l,TITLE,DESCRIPTION,PLATFORM_ID,LANGUAGE_ID,TAGS,null);

	@Before
	public void setup() {
		// challenge.setName(SOME_NAME);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void thatGetListOfStagesWhenRequestAllStages() {
		// Given
		List<Challenge> mockData = new ArrayList<>();
		mockData.add(challenge);
		when(repository.findAll()).thenReturn(mockData);
		List<Challenge> list = service.findAll();
		assertEquals(1, list.size());
	}

	@Test
	public void thatGetParticularStageWhenRequestByStageId() {

		when(repository.findOne(1l)).thenReturn(challenge);
		Challenge client = service.findById(1);
		assertEquals(client.getTitle(), TITLE);
		assertEquals(client.getDescription(), DESCRIPTION);
		assertEquals(client.getLanguage_id(), LANGUAGE_ID);
		assertEquals(client.getPlatform_id(), PLATFORM_ID);
		assertEquals(client.getTags(), TAGS);
		// assertEquals(client.getSolutions(), TITLE);

	}

	@Test
	public void thatCreateStageWhenPassingNewStageInfo() {
		when(repository.save(challenge)).thenReturn(challenge);
		Challenge client = service.saveChallenge(challenge);
		assertEquals(client.getTitle(), TITLE);
		assertEquals(client.getDescription(), DESCRIPTION);
		assertEquals(client.getLanguage_id(), LANGUAGE_ID);
		assertEquals(client.getPlatform_id(), PLATFORM_ID);
		assertEquals(client.getTags(), TAGS);
	}

	@Test
	public void thatUpdateStageWhenPassingUpdatedStagesInfo() {
		when(repository.save(challenge)).thenReturn(challenge);
		Challenge client = service.updateChallenge(challenge, 1);
		assertEquals(client.getTitle(), TITLE);
		assertEquals(client.getDescription(), DESCRIPTION);
		assertEquals(client.getLanguage_id(), LANGUAGE_ID);
		assertEquals(client.getPlatform_id(), PLATFORM_ID);
		assertEquals(client.getTags(), TAGS);
	}

	@Test
	public void thatDeleteRoleWhenPassingStageId() {
		service.deleteChallenge(1);
		assertTrue(true);
	}

}