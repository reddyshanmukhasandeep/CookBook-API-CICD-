package com.cts.cde.io.cookbook.challenge;

import java.util.List;

public interface ChallengeService {
	Challenge saveChallenge(Challenge challenge);

	Challenge updateChallenge(Challenge challenge, long id);

	void deleteChallenge(long id);

	Challenge findById(long id);

	List<Challenge> findAll();

	List<Challenge> searchTitle(String searchText);

}
