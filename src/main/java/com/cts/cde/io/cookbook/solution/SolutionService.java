package com.cts.cde.io.cookbook.solution;

import java.util.List;

public interface SolutionService {

	Solution createSolution(Solution solution);

	Solution updateSolution(Solution solution, long id);

	void deleteSolution(long id);

	Solution findById(long id);

	List<Solution> findAll();

}