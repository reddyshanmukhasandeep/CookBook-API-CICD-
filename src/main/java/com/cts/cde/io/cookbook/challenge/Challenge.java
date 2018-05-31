package com.cts.cde.io.cookbook.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.cts.cde.io.cookbook.solution.Solution;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Challenge {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;	
	
	@Column(name = "TITLE",length=2500)
	private String title;
	
	@Column(name = "DESCRIPTION",length=2500)
	private String description;
	
	@Column(name = "PLATFORM_ID")
	private Long platform_id;
	
	@Column(name = "LANGUAGE_ID")
	private Long language_id;
	
	@Column(name = "TAGS")
	private String tags;
	
	 @OneToMany(
	            mappedBy = "challenge",
	            cascade = CascadeType.PERSIST,
	            fetch = FetchType.LAZY,
	            orphanRemoval = true)
	    @JsonManagedReference    
	   
	    private List<Solution> solutions ;	
	

	public Challenge(Long id, String title, String description, Long platform_id, Long language_id, String tags,List<Solution> solutions) {
		
		this.id = id;
		this.title = title;
		this.description = description;
		this.platform_id = platform_id;
		this.language_id = language_id;
		this.tags = tags;
		this.solutions=solutions;
	}
	
	public Challenge( String title, String description, Long platform_id, Long language_id, String tags) {		
		this.title = title;
		this.description = description;
		this.platform_id = platform_id;
		this.language_id = language_id;
		this.tags = tags;		
	}
	
	public Challenge() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(Long platform_id) {
		this.platform_id = platform_id;
	}

	public Long getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(Long language_id) {
		this.language_id = language_id;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public List<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}
	

}
