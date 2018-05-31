package com.cts.cde.io.cookbook.challenge.elasticsearch;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import com.cts.cde.io.cookbook.solution.elasticsearch.SolutionDocument;

import org.springframework.data.annotation.Id;

@Document(indexName = "cookbook", type = "challenge")

public class ChallengeDocument {

	public ChallengeDocument(String title, String description, Long platform_id, Long language_id,
			String tags) {

		this.title = title;
		this.description = description;
		this.platform_id = platform_id;
		this.language_id = language_id;
		this.tags = tags;

	}

	public ChallengeDocument() {
	}

	@Id
	private Long id;

	private String title;

	private String description;

	private Long platform_id;

	private Long language_id;

	private String tags;

	@Field(type = FieldType.Nested)
	private List<SolutionDocument> solution_docuemnt;

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

	public List<SolutionDocument> getSolutionDocuemnt() {
		return solution_docuemnt;
	}

	public void setSolutionDocuemnt(List<SolutionDocument> solution_docuemnt) {
		this.solution_docuemnt = solution_docuemnt;
	}
}
