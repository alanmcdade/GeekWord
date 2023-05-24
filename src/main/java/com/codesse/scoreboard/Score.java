package com.codesse.scoreboard;

import java.time.LocalDateTime;
import java.util.Objects;

public class Score implements Comparable<Score>{
    public static final int SAME = 0;
    private final String playerName;
    private final Integer score;
    private final LocalDateTime when;
    private final String word;

    public Score(String playerName, String word, int score, LocalDateTime when) {
        this.playerName = playerName;
        this.score = score;
        this.when = when;
        this.word = word;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getWhen() {
        return when;
    }
    public String getWord() {
        return word;
    }

    @Override
    public int compareTo(Score other) {
        int scoreComparison = other.score.compareTo(this.score);

        if (scoreComparison > SAME || scoreComparison < SAME){
            return scoreComparison;
        } else {
            return other.when.compareTo(this.when);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return Objects.equals(playerName, score1.playerName) && Objects.equals(score, score1.score) && Objects.equals(when, score1.when) && Objects.equals(word, score1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, score, when, word);
    }
}
