package com.nasa.mars.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by rmamilla on 12/3/16.
 */
@AllArgsConstructor
@Data
public class RoverInPlateau {
    Rover rover;
    Plateau plateau;

    public boolean isValidNewPosition(Position newPosition){
        if(newPosition.getX() > plateau.getArea().getX() || newPosition.getY() > plateau.getArea().getY()
                || newPosition.getX() < 0 || newPosition.getY() < 0) {
            System.out.println("coming here");
            return false;
        }
        return true;
    }

    public void performAction(RoverAction action){
        switch (action){
            case M:
                if(isValidNewPosition(rover.getNewPositionIfMoved())) {
                    rover.move();
                }
                break;
            case L:
                rover.rotateLeft();
                break;
            case R:
                rover.rotateRight();
                break;
            default:
                System.out.println("Wrong input : Skipping");
        }
    }
}
