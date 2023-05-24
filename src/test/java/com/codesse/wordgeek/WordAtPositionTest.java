package com.codesse.wordgeek;

import com.codesse.scoreboard.ScoreBoard;
import com.codesse.scoreboard.SimpleFixedScoreBoard;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class WordAtPositionTest {
    static ValidWords validWords;
    WordGeek service;
    ScoreBoard scoreBoard = new SimpleFixedScoreBoard();

    @BeforeClass
    public static void oneTimeSetUp() {
        validWords = new ValidWordsImpl();
    }

    @Before
    public void setUp() throws Exception {
        service = new WordGeekImpl("areallylongword", validWords, scoreBoard);
        service.submitWord("player1", "all");
        service.submitWord("player2", "word");
        service.submitWord("player3", "tale");
        service.submitWord("player4", "glly");
        service.submitWord("player5", "woolly");
    }

    @Test
    public void testGetWordAtPosition(){
        assertEquals("woolly", service.getWordEntryAtPosition(0));
        assertEquals("word", service.getWordEntryAtPosition(1));
        assertEquals("all", service.getWordEntryAtPosition(2));
    }

    @Test
    public void testGetWordAtInavlidPosition(){
        assertNull(service.getWordEntryAtPosition(9));
    }
}
