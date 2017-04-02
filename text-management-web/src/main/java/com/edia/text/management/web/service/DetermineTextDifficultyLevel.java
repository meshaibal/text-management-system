package com.edia.text.management.web.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DetermineTextDifficultyLevel {

	private static final String REGEX_TO_VALIDATE_ONLY_LETTER = "[^a-zA-Z]+" ;
	private static final String REGEX_TO_VALIDATE_ONLY_LETTER_AND_NUMBER = "[^a-zA-Z0-9]+" ;
	
	
	public Integer getTextDifficultyLevel(String textContent){
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
	
	private boolean validateRegex(String patternToMatch, String text){
		 Pattern pattern = Pattern.compile(patternToMatch);
	     Matcher matcher = pattern.matcher(text);

	     return matcher.find();
	}

}
