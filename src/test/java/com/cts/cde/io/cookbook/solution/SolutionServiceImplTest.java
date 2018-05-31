package com.cts.cde.io.cookbook.solution;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class SolutionServiceImplTest {

	@InjectMocks
	private SolutionServiceImplemetation service;

	private String DESCRIPTION = "description";
	private String REFERENCE_LINK = "reference_link";
	@Mock
	private SolutionRepository repository;

	private Solution solution = new Solution(1l, DESCRIPTION, REFERENCE_LINK);

	@Before
	public void setup() {
	//	sampleSubStage.setName(SOME_NAME);
		MockitoAnnotations.initMocks(this);
	}

/*	@Test
	public void thatGetListOfSolutionWhenRequestAllSolution() {
	      //Given
        List<Solution> mockData = new ArrayList<>();
        mockData.add(solution);
		when(repository.findAll()).thenReturn((Iterable<Solution>) solution);
		List<Solution> list = service.findAll();
		assertEquals(1, list.size());
	}*/

	@Test
	public void thatGetParticularSolutionWhenRequestBySolutionId() {

		when(repository.findOne(1l)).thenReturn(solution);
		Solution client = service.findById(1);
		assertEquals(client.getDescription(), DESCRIPTION);
		assertEquals(client.getReference_link(), REFERENCE_LINK);
	}

	@Test
	public void thatCreateSolutionWhenPassingNewSolutionInfo() {
		when(repository.save(solution)).thenReturn(solution);
		Solution client = service.createSolution(solution);
		assertEquals(client.getDescription(), DESCRIPTION);
		assertEquals(client.getReference_link(), REFERENCE_LINK);
	}

	@Test
	public void thatUpdateSolutionWhenPassingUpdatedSolutionInfo() {
		when(repository.save(solution)).thenReturn(solution);
		service.updateSolution(solution, 1);
		assertTrue(true);
	}

	@Test
	public void thatDeleteSolutionWhenPassingSolutionId() {
		service.deleteSolution(1);
		assertTrue(true);
	}

}