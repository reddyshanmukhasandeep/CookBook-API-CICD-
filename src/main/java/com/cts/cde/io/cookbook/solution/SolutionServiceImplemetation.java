package com.cts.cde.io.cookbook.solution;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SolutionServiceImplemetation implements SolutionService {
	
	@Autowired
	SolutionRepository solutionRepository;
	
	@Override
	public List<Solution> findAll() {
		List<Solution> solutions= new ArrayList();
		 solutionRepository.findAll().forEach(solutions::add);
		 return solutions;
	}
	

	@Override
	public Solution findById(long id) {
		
		return solutionRepository.findOne(id);
	}

	@Override
	public Solution createSolution(Solution solution) {
		
		return solutionRepository.save(solution);
	}

	@Override
	public Solution updateSolution(Solution solution, long id) {
		
		return solutionRepository.save(solution);
	}

	@Override
	public void deleteSolution(long id) {
		
		solutionRepository.delete(id);
	}

	


}
