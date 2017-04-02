package com.edia.text.management.persistence.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
@SequenceGenerator(name = "seqGen", sequenceName = "text_id", allocationSize = 1)
public class Text {

	@Id
	@Column(name = "text_id")
	@GeneratedValue(generator = "seqGen", strategy = GenerationType.SEQUENCE)
	private Long textId;
	
	@Column(name = "text_title")
	private String textTitle;
	
	@Column(name = "text_content")
	private String textContent;

	@Column(name = "created_date")
	private Timestamp createdDate;
	
	@Column(name = "difficulty_level")
	private Integer difficultyLevel;
	
	public Text() {
	}

	public Long getTextId() {
		return textId;
	}

	public void setTextId(Long textId) {
		this.textId = textId;
	}

	public String getTextTitle() {
		return textTitle;
	}

	public void setTextTitle(String textTitle) {
		this.textTitle = textTitle;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(Integer difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	
	
	
}
