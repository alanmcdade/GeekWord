package com.codesse.scoreboard;

import java.util.concurrent.*;

public class QueueScoreBoardConcurrencyDecorator implements ScoreBoard {
    private final ScoreBoard scoreBoard;
    private final BlockingQueue<ScoreboardCommand> queue;
    private final ExecutorService executorService;
    private boolean done = false;


    public QueueScoreBoardConcurrencyDecorator(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
        this.queue = new LinkedBlockingQueue<>();
        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(this::executeQueuedCommands);
    }
    @Override
    public Score scoreAt(int position) {
        CompletableFuture<Score> future = new CompletableFuture<>();

        while(!queue.offer(new GetScoreByPosition(position, future))){
            try {
                Thread.sleep(100l);
            } catch (InterruptedException e) {
                // log this and continue
            }
        }

        try {
            return future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addScore(Score score) {
        while(!queue.offer(new AddScore(score))){
            try {
                Thread.sleep(100l);
            } catch (InterruptedException e) {
                // log this and continue
            }
        }
    }

    private void executeQueuedCommands() {
        while(!done){
            try {
                ScoreboardCommand command =queue.take();
                command.execute();
            } catch (InterruptedException e) {
                done = true;
                executorService.shutdown();
            }
        }
    }

    interface ScoreboardCommand {
        void execute();
    };

    private class AddScore implements ScoreboardCommand {
        private final Score score;

        public AddScore(Score score) {
            this.score = score;
        }

        @Override
        public void execute() {
            scoreBoard.addScore(score);
        }
    }

    private class GetScoreByPosition implements ScoreboardCommand {
        private final int position;
        private final CompletableFuture<Score> future;

        public GetScoreByPosition(int position, CompletableFuture<Score> future) {
            this.position = position;
            this.future = future;
        }

        @Override
        public void execute() {
            future.complete(scoreBoard.scoreAt(position));
        }
    }
}
