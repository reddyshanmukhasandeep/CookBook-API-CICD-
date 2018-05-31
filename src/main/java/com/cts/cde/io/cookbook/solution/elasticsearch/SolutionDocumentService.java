package com.cts.cde.io.cookbook.solution.elasticsearch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionDocumentService {

	@Autowired
	private SolutionDocumentRepository solutionDocumentRepository;
	
	public List<SolutionDocument> findAll() {
		List<SolutionDocument> solutions = new ArrayList();
		solutionDocumentRepository.findAll().forEach(solutions::add);
		return solutions;
	}

}
