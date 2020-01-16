package com.sbt.javaschool.losev.lesson17;

public class Tractor {
    int[] position = new int[] { 0, 0 };
    int[] field = new int[] { 5, 5 };
    Orientation orientation = Orientation.NORTH;

    public void move(String command) {
        if (command.equals("F"))
            moveForwards();
        if (command.equals("T"))
            turnClockwise();
    }

    public void moveForwards() {
        switch (orientation){
            case NORTH:
                position = new int[] { position[0], position[1] + 1 };
                break;
            case EAST:
                position = new int[] { position[0] + 1, position[1] };
                break;
            case SOUTH:
                position = new int[] { position[0], position[1] - 1 };
                break;
            case WEST:
                position = new int[] { position[0] - 1, position[1] };
                break;
        }
        if (position[0] > field[0] || position[1] > field[1]) {
            throw new TractorInDitchException();
        }
    }

    public void turnClockwise() {
        switch (orientation) {
            case NORTH:
                orientation = Orientation.EAST;
                break;
            case EAST:
                orientation = Orientation.SOUTH;
                break;
            case SOUTH:
                orientation = Orientation.WEST;
                break;
            case WEST:
                orientation = Orientation.NORTH;
                break;
        }
    }

    public int getPositionX() {
        return position[0];
    }

    public int getPositionY() {
        return position[1];
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
