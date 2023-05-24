package com.codesse.wordgeek;


import com.codesse.scoreboard.Score;
import com.codesse.scoreboard.ScoreBoard;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This is the shell implementation of the WordGeek interface.
 * It is the class that you should focus on when developing your solution to the Challenge.
 */
public class WordGeekImpl implements WordGeek {
    private final LetterFrequencyGraph frequencyMap;
    private final ValidWords validWords;

    private final ScoreBoard scoreBoard;

    public WordGeekImpl(String areallylongword, ValidWords validWords) {
        this.frequencyMap = new LetterFrequencyGraph(areallylongword);
        this.validWords = validWords;
        this.scoreBoard = new ScoreBoard();
    }

    @Override
    public int submitWord(String playerName, String word) {
        if (Objects.isNull(playerName) || Objects.isNull(word) || playerName.trim().length() == 0) {
            return 0;
        }

        Integer result;
        if (checkAllLettersAreAvailable(word) && validWords.contains(word)) {
            result = word.length();
        } else {
            result = 0;
        }

        scoreBoard.addScore(new Score(playerName, word, result, LocalDateTime.now(Clock.systemUTC())));

        return result;
    }

    private boolean checkAllLettersAreAvailable(String word) {
        return frequencyMap.containsAll(new LetterFrequencyGraph(word));
    }

    @Override
    public String getPlayerNameAtPosition(int position) {
        try {
            return scoreBoard.scoreAt(position).getPlayerName();
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    @Override
    public String getWordEntryAtPosition(int position) {
        try {
            return scoreBoard.scoreAt(position).getWord();
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    @Override
    public Integer getScoreAtPosition(int position) {
        try {
            return scoreBoard.scoreAt(position).getScore();
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
