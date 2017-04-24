package com.pelican.text.management.web.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pelican.text.management.persistence.model.Text;
import com.pelican.text.management.web.service.DetermineTextDifficultyLevel;
import com.pelican.text.management.web.service.TextRepositoryService;

@Controller
public class TextCommonController {

	private TextRepositoryService textRepositoryService;
	private DetermineTextDifficultyLevel determineTextDifficultyLevel;
	
	//Injecting dependency through constructor-injection
	@Autowired
	public TextCommonController(TextRepositoryService textRepositoryService, DetermineTextDifficultyLevel determineTextDifficultyLevel) {
		this.textRepositoryService = textRepositoryService;
		this.determineTextDifficultyLevel = determineTextDifficultyLevel;
	}


	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView getWelComePage(){
		ModelAndView modelAndView = new ModelAndView("welCome");
		modelAndView.addObject("welcomeMessage", "Welcome to Text Mangement System");
		return modelAndView;
	}
	
	@RequestMapping(path = "/add-text", method = RequestMethod.POST)
	public ModelAndView saveText(@ModelAttribute("text")Text text){
		ModelAndView modelAndView = new ModelAndView("welCome");
		//text.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		text.setDifficultyLevel(determineTextDifficultyLevel.getTextDifficultyLevel(text.getTextContent()));
		
		text = textRepositoryService.addText(text, false);
		
		if(text != null && text.getTextId() != null){
			modelAndView.addObject("textAdded", true);
		}else{
			modelAndView.addObject("isExists", true);
		}
		modelAndView.addObject("availableTextList", textRepositoryService.getAllTextOrderByCreatedDate());
		return modelAndView;
	}
	
	@RequestMapping(path = "/update-text", method = RequestMethod.POST)
	public ModelAndView updateText(@ModelAttribute("text")Text text){
		ModelAndView modelAndView = new ModelAndView("welCome");
		Long currentTextId = text.getTextId();
		//text.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		text.setDifficultyLevel(determineTextDifficultyLevel.getTextDifficultyLevel(text.getTextContent()));
		
		text = textRepositoryService.addText(text, true);
		
		if(text != null && text.getTextId().equals(currentTextId)){
			modelAndView.addObject("textUpdated", true);
		}
		modelAndView.addObject("availableTextList", textRepositoryService.getAllTextOrderByCreatedDate());
		return modelAndView;
	}
}
