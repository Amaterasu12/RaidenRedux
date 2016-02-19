package com.amaterasu12.raidenredux.Tools;

import com.amaterasu12.raidenredux.Components.ActiveComponent;
import com.amaterasu12.raidenredux.Components.BodyComponent;
import com.amaterasu12.raidenredux.Components.HealthComponent;
import com.amaterasu12.raidenredux.Components.PlayerComponent;
import com.amaterasu12.raidenredux.Components.ProjectileComponent;
import com.amaterasu12.raidenredux.Components.RenderableComponent;
import com.amaterasu12.raidenredux.Components.PositionComponent;
import com.amaterasu12.raidenredux.Components.VelocityComponent;
import com.amaterasu12.raidenredux.RaidenRedux;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by Carl on 2/17/2016.
 */
public class Entities {
    public enum PROJECTILE_TYPE {PLAYER_PHASOR, PLAYER_MISSILE, ENEMY_PHASOR, ENEMY_MISSILE}
    public static final float ENEMY_BULLET_Z = 1.0f;
    public static final float PLAYER_BULLET_Z = 1.1f;
    public static final short PLAYER_BITS = 1;
    public static final short ENEMY_BITS = 2;
    public static final short PLAYER_PROJECTILE_BITS = 4;
    public static final short ENEMY_PROJECTILE_BITS = 8;
    public static final int DYNAMIC_BOD = 1;
    public static final int KINEMATIC_BOD = 2;


    public static Entity createPlayer() {
        Entity playerShip = new Entity();
        playerShip.add(new PlayerComponent());
        PositionComponent playerPosition = new PositionComponent(RaidenRedux.W_WIDTH/2, 100f, 2);
        playerShip.add(playerPosition);
        playerShip.add(new VelocityComponent());
        playerShip.add(new HealthComponent(1));
        playerShip.add(new RenderableComponent(Manager.playerShipAnimation));

        CircleShape circle = new CircleShape();
        float textureSize = Manager.playerShipAnimation.getKeyFrame(0.1f).getRegionX();
        circle.setRadius(textureSize / 5);
        BodyComponent bodyComponent = new BodyComponent(circle, playerPosition.x, playerPosition.y + textureSize/16, PLAYER_BITS, 0, playerShip, DYNAMIC_BOD, false);
        playerShip.add(bodyComponent);
        circle.dispose();

        bodyComponent.body.setActive(true);
        playerShip.add(new ActiveComponent());

        return playerShip;
    }

    public static Entity createShip(float xPos, float yPos, int health, RenderableComponent render, int size) {
        Entity ship = new Entity();
        PositionComponent shipPosition = new PositionComponent(xPos, yPos, 1.9f);
        ship.add(shipPosition);
        ship.add(new VelocityComponent());
        ship.add(new HealthComponent(health));
        ship.add(render);

        CircleShape circle = new CircleShape();
        circle.setRadius(size / 2.5f);
        ship.add(new BodyComponent(circle, shipPosition.x, shipPosition.y, ENEMY_BITS, 0, ship, KINEMATIC_BOD, false));
        circle.dispose();

        return ship;
    }

    public static Entity createBullet(PROJECTILE_TYPE type, float xPos, float yPos, float zPos, float xVel, float yVel){
        Entity bullet = new Entity();
        bullet.add(new PositionComponent(xPos, yPos, zPos));
        VelocityComponent velocity = new VelocityComponent(xVel, yVel);
        bullet.add(velocity);
        bullet.add(new HealthComponent(1));
        bullet.add(new ProjectileComponent(type));
        //body and sprite
        switch (type){
            case PLAYER_PHASOR:
                Manager.phasorSound01.play(Manager.SOUND_VOLUME / 20);
                Sprite sprite = Manager.phasorRed06;
                bullet.add(new RenderableComponent(sprite));
                float spriteSizeX = Manager.phasorRed06.getWidth();
                float spriteSizeY = Manager.phasorRed06.getHeight();
//                CircleShape circleShape = new CircleShape();
//                circleShape.setRadius(Math.min(spriteSizeX, spriteSizeY) / 2);
//                bullet.add(new BodyComponent(circleShape, xPos + spriteSizeX*xVel/velocity.getMagnitude()/3, yPos + spriteSizeY*yVel/velocity.getMagnitude()/3, PLAYER_PROJECTILE_BITS, 0));
//                circleShape.dispose();
                PolygonShape polygonShape = new PolygonShape();
                polygonShape.setAsBox(spriteSizeX / 2, spriteSizeY / 2);
                BodyComponent bodyComponent = new BodyComponent(polygonShape, xPos, yPos, PLAYER_PROJECTILE_BITS, 0, bullet, DYNAMIC_BOD, false);
                bullet.add(bodyComponent);
                bodyComponent.body.setActive(true);
                polygonShape.dispose();
                break;
            default:
                bullet.add(new RenderableComponent(Manager.phasorGreen04));
        }
        bullet.add(new ActiveComponent());
        return bullet;
    }
}
