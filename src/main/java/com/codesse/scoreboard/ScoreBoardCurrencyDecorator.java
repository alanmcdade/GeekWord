package com.codesse.scoreboard;

public class ScoreBoardCurrencyDecorator implements ScoreBoard{
    private final ScoreBoard scoreBoard;

    public ScoreBoardCurrencyDecorator(ScoreBoard scoreBoard) {
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
