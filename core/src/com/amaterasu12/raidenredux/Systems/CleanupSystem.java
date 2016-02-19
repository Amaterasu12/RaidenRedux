package com.amaterasu12.raidenredux.Systems;

import com.amaterasu12.raidenredux.Components.BodyComponent;
import com.amaterasu12.raidenredux.Components.HealthComponent;
import com.amaterasu12.raidenredux.Components.PositionComponent;
import com.amaterasu12.raidenredux.RaidenRedux;
import com.amaterasu12.raidenredux.Screens.PlayScreen;
import com.amaterasu12.raidenredux.Tools.Tools;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

/**
 * Created by Carl on 2/18/2016.
 */
public class CleanupSystem extends IteratingSystem {
    private final int GARBAGE_ZONE = -800;
    private ComponentMapper<PositionComponent> pm;
    private ComponentMapper<HealthComponent> hm;
    private ComponentMapper<BodyComponent> bm;

    public CleanupSystem() {
        super(Family.all(PositionComponent.class).get());

        pm = ComponentMapper.getFor(PositionComponent.class);
        hm = ComponentMapper.getFor(HealthComponent.class);
        bm = ComponentMapper.getFor(BodyComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = pm.get(entity);

        if((Tools.outOfBoundsX(position, GARBAGE_ZONE) != Tools.DIRECTION.NONE) || (Tools.outOfBoundsY(position, GARBAGE_ZONE) != Tools.DIRECTION.NONE)){
            if(bm.has(entity))
                PlayScreen.world.destroyBody(bm.get(entity).body);
            RaidenRedux.engine.removeEntity(entity);
        }

        else if(hm.has(entity) && hm.get(entity).health == 0){
            if(bm.has(entity))
                PlayScreen.world.destroyBody(bm.get(entity).body);
            RaidenRedux.engine.removeEntity(entity);
        }
    }
}
