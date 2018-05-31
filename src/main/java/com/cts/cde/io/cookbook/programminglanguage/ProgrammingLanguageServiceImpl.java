package com.cts.cde.io.cookbook.programminglanguage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProgrammingLanguageServiceImpl implements ProgrammingLanguageService {

	@Autowired
	ProgrammingLanguageRepository languageRepository;
	
	
	@Override
	public List<ProgrammingLanguage> findAll() {
		List<ProgrammingLanguage> languages = new ArrayList();
		 languageRepository.findAll().forEach(languages::add);
		 return languages;
	}
	
	@Override
	public ProgrammingLanguage findById(long id) {
		
		return languageRepository.findOne(id);
	}


	@Override
	public ProgrammingLanguage createLanguage(ProgrammingLanguage language) {
		
		return languageRepository.save(language);
	}

	@Override
	public ProgrammingLanguage updateLanguage(ProgrammingLanguage language, long id) {
		
		return languageRepository.save(language);
	}

	@Override
	public void deleteLanguage(long id) {
		
		languageRepository.delete(id);
	}

	

}
