package com.example.kik.gamelogics;

public class Judge {
    private static final String TAG = "Judge";

    private Game game;

    public Judge(Game game) {
        this.game = game;
    }

    public void newGame() {
        game.newGame();
    }

    public synchronized void move(int x, int y) {
        if (game.isMoveValid(x, y)) {
            game.move(x, y);
            if (game.getField().checkEndGame()) {
                newGame();
            }
        }
    }
}
