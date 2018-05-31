package com.cts.cde.io.cookbook.programminglanguage;

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

@RestController
@CrossOrigin
@RequestMapping(path = "/language")
public class ProgrammingLanguageController {

	@Autowired
	private ProgrammingLanguageService languageService;
	

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProgrammingLanguage>> getAllLanguage() {
		List<ProgrammingLanguage> languages = languageService.findAll();
		return new ResponseEntity<List<ProgrammingLanguage>>(languages,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/{id}")
	public ResponseEntity<ProgrammingLanguage> getLanguageById(@PathVariable("id") Long id) {
		ProgrammingLanguage language= languageService.findById(id);
		return new ResponseEntity<ProgrammingLanguage>(language,HttpStatus.OK);

	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ProgrammingLanguage> createLanguage(@RequestBody ProgrammingLanguage language) {

		ProgrammingLanguage languageData = languageService.createLanguage(language);
		return new ResponseEntity<ProgrammingLanguage>(languageData,HttpStatus.OK);
		

	}
	
	@RequestMapping(method = RequestMethod.PUT,value="/{id}")
	public ResponseEntity<ProgrammingLanguage> updateLanguage(@RequestBody ProgrammingLanguage language,@PathVariable("id") Long id) {

		ProgrammingLanguage languageData = languageService.updateLanguage(language,id);
		return new ResponseEntity<ProgrammingLanguage>(languageData,HttpStatus.OK);
		

	}

	@RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
	public ResponseEntity<String> deleteLanguage(@PathVariable("id") Long id) {
		languageService.deleteLanguage(id);
		return new ResponseEntity<String>("Language Deleted Successfully",HttpStatus.OK);
	}
}