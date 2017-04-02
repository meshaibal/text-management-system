package com.edia.text.management.web.service;

import org.mockito.InjectMocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DetermineTextDifficultyLevelTest {

	@InjectMocks
	DetermineTextDifficultyLevel determineTextDifficultyLevel = new DetermineTextDifficultyLevel();
	
	/**
	 * If the Text has only letters, Then it's of difficulty level 1 (Ignoring space, newline, tab)
	 */
	@Test
	public void checkForTextDifficultyLevelOne(){
		assertEquals(1, determineTextDifficultyLevel.getTextDifficultyLevel("This Is Sample Text To Test Difficulty Level One").intValue());
	}
	
	/**
	 * If the Text has only letters and numbers Then it's of difficulty level 2 (Ignoring space, newline, tab)
	 */
	@Test
	public void checkForTextDifficultyLevelTwo(){
		assertEquals(2, determineTextDifficultyLevel.getTextDifficultyLevel("This Is Sample Text To Test Difficulty Level 2").intValue());
	}
	
	/**
	 * If the Text has letters , numbers , special characters, Then it's of difficulty level 3 (Ignoring space, newline, tab)
	 */
	@Test
	public void checkForTextDifficultyLevelThree(){
		assertEquals(3, determineTextDifficultyLevel.getTextDifficultyLevel("This Is Sample Text To Test Difficulty Level 3 !").intValue());
	}
}
