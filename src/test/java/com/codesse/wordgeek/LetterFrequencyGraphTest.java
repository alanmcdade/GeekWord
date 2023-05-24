package com.codesse.wordgeek;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class LetterFrequencyGraphTest extends TestCase {
    private final static String BASE_LETTERS = "areallylongword";

    LetterFrequencyGraph letterFrequencyGraph;

    @Before
    public void setUp() {
        letterFrequencyGraph = new LetterFrequencyGraph(BASE_LETTERS);
    }

    @Test
    public void testSingleLetters() {
        assertEquals(1, letterFrequencyGraph.howManyOf('d'));
    }

    @Test
    public void testMulipleLetters() {
        assertEquals(2, letterFrequencyGraph.howManyOf('o'));
    }

    @Test
    public void testMissingLetters() {
        assertEquals(0, letterFrequencyGraph.howManyOf('c'));
    }

    @Test
    public void testContainsAllMatching() {
        LetterFrequencyGraph expected = new LetterFrequencyGraph("all");
        assertTrue(letterFrequencyGraph.containsAll(expected));
    }

    @Test
    public void testContainsAllNotMatching() {
        LetterFrequencyGraph expected = new LetterFrequencyGraph("tale");
        assertFalse(letterFrequencyGraph.containsAll(expected));
    }

    @Test
    public void testContainsAllEmpty() {
        LetterFrequencyGraph expected = new LetterFrequencyGraph("");
        assertTrue(letterFrequencyGraph.containsAll(expected));
    }

}