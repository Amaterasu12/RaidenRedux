package com.amaterasu12.raidenredux.Entities;

import com.amaterasu12.raidenredux.Components.PlayerComponent;
import com.amaterasu12.raidenredux.Components.RenderableComponent;
import com.amaterasu12.raidenredux.Components.PositionComponent;
import com.amaterasu12.raidenredux.Components.VelocityComponent;
import com.amaterasu12.raidenredux.Tools.Manager;
import com.badlogic.ashley.core.Entity;

/**
 * Created by Carl on 2/17/2016.
 */
public class Entities {

    public static Entity createPlayer() {
        Entity playerShip = new Entity();
        playerShip.add(new PlayerComponent());
        playerShip.add(new PositionComponent());
        playerShip.add(new VelocityComponent());
        playerShip.add(new RenderableComponent(Manager.playerShipAnimation));

        playerShip.getComponent(PositionComponent.class).x = 240f;
        playerShip.getComponent(PositionComponent.class).y = 100f;
        return playerShip;
    }
}
