package com.codesse.scoreboard;

public class SimpleScoreBoardCurrencyDecorator implements ScoreBoard{
    private final ScoreBoard scoreBoard;

    public SimpleScoreBoardCurrencyDecorator(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    @Override
    public Score scoreAt(int position) {
        synchronized (scoreBoard){
            return scoreBoard.scoreAt(position);
        }
    }

    @Override
    public void addScore(Score score) {
        synchronized (scoreBoard){
            scoreBoard.addScore(score);
        }
    }
}
