package com.cts.cde.io.cookbook.programminglanguage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProgrammingLanguage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "LANGUAGE")
	private String language;
	

	public ProgrammingLanguage(Long id, String language) {
		
		this.id = id;
		this.language = language;
	}


	public ProgrammingLanguage() {		
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
