package com.cts.cde.io.cookbook.challenge.elasticsearch;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.cts.cde.io.cookbook.challenge.Challenge;
import com.cts.cde.io.cookbook.solution.Solution;
import com.cts.cde.io.cookbook.solution.elasticsearch.SolutionDocument;;


@Service
public class ChallengeDocumentService {
	
	@Autowired
	private ChallengeDocumentRepository challengeDocumentRepository;

	public void  saveChallenge(Challenge challengeData) {
		
		ChallengeDocument challengeDocument =new ChallengeDocument() ;
		challengeDocument.setDescription(challengeData.getDescription());
		challengeDocument.setId(challengeData.getId());
		challengeDocument.setLanguage_id(challengeData.getLanguage_id());
		challengeDocument.setPlatform_id(challengeData.getPlatform_id());
		challengeDocument.setTags(challengeData.getTags());
		challengeDocument.setTitle(challengeData.getTitle());
		
		List<SolutionDocument> solutionDocumentList = new ArrayList();
		for(Solution s : challengeData.getSolutions()) {
			SolutionDocument solDoc = new SolutionDocument();
			solDoc.setId(s.getId());
			solDoc.setDescription(s.getDescription());
			solDoc.setReference_link(s.getReference_link());
			solutionDocumentList.add(solDoc);
		}
		challengeDocument.setSolutionDocuemnt(solutionDocumentList);
		 challengeDocumentRepository.save(challengeDocument);
		
	}

	public List<ChallengeDocument> findAll() {
		List<ChallengeDocument> challenges = new ArrayList();
		challengeDocumentRepository.findAll().forEach(challenges::add);
		  return challenges;
	}
	
	public List<ChallengeDocument> searchwithTitle(String searchText)
	{
		
		return challengeDocumentRepository.findByTitleLike(searchText);
		 
	}
	
	public List<ChallengeDocument> searchwithFullText(String searchText)
	{
		
		return challengeDocumentRepository.findByTitle(searchText);
		 
	}
	
	public List<ChallengeDocument> searchwithTag(String tags)
	{
		
		return challengeDocumentRepository.findByTagsLike(tags);
		 
	}

}
