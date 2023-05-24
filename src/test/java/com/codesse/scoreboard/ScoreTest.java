package com.codesse.scoreboard;

import junit.framework.TestCase;
import org.junit.Test;

import java.time.Clock;
import java.time.LocalDateTime;

public class ScoreTest extends TestCase {

    @Test
    public void testCompareToWithHigherScore() {
        Score firstScore = new Score("test player 1","word", 4, LocalDateTime.now(Clock.systemUTC()));
        Score secondScore = new Score("test player 2","longword", 8, LocalDateTime.now(Clock.systemUTC()));

        assertTrue(firstScore.compareTo(secondScore) > 0);
    }

    @Test
    public void testCompareToWithLowerScore() {
        Score firstScore = new Score("test player 1","longword", 8, LocalDateTime.now(Clock.systemUTC()));
        Score secondScore = new Score("test player 2","word", 4, LocalDateTime.now(Clock.systemUTC()));

        assertTrue(firstScore.compareTo(secondScore) < 0);
    }

    @Test
    public void testCompareToWithSameScoreEarlier() {
        Score firstScore = new Score("test player 1","longword", 8, LocalDateTime.now(Clock.systemUTC()));
        Score secondScore = new Score("test player 2","longword", 8, LocalDateTime.now(Clock.systemUTC()).minusDays(1));

        assertTrue(firstScore.compareTo(secondScore) < 0);
    }

    @Test
    public void testCompareToWithSameScoreLater() {
        Score firstScore = new Score("test player 1","word", 4, LocalDateTime.now(Clock.systemUTC()));
        Score secondScore = new Score("test player 2","word", 4, LocalDateTime.now(Clock.systemUTC()).plusDays(1));

        assertTrue(firstScore.compareTo(secondScore) > 0);
    }

    @Test
    public void testCompareToWithSameScoreSameTime() {
        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
        Score firstScore = new Score("test player 1","word", 4, now);
        Score secondScore = new Score("test player 2","word", 4, now);

        assertTrue(firstScore.compareTo(secondScore) == 0);
    }

}