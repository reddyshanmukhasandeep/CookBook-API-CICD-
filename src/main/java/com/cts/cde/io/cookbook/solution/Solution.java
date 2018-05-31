package com.cts.cde.io.cookbook.solution;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.cts.cde.io.cookbook.challenge.Challenge;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Solution {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
	@Column(name = "DESCRIPTION",length=2500)
	private String description;
	
	@Column(name = "REFERENCE_LINK",length=2500)
	private String reference_link;
	
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "challenge_id")
    @JsonBackReference
    private Challenge challenge;
	
	public Solution(Long id, String description, String reference_link) {
		this.id = id;
		this.description = description;
		this.reference_link = reference_link;
		
	}
	
	public Solution( String description, String reference_link) {
		this.description = description;
		this.reference_link = reference_link;
		
	}
	
	public Solution()
	{
		
	}

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

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}


	
}
