package com.edia.text.management.web.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DetermineTextDifficulty {

	private static final String REGEX_TO_VALIDATE_ONLY_LETTER = "[^a-zA-Z]+" ;
	private static final String REGEX_TO_VALIDATE_ONLY_LETTER_AND_NUMBER = "[^a-zA-Z0-9]+" ;
	
	
	public Integer getTextDifficulty(String textContent){
		if(StringUtils.isEmpty(textContent)){
			return -1;
		}
		textContent = textContent.replaceAll("\\s+","").replaceAll("\n", "").replaceAll("\r", "");
		boolean containsOnlyLetter = validateRegex(REGEX_TO_VALIDATE_ONLY_LETTER, textContent);	
		if(!containsOnlyLetter){
			return 1;
		}
		
		boolean containsOnlyLetterAndNumber = validateRegex(REGEX_TO_VALIDATE_ONLY_LETTER_AND_NUMBER, textContent);
		if(!containsOnlyLetterAndNumber){
			return 2;
		}
		
		return 3;
	}
	
	private boolean validateRegex(String pattern, String text){
		Pattern p = Pattern.compile(pattern);
	     Matcher m = p.matcher(text);

	     return m.find();
	}
	
	public static void main(String[] args) {//[^a-zA-Z]+     [^a-zA-Z0-9]+
		DetermineTextDifficulty d = new DetermineTextDifficulty();
		System.out.println(d.getTextDifficulty("wqwqeqwew!"));
	}
}
