package com.cts.cde.io.cookbook.solution.elasticsearch;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.annotation.Id;

@Document(indexName = "cookbook", type = "solution")
public class SolutionDocument {

	@Id
	private Long id;
	
	private String description;
	
	private String reference_link;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReference_link() {
		return reference_link;
	}
	public void setReference_link(String reference_link) {
		this.reference_link = reference_link;
	}
}
