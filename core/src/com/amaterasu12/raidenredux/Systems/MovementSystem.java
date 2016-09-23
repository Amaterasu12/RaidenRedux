package com.amaterasu12.raidenredux.Systems;

import com.amaterasu12.raidenredux.Components.PositionComponent;
import com.amaterasu12.raidenredux.Components.VelocityComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

/**
 * Created by Carl on 2/17/2016.
 */
public class MovementSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> pm;
    private ComponentMapper<VelocityComponent> vm;

    public MovementSystem(){
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());

        pm = ComponentMapper.getFor(PositionComponent.class);
        vm = ComponentMapper.getFor(VelocityComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = pm.get(entity);
        VelocityComponent velocity = vm.get(entity);

        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;
    }
}
