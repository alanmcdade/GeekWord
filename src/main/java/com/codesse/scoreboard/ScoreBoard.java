package com.codesse.scoreboard;

public interface ScoreBoard {
    Score scoreAt(int position);

    void addScore(Score score);
}
