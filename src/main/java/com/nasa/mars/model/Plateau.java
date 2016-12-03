package com.nasa.mars.model;

import lombok.Data;

/**
 * Created by rmamilla on 12/3/16.
 */

@Data
public class Plateau{
    final Position area;

    public Plateau(int x, int y){
        this.area = new Position(x, y, null);
    }
}