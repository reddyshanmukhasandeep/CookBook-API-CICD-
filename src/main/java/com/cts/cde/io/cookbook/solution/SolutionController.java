package com.cts.cde.io.cookbook.solution;

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
import com.cts.cde.io.cookbook.solution.elasticsearch.SolutionDocument;
import com.cts.cde.io.cookbook.solution.elasticsearch.SolutionDocumentService;

@RestController
@CrossOrigin
@RequestMapping(path = "/solution")
public class SolutionController {

	@Autowired
	private SolutionService solutionService;
	
	@Autowired
	private SolutionDocumentService solutionDocumentService;

	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Solution>> getAllSolution() {
		List<Solution> solutions = solutionService.findAll();
		return new ResponseEntity<List<Solution>>(solutions,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/{id}")
	public ResponseEntity<Solution> getSolutionById(@PathVariable("id") Long id) {
		
		Solution solution= solutionService.findById(id);
		return new ResponseEntity<Solution>(solution,HttpStatus.OK);

	}
	
	@RequestMapping(method = RequestMethod.GET,value="/search")
	public ResponseEntity<List<SolutionDocument>> getSolutionfromES() {
		
		List<SolutionDocument> solutions= solutionDocumentService.findAll();
		return new ResponseEntity<List<SolutionDocument>>(solutions,HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Solution> createSolution(@RequestBody Solution solution) {

		Solution solutionData=  solutionService.createSolution(solution);
		return new ResponseEntity<Solution>(solutionData,HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PUT,value= "/{id}")
	public ResponseEntity<Solution> updateSolution(@RequestBody Solution solution,@PathVariable("id") Long id) {

		Solution solutionData=  solutionService.updateSolution(solution,id);
		return new ResponseEntity<Solution>(solutionData,HttpStatus.OK);

	}

	

	@RequestMapping(method = RequestMethod.DELETE,value="/{id}")
	public ResponseEntity<String> deleteSolution(@PathVariable("id") Long id) {
		solutionService.deleteSolution(id);
		return new ResponseEntity<String>("Solution Deleted Successfully",HttpStatus.OK);

	}
}