package com.edia.text.management.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TextCommonController {

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView getWelComePage(){
		ModelAndView modelAndView = new ModelAndView("welCome");
		modelAndView.addObject("welcomeMessage", "Welcome to Text Mangement System");
		return modelAndView;
	}
}
