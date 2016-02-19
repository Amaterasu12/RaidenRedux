package com.amaterasu12.raidenredux.Systems;

import com.amaterasu12.raidenredux.Components.ActiveComponent;
import com.amaterasu12.raidenredux.Components.BodyComponent;
import com.amaterasu12.raidenredux.Components.PositionComponent;
import com.amaterasu12.raidenredux.Components.VelocityComponent;
import com.amaterasu12.raidenredux.Tools.Tools;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Carl on 2/17/2016.
 */
public class MovementSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> pm;
    private ComponentMapper<VelocityComponent> vm;
    private ComponentMapper<BodyComponent> bm;
    public MovementSystem(){
        super(Family.all(PositionComponent.class, VelocityComponent.class, ActiveComponent.class).get());

        pm = ComponentMapper.getFor(PositionComponent.class);
        vm = ComponentMapper.getFor(VelocityComponent.class);
        bm = ComponentMapper.getFor(BodyComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = pm.get(entity);
        VelocityComponent velocity = vm.get(entity);

        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;

        if(bm.has(entity)){
            BodyComponent bodyComponent = bm.get(entity);
            Vector2 bodyPosition = bodyComponent.body.getPosition();
            bodyComponent.body.setTransform(bodyPosition.x + velocity.x*deltaTime, bodyPosition.y + velocity.y*deltaTime, (float)Math.toRadians(Tools.getRotationAngle(velocity)));
        }
    }
}
