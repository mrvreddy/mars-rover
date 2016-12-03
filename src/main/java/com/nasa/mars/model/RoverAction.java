package com.nasa.mars.model;

/**
 * Created by rmamilla on 12/3/16.
 */
public enum RoverAction {
    M,
    L,
    R;

    public static RoverAction valueOf(char c){
        for(RoverAction action : RoverAction.values()){
            if(action.name().equals(c+"")){
                return action;
            }
        }
        return null;
    }
}