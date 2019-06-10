package com.example.kik.gamelogics;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import static com.example.kik.gamelogics.Player.CROSS;
import static com.example.kik.gamelogics.Player.EMPTY;
import static com.example.kik.gamelogics.Player.NOUGHT;
import static com.example.kik.tools.Tools.allFieldsTaken;
import static com.example.kik.tools.Tools.areSameType;
import static com.example.kik.tools.Tools.indexFromXY;

public class Field {
    private static final String TAG = "Field";

    private ImageView cells[];
    private ImageView turnImage;

    private Drawable cross;
    private Drawable nought;
    private Drawable end;

    private Player[] cellsOwners = new Player[9];


    public Field(ImageView[] cells, ImageView turnImage, Drawable cross, Drawable nought, Drawable end) {
        this.cells = cells;
        this.turnImage = turnImage;
        this.cross = cross;
        this.nought = nought;
        this.end = end;
        for (int i = 0; i < cellsOwners.length; i++) {
            cells[i].setVisibility(View.INVISIBLE);
            cellsOwners[i] = EMPTY;
        }
    }

    public boolean isEmpty(int x, int y) {
        int index = indexFromXY(x, y);
        return EMPTY.equals(cellsOwners[index]);
    }

    public void move(int x, int y, Player current) {
        int index = indexFromXY(x, y);
        cellsOwners[index] = current;
        cells[index].setImageDrawable(CROSS.equals(current) ? cross : nought);
        cells[index].setVisibility(View.VISIBLE);
    }

    public boolean checkEndGame() {
        // horizontal
        return ((areSameType(cellsOwners[0], cellsOwners[1], cellsOwners[2])) ||
                (areSameType(cellsOwners[3], cellsOwners[4], cellsOwners[5])) ||
                (areSameType(cellsOwners[6], cellsOwners[7], cellsOwners[8])) ||
                // vertical
                (areSameType(cellsOwners[0], cellsOwners[3], cellsOwners[6])) ||
                (areSameType(cellsOwners[1], cellsOwners[4], cellsOwners[7])) ||
                (areSameType(cellsOwners[2], cellsOwners[5], cellsOwners[8])) ||
                // diagonals
                (areSameType(cellsOwners[0], cellsOwners[4], cellsOwners[8])) ||
                (areSameType(cellsOwners[2], cellsOwners[4], cellsOwners[6])) ||
                // tie
                allFieldsTaken(cellsOwners)
        );
    }

    public void setTurnImage(Player current) {
        Drawable img;
        if (CROSS.equals(current)) {
            img = cross;
        } else if (NOUGHT.equals(current)) {
            img = nought;
        } else {
            img = end;
        }
        turnImage.setImageDrawable(img);
    }

    public void clear() {
        for (int i = 0; i < cellsOwners.length; i++) {
            cells[i].setVisibility(View.INVISIBLE);
            cellsOwners[i] = EMPTY;
        }
    }

    public Player[] getCellsOwners() {
        return cellsOwners;
    }
}
