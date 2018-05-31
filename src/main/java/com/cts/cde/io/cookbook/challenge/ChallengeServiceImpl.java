package com.cts.cde.io.cookbook.challenge;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ChallengeServiceImpl implements ChallengeService {

	 @Autowired
	    ChallengeRepository challengeRepository;
	 
	   @Override
		public List<Challenge> findAll() {		
		  List<Challenge> challenges = new ArrayList();
		  challengeRepository.findAll().forEach(challenges::add);
		  return challenges;
		}
	 
	   @Override
		public Challenge findById(long id) {
			
		 return challengeRepository.findOne(id);
		}
	 
		@Override
		public Challenge saveChallenge(Challenge challenge) {
			
			return challengeRepository.save(challenge);
		}
	
		@Override
		public Challenge updateChallenge(Challenge challenge, long id) {
			
			return challengeRepository.save(challenge);
		}
	
		@Override
		public void deleteChallenge(long id) {
			
			challengeRepository.delete(id);
		}
		@Override
		public List<Challenge> searchTitle(String text) {
			
			return challengeRepository.findByTitleContaining(text);
		
		}
}