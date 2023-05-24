package com.codesse.scoreboard;

import junit.framework.TestCase;
import org.junit.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class ScoreBoardCurrencyDecoratorTest extends TestCase {

    public static final String THIRTEEN_LETTER_WORD = "waterflooding";
    public static final String TWELVE_LETTER_WORD = "abandonments";
    public static final String ELEVEN_LETTER_WORD = "abandonment";
    public static final String TEN_LETTER_WORD = "aardwolves";
    public static final String NINE_LETTER_WORD = "abashment";
    public static final String EIGHT_LETTER_WORD = "abacuses";
    public static final String SEVEN_LETTER_WORD = "abasias";
    public static final String SIX_LETTER_WORD = "abbacy";
    public static final String FIVE_LETTER_WORD = "abate";
    public static final String FOUR_LETTER_WORD = "abba";
        private ScoreBoard scoreBoard = new ScoreBoardCurrencyDecorator(new SimpleFixedScoreBoard());

    @Test
    public void testScoreboardConcurrency() {
        int size = 100;
        List<CompletableFuture<Score>> futures = new ArrayList<>(size    );
        for (int i =0; i< size; i++){
            final int index = i;
            futures.add(CompletableFuture.supplyAsync(() -> {
               String word;
               switch (index) {
                   case 0: word = FOUR_LETTER_WORD;
                       break;
                   case 10: word = TWELVE_LETTER_WORD;
                       break;
                   case 20: word = EIGHT_LETTER_WORD;
                       break;
                   case 30: word = SEVEN_LETTER_WORD;
                       break;
                   case 40: word = TEN_LETTER_WORD;
                       break;
                   case 50: word = NINE_LETTER_WORD;
                       break;
                   case 60: word = SIX_LETTER_WORD;
                       break;
                   case 70: word = THIRTEEN_LETTER_WORD;
                       break;
                   case 80: word = FIVE_LETTER_WORD;
                       break;
                   case 90: word = ELEVEN_LETTER_WORD;
                       break;
                   default: word = "all";
                       break;
               }
               Score score = new Score(String.format("player%s",index), word, word.length(), LocalDateTime.now(Clock.systemUTC()));
               scoreBoard.addScore(score);
               return score;
            }));
        }

        futures.stream().forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException e) {
                fail(e.getMessage());
            } catch (ExecutionException e) {
                fail(e.getMessage());
            }
        });

        assertEquals(THIRTEEN_LETTER_WORD, scoreBoard.scoreAt(0).getWord());
        assertEquals(TWELVE_LETTER_WORD, scoreBoard.scoreAt(1).getWord());
        assertEquals(ELEVEN_LETTER_WORD, scoreBoard.scoreAt(2).getWord());
        assertEquals(TEN_LETTER_WORD, scoreBoard.scoreAt(3).getWord());
        assertEquals(NINE_LETTER_WORD, scoreBoard.scoreAt(4).getWord());
        assertEquals(EIGHT_LETTER_WORD, scoreBoard.scoreAt(5).getWord());
        assertEquals(SEVEN_LETTER_WORD, scoreBoard.scoreAt(6).getWord());
        assertEquals(SIX_LETTER_WORD, scoreBoard.scoreAt(7).getWord());
        assertEquals(FIVE_LETTER_WORD, scoreBoard.scoreAt(8).getWord());
        assertEquals(FOUR_LETTER_WORD, scoreBoard.scoreAt(9).getWord());
    }

}