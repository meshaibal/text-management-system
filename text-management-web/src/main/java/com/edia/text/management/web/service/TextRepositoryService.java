package com.edia.text.management.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edia.text.management.persistence.model.Text;
import com.edia.text.management.persistence.repository.TextRepository;

@Service
public class TextRepositoryService {

	private TextRepository textRepository;

	//Injecting dependency through constructor-injection
	@Autowired
	public TextRepositoryService(TextRepository textRepository) {
		this.textRepository = textRepository;
	}
	
	public Text addText(Text text){
		return textRepository.save(text);
	}
	
	public List<Text> getAllTextOrderByCreatedDate(){
		return textRepository.findAllByOrderByCreatedDateDesc();
	}
}
