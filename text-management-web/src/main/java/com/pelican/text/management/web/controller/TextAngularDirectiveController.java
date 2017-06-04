package com.pelican.text.management.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.pelican.text.management.web.service.TextRepositoryService;

@Controller
@RequestMapping(value = "/angular-directive")
public class TextAngularDirectiveController {

	private static final String WELCOME_VIEW_NAME = "welComeAngularDirective" ;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showWelcomePage(){
		ModelAndView welcomeView = new ModelAndView(WELCOME_VIEW_NAME);
		return welcomeView;
	}
	
	@RequestMapping(value = "/texts", method = RequestMethod.GET)
	public @ResponseBody String getAllTexts(){
		return GSON.toJson(textRepositoryService.getAllTextOrderByCreatedDate());
	}
	
	@RequestMapping(value = "/texts/{textId}", method = RequestMethod.GET)
	public @ResponseBody String getTextById(@PathVariable("textId") Long textId){
		return GSON.toJson(textRepositoryService.getTextByTextId(textId));
	}
	
	private static final Gson GSON = new Gson();
	
	private TextRepositoryService textRepositoryService;

	@Autowired
	public TextAngularDirectiveController(TextRepositoryService textRepositoryService) {
		this.textRepositoryService = textRepositoryService;
	}
	
}
