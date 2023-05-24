package com.codesse.scoreboard;

import java.util.Optional;

public class ScoreBoard {
    public static final int MAX_SIZE = 10;
    private Score[] highScores = new Score[MAX_SIZE];


    public Score scoreAt(int position) {
        checkLimits(position);
        return Optional.ofNullable(highScores[position])
                .orElseThrow(() -> new IllegalArgumentException("Nothing in that position"));
    }

    // Not required by game would normally be in the constructor to make it configurable
    private void checkLimits(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("Position cannot be less than 0");
        }

        if (position >= MAX_SIZE){
            throw new IllegalArgumentException("Position cannot be greater than 9");
        }
    }

    public void addScore(Score score) {
        int position = 0;
        while(position < MAX_SIZE){
            Optional<Score> possibleScore = Optional.ofNullable(highScores[position]);

            if (possibleScore.isPresent()){
                if (possibleScore.get().compareTo(score) > 0) {
                    System.arraycopy(highScores, position, highScores, position + 1, MAX_SIZE - position - 1);
                    highScores[position] = score;
                    break;
                } else {
                    position++;
                }
            } else {
                highScores[position] = score;
                break;
            }
        }
    }
}
