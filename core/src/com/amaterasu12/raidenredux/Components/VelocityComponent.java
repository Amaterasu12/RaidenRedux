package com.amaterasu12.raidenredux.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Carl on 2/17/2016.
 */
public class VelocityComponent implements Component {
    public float x = 0.0f;
    public float y = 0.0f;

    public VelocityComponent() {
    }

    public VelocityComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
