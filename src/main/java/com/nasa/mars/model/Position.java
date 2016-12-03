package com.nasa.mars.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by rmamilla on 12/3/16.
 */
@AllArgsConstructor
@Data
public class Position{
    private int x;
    private int y;
    private Direction direction;

    public String toString(){
        return String.format("Coordinates : X : %d , Y: %d , Facing Direction: %s", x, y, direction.name());
    }

    public enum Direction {
        EAST,
        WEST,
        NORTH,
        SOUTH;
    }
}