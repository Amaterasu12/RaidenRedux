package com.amaterasu12.raidenredux.Tools;

import com.amaterasu12.raidenredux.Components.HealthComponent;
import com.amaterasu12.raidenredux.Components.PlayerComponent;
import com.amaterasu12.raidenredux.Components.ProjectileComponent;
import com.amaterasu12.raidenredux.Components.RenderableComponent;
import com.amaterasu12.raidenredux.Components.PositionComponent;
import com.amaterasu12.raidenredux.Components.VelocityComponent;
import com.amaterasu12.raidenredux.RaidenRedux;
import com.badlogic.ashley.core.Entity;

/**
 * Created by Carl on 2/17/2016.
 */
public class Entities {
    public enum PROJECTILE_TYPE {PLAYER_PHASOR, PLAYER_MISSILE, ENEMY_PHASOR, ENEMY_MISSILE}
    public static final float ENEMY_BULLET_Z = 1.0f;
    public static final float PLAYER_BULLET_Z = 1.1f;

    public static Entity createPlayer() {
        Entity playerShip = new Entity();
        playerShip.add(new PlayerComponent());
        playerShip.add(new PositionComponent(RaidenRedux.W_WIDTH/2, 100f, 2));
        playerShip.add(new VelocityComponent());
        playerShip.add(new HealthComponent(1));
        playerShip.add(new RenderableComponent(Manager.playerShipAnimation));
        return playerShip;
    }

    public static Entity createBullet(PROJECTILE_TYPE type, float xPos, float yPos, float zPos, float xVel, float yVel){
        Entity bullet = new Entity();
        bullet.add(new PositionComponent(xPos, yPos, zPos));
        bullet.add(new VelocityComponent(xVel, yVel));
        bullet.add(new HealthComponent(1));
        bullet.add(new ProjectileComponent(type));
        switch (type){
            case PLAYER_PHASOR:
                Manager.phasorSound01.play(Manager.SOUND_VOLUME/20);
                bullet.add(new RenderableComponent(Manager.phasor01));
                bullet.add(new PlayerComponent());
                break;
            default:
                bullet.add(new RenderableComponent(Manager.phasor02));
        }
        return bullet;
    }
}
