package com.cts.cde.io.cookbook.challenge.elasticsearch;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ChallengeDocumentRepository extends ElasticsearchRepository<ChallengeDocument,Long> {
	List<ChallengeDocument> findByTitleLike(String searchtext);
	List<ChallengeDocument> findByTitle(String searchtext);
	List<ChallengeDocument> findByTagsLike(String tags);

}
