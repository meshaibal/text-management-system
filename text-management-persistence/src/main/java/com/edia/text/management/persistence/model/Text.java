package com.edia.text.management.persistence.model;

import java.sql.Date;

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
	private String textId;
	
	@Column(name = "text_title")
	private String textTitle;
	
	@Column(name = "text_content")
	private String textContent;

	@Column(name = "created_date")
	private Date createdDate;
	
	public Text() {
	}

	public String getTextId() {
		return textId;
	}

	public void setTextId(String textId) {
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
}
