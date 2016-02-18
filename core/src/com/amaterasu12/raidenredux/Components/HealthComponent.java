package com.amaterasu12.raidenredux.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Carl on 2/18/2016.
 */
public class HealthComponent implements Component{
    public int health;

    public HealthComponent(int health){
        this.health = health;
    }
}
