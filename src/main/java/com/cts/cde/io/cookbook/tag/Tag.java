package com.cts.cde.io.cookbook.tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tag {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
	@Column(name = "NAME")
	private String name;
	

	public Tag(Long id, String name) {
		
		this.id = id;
		this.name = name;
	}
	

	public Tag() {
		
	}


	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
