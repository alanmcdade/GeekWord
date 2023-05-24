package com.codesse.wordgeek;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ScoreAtPositionTest {
    static ValidWords validWords;
    WordGeek service;

    @BeforeClass
    public static void oneTimeSetUp() {
        validWords = new ValidWordsImpl();
    }

    @Before
    public void setUp() throws Exception {
        service = new WordGeekImpl("areallylongword", validWords);
        service.submitWord("player1", "all");
        service.submitWord("player2", "word");
        service.submitWord("player3", "tale");
        service.submitWord("player4", "glly");
        service.submitWord("player5", "woolly");
    }

    @Test
    public void testScoreAtValidPosition() {
        assertEquals(Integer.valueOf(6), service.getScoreAtPosition(0));
        assertEquals(Integer.valueOf(4), service.getScoreAtPosition(1));
        assertEquals(Integer.valueOf(3), service.getScoreAtPosition(2));
    }

    @Test
    public void testScoreAtInvalidPosition() {
        assertNull(service.getScoreAtPosition(9));
    }
}
