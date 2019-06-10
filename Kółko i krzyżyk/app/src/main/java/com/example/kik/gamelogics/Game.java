package com.example.kik.gamelogics;

import static com.example.kik.gamelogics.Player.*;

public class Game {
    private static final String TAG = "Game";

    private Field field;

    private Player current;
    private boolean gameRunning;

    public Game(Field field) {
        this.field = field;
        newGame();
    }

    public void move(int x, int y) {
        if (!gameRunning) {
            return;
        }
        if (!isMoveValid(x,y)){
            return;
        }
        field.move(x, y, current);
        if (field.checkEndGame()) {
//            newGame();
            return;
        }
        current = CROSS.equals(current) ? NOUGHT : CROSS;
        field.setTurnImage(current);
    }

    public void newGame() {
        gameRunning = true;
        current = CROSS;
        field.setTurnImage(current);
        field.clear();
    }

    public boolean isMoveValid(int x, int y) {
        return (!(x < 0 || x > 2 || y < 0 || y > 2) && field.isEmpty(x, y));
    }

    public Player[] getCellsOwners() {
        return field.getCellsOwners();
    }

    public Field getField() {
        return field;
    }
}
