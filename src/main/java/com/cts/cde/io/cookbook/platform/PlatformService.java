package com.cts.cde.io.cookbook.platform;

import java.util.List;

public interface PlatformService {

	Platform createPlatform(Platform platform);

	Platform updatePlatform(Platform platform, long id);

	void deletePlatform(long id);

	Platform findById(long id);

	List<Platform> findAll();

	}
