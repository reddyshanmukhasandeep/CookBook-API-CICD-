package com.cts.cde.io.cookbook.challenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.cde.io.cookbook.challenge.elasticsearch.ChallengeDocument;
import com.cts.cde.io.cookbook.challenge.elasticsearch.ChallengeDocumentService;

@RestController
@CrossOrigin
@RequestMapping(path = "/challenge")
public class ChallengeController {

	@Autowired
	private ChallengeService challengeService;

	@Autowired
	private ChallengeDocumentService challengeDocumentService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Challenge>> getAllChallenge() {
		List<Challenge> challenges = challengeService.findAll();
		return new ResponseEntity<List<Challenge>>(challenges, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(method=RequestMethod.GET,value="/search/{text}") public
	 * ResponseEntity<List<ChallengeDocument>> getChallenge(@PathVariable("text")
	 * String searchText) { List<ChallengeDocument> challenges=
	 * challengeDocumentService.searchwithTitle(searchText); return new
	 * ResponseEntity<List<ChallengeDocument>>(challenges,HttpStatus.OK); }
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/search/{text}")
	public ResponseEntity<List<Challenge>> getChallenge(@PathVariable("text") String searchText) {
		List<Challenge> challenges = challengeService.searchTitle(searchText);
		return new ResponseEntity<List<Challenge>>(challenges, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/title/{text}")
	public ResponseEntity<List<ChallengeDocument>> searchChallenge(@PathVariable("text") String searchText) {
		List<ChallengeDocument> challenges = challengeDocumentService.searchwithFullText(searchText);
		return new ResponseEntity<List<ChallengeDocument>>(challenges, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/tags/{text}")
	public ResponseEntity<List<ChallengeDocument>> searchWithTags(@PathVariable("text") String searchText) {
		List<ChallengeDocument> challenges = challengeDocumentService.searchwithTag(searchText);
		return new ResponseEntity<List<ChallengeDocument>>(challenges, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Challenge> getChallengeById(@PathVariable("id") Long id) {

		Challenge challengeData = challengeService.findById(id);
		return new ResponseEntity<Challenge>(challengeData, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Challenge> createChallenge(@RequestBody Challenge challenge) {

		Challenge challengeData = challengeService.saveChallenge(challenge);
		if (challengeData.getId() != null) {
			challengeDocumentService.saveChallenge(challengeData);
		}
		return new ResponseEntity<Challenge>(challengeData, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Challenge> updateChallenge(@RequestBody Challenge challenge, @PathVariable("id") Long id) {

		Challenge challengeData = challengeService.updateChallenge(challenge, id);
		return new ResponseEntity<Challenge>(challengeData, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deleteChallenge(@PathVariable("id") Long id) {
		challengeService.deleteChallenge(id);
		return new ResponseEntity<String>("Challenge Deleted Successfully", HttpStatus.OK);

	}
}