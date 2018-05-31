package com.cts.cde.io.cookbook.programminglanguage;

import java.util.List;

public interface ProgrammingLanguageService {

	ProgrammingLanguage createLanguage(ProgrammingLanguage language);

	ProgrammingLanguage updateLanguage(ProgrammingLanguage language, long id);

	void deleteLanguage(long id);

	ProgrammingLanguage findById(long id);

	List<ProgrammingLanguage> findAll();

}
