package com.cts.cde.io.cookbook.platform;

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

public class PlatformServiceImplTest {

	private String SOME_NAME = "somename";
	private static final Long SOME_ID = 1l;
	@InjectMocks
	PlatformServiceImpl platformService;

	@Mock
	PlatformRepository platformRepository;

	private Platform platform = new Platform(1l, SOME_NAME);

	@Before
	public void setup() {
		platform.setName(SOME_NAME);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void thatGetListOfStagesWhenRequestAllStages() {
		// Given
		List<Platform> mockData = new ArrayList<>();
		mockData.add(platform);
		when(platformRepository.findAll()).thenReturn(mockData);
		List<Platform> list = platformService.findAll();
		assertEquals(1, list.size());
	}


	@Test
	public void thatGetParticularStageWhenRequestByStageId() {

		when(platformRepository.findOne(SOME_ID)).thenReturn(platform);
		Platform client = platformService.findById(SOME_ID);
		assertEquals(client.getName(), SOME_NAME);
	}

	@Test
	public void thatCreateStageWhenPassingNewStageInfo() {
		when(platformRepository.save(platform)).thenReturn(platform);
		Platform p = platformService.createPlatform(platform);
		assertEquals(p.getName(), SOME_NAME);
	}

	@Test
	public void thatUpdateStageWhenPassingUpdatedStagesInfo() {
		when(platformRepository.save(platform)).thenReturn(platform);
		Platform client = platformService.updatePlatform(platform, 1);
		assertEquals(client.getName(), SOME_NAME);
	}

	@Test
	public void thatDeleteRoleWhenPassingStageId() {
		platformService.deletePlatform(1);
		assertTrue(true);
	}

}