package com.codesse.scoreboard;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.LocalDateTime;

public class SimpleFixedScoreBoardTest extends TestCase {
    private SimpleFixedScoreBoard scoreBoard;
    private final Score player1 = new Score("Player1", "all", 3, LocalDateTime.now(Clock.systemUTC()));
    private final Score player2 = new Score("Player2", "word", 4, LocalDateTime.now(Clock.systemUTC()));
    private final Score player3 = new Score("Player3", "tale",0, LocalDateTime.now(Clock.systemUTC()));
    private final Score player4 = new Score("Player4", "glly",0, LocalDateTime.now(Clock.systemUTC()));
    private final Score player5 = new Score("Player5", "woolly", 6, LocalDateTime.now(Clock.systemUTC()));


    @Before
    public void setUp(){
        scoreBoard = new SimpleFixedScoreBoard();
        scoreBoard.addScore(player1);
        scoreBoard.addScore(player2);
        scoreBoard.addScore(player3);
        scoreBoard.addScore(player4);
        scoreBoard.addScore(player5);
        scoreBoard.addScore(player3);
        scoreBoard.addScore(player3);
        scoreBoard.addScore(player3);
        scoreBoard.addScore(player3);
        scoreBoard.addScore(player3);
        scoreBoard.addScore(player3);
    }

    @Test
    public void testScoreAt() {
        assertEquals(player5, scoreBoard.scoreAt(0));
        assertEquals(player2, scoreBoard.scoreAt(1));
        assertEquals(player1, scoreBoard.scoreAt(2));
    }

    @Test
    public void testPlayersCanBeRepeated() {
        assertEquals(player3, scoreBoard.scoreAt(5));
        assertEquals(player3, scoreBoard.scoreAt(6));
        assertEquals(player3, scoreBoard.scoreAt(7));
    }
}