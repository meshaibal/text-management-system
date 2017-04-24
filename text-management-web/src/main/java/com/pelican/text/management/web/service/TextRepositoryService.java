package com.pelican.text.management.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelican.text.management.persistence.model.Text;
import com.pelican.text.management.persistence.repository.TextRepository;

@Service
public class TextRepositoryService {

	private TextRepository textRepository;

	//Injecting dependency through constructor-injection
	@Autowired
	public TextRepositoryService(TextRepository textRepository) {
		this.textRepository = textRepository;
	}
	
	public Text addText(Text text, boolean isUpdate){
		if(!isUpdate && textRepository.findByTextTitle(text.getTextTitle()) != null){
			return null;
		}
		return textRepository.save(text);
	}
	
	public List<Text> getAllTextOrderByCreatedDate(){
		return textRepository.findAllByOrderByTextIdDesc();
	}
}
