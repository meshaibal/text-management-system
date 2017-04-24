package com.pelican.text.management.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.pelican.text.management.persistence.model.Text;
import com.pelican.text.management.web.service.TextRepositoryService;

@Controller
@RequestMapping(value = "/angular")
public class TextAngularController {

	private static final String WELCOME_VIEW_NAME = "welComeAngular" ;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showWelcomePage(){
		ModelAndView welcomeView = new ModelAndView(WELCOME_VIEW_NAME);
		return welcomeView;
	}
	
	@RequestMapping(value="/texts", method = RequestMethod.GET)
	public @ResponseBody String getAllTexts(){
		return GSON.toJson(textRepositoryService.getAllTextOrderByCreatedDate());
	}
	
	private static final Gson GSON = new Gson();
	
	private TextRepositoryService textRepositoryService;

	@Autowired
	public TextAngularController(TextRepositoryService textRepositoryService) {
		this.textRepositoryService = textRepositoryService;
	}
	
	@RequestMapping(value = "/save-text", method = RequestMethod.POST)
	public @ResponseBody String saveText(@RequestBody Text text){
		textRepositoryService.addText(text, true);
		return GSON.toJson(textRepositoryService.getAllTextOrderByCreatedDate());
	}
}
