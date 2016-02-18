package com.amaterasu12.raidenredux.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Carl on 2/17/2016.
 */
public class PositionComponent implements Component{
    public float x = 0;
    public float y = 0;
    public float z = 0;

    public PositionComponent(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PositionComponent() {
    }
}
