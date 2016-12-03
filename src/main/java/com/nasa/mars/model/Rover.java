package com.nasa.mars.model;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by rmamilla on 12/3/16.
 */
@ToString(exclude = {"plateau"})
@Getter
public class Rover{
    long roverNumber;
    private Position position;

    public Rover(long roverNumber, Position originalPosition){
        this.roverNumber = roverNumber;
        this.position = originalPosition;
    }

    public void move(){
        position = getNewPositionIfMoved();
    }


    //Assuming unit of move for Rover is 1 grid which is equivalent to how plateau is divided
    public Position getNewPositionIfMoved(){
        Position newPosition = null;
        switch (position.getDirection()){
            case EAST:
                newPosition = new Position(position.getX() + 1, position.getY(), position.getDirection());
                break;
            case WEST:
                newPosition =new Position(position.getX() - 1, position.getY(), position.getDirection());
                break;
            case NORTH:
                newPosition = new Position(position.getX(), position.getY() + 1, position.getDirection());
                break;
            case SOUTH:
                newPosition = new Position(position.getX(), position.getY() - 1, position.getDirection());
                break;
        }
        return newPosition;
    }

    public void rotateLeft(){
        switch (position.getDirection()){
            case EAST:
                position = new Position(position.getX(), position.getY(), Position.Direction.NORTH);
                break;
            case WEST:
                position = new Position(position.getX(), position.getY(), Position.Direction.SOUTH);
                break;
            case NORTH:
                position = new Position(position.getX(), position.getY(), Position.Direction.WEST);
                break;
            case SOUTH:
                position = new Position(position.getX(), position.getY(), Position.Direction.EAST);
                break;
        }
    }

    public void rotateRight(){
        switch (position.getDirection()){
            case EAST:
                position = new Position(position.getX(), position.getY(), Position.Direction.SOUTH);
                break;
            case WEST:
                position = new Position(position.getX(), position.getY(), Position.Direction.NORTH);
                break;
            case NORTH:
                position = new Position(position.getX(), position.getY(), Position.Direction.EAST);
                break;
            case SOUTH:
                position = new Position(position.getX(), position.getY(), Position.Direction.WEST);
                break;
        }
    }
}

