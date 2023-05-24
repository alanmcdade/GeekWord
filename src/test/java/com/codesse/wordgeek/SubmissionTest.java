package com.codesse.wordgeek;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Copyright (C) 2022 Codesse. All rights reserved.
 * ••••••••••••••••••••••••••••••••••••••••••••••••
 */
public class SubmissionTest {

	static ValidWords validWords;
	WordGeek service;

	@BeforeClass
	public static void oneTimeSetUp() {
		validWords = new ValidWordsImpl();
	}

	@Before
	public void setUp() throws Exception {
		service = new WordGeekImpl("areallylongword", validWords);
	}

	@Test
	public void testSubmission() throws Exception {
		assertEquals(3, service.submitWord("player1", "all"));
		assertEquals(4, service.submitWord("player2", "word"));
		assertEquals(0, service.submitWord("player3", "tale"));
		assertEquals(0, service.submitWord("player4", "glly"));
		assertEquals(6, service.submitWord("player5", "woolly"));
		assertEquals(0, service.submitWord("player6", "adder"));
	}

	@Test
	public void testWordAcceptedAndFound(){
		assertEquals(3, service.submitWord("player1", "all"));
		assertEquals(4, service.submitWord("player2", "word"));
		assertEquals(6, service.submitWord("player5", "woolly"));
	}

	@Test
	public void testWordAcceptedButNotFound(){
		assertEquals(0, service.submitWord("player6", "adder"));
		assertEquals(0, service.submitWord("player3", "tale"));
		assertEquals(0, service.submitWord("player4", "glly"));
	}

	@Test
	public void testWordNotAccepted(){
		assertEquals(0, service.submitWord("player6", "adder"));
		assertEquals(0, service.submitWord("player3", "tale"));
		assertEquals(0, service.submitWord("player4", "glly"));
	}

	@Test
	public void testNotAcceptedNoPlayer(){
		assertEquals(0, service.submitWord("", "all"));
		assertEquals(0, service.submitWord(" \t", "all"));
		assertEquals(0, service.submitWord(null, "all"));
	}

	@Test
	public void testNotAcceptedNoNoWord(){
		assertEquals(0, service.submitWord("player1", ""));
		assertEquals(0, service.submitWord("player1", null));
	}
}
