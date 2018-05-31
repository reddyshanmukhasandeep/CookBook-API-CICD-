package com.cts.cde.io.cookbook.solution.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SolutionDocumentRepository extends ElasticsearchRepository<SolutionDocument,Long> {

}
