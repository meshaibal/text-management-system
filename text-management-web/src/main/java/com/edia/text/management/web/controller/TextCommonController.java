package com.edia.text.management.web.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edia.text.management.persistence.model.Text;
import com.edia.text.management.web.service.TextRepositoryService;

@Controller
public class TextCommonController {

	private TextRepositoryService textRepositoryService;
	
	//Injecting dependency through constructor-injection
	@Autowired
	public TextCommonController(TextRepositoryService textRepositoryService) {
		this.textRepositoryService = textRepositoryService;
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
		text.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		text = textRepositoryService.addText(text);
		if(text != null && text.getTextId() != null){
			modelAndView.addObject("textAdded", true);
		}
		modelAndView.addObject("availableTextList", textRepositoryService.getAllTextOrderByCreatedDate());
		return modelAndView;
	}
	
	@RequestMapping(path = "/update-text", method = RequestMethod.POST)
	public ModelAndView updateText(@ModelAttribute("text")Text text){
		ModelAndView modelAndView = new ModelAndView("welCome");
		Long currentTextId = text.getTextId();
		text.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		text = textRepositoryService.addText(text);
		if(text != null && text.getTextId().equals(currentTextId)){
			modelAndView.addObject("textUpdated", true);
		}
		modelAndView.addObject("availableTextList", textRepositoryService.getAllTextOrderByCreatedDate());
		return modelAndView;
	}
}
