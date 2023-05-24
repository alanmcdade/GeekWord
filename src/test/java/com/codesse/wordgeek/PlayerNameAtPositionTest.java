package com.codesse.wordgeek;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PlayerNameAtPositionTest {

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
    public void testPlayerAtValidPosition() {
        assertEquals("player5", service.getPlayerNameAtPosition(0));
        assertEquals("player2", service.getPlayerNameAtPosition(1));
        assertEquals("player1", service.getPlayerNameAtPosition(2));
    }

    @Test
    public void testPlayerAtInvalidPosition() {
        assertNull(service.getPlayerNameAtPosition(9));
    }
}
