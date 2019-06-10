package com.example.kik.tools;

import com.example.kik.gamelogics.Player;

import static com.example.kik.gamelogics.Player.*;

public abstract class Tools {

    public static int map(double value, double min1, double max1, double min2, double max2) {
        if (value <= min1) {
            return (int) min2;
        } else if (value >= max1) {
            return (int) max2;
        } else {
            return (int) (((value - min1) * (max2 - min2)) / (max1 - min1) + min2);
        }
    }

    public static int indexFromXY(int x, int y) {
        return x + y * 3;
    }

    public static int XFromIndex(int index) {
        return index % 3;
    }

    public static int YFromIndex(int index) {
//        if (index < 3) {
//            return 0;
//        } else if (index < 6) {
//            return 1;
//        } else {
//            return 2;
//        }
        return (index - (index % 3)) / 3;
    }

    public static boolean areSameType(Player a, Player b, Player c) {
        return (!EMPTY.equals(a) &&
                a.equals(b) &&
                b.equals(c));
    }

    public static boolean allFieldsTaken(Player[] cellsOwners) {
        for (Player owner : cellsOwners) {
            if (Player.EMPTY.equals(owner)) {
                return false;
            }
        }
        return true;
    }
}
