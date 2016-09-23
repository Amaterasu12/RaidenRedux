package com.amaterasu12.raidenredux.Components;

import com.amaterasu12.raidenredux.Screens.PlayScreen;
import com.amaterasu12.raidenredux.Tools.Entities;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * Created by Carl on 2/18/2016.
 */
public class BodyComponent implements Component {
    public Body body;

    public BodyComponent(Shape shape, float x, float y, short bit, float angle){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x, y);

        body = PlayScreen.world.createBody(bodyDef);
        body.setTransform(x, y, angle);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = bit;
        if(bit == Entities.PLAYER_BITS) {
            fixtureDef.filter.maskBits = Entities.ENEMY_BITS | Entities.ENEMY_PROJECTILE_BITS;
        }
        else if(bit == Entities.ENEMY_BITS) {
            fixtureDef.filter.maskBits = Entities.PLAYER_BITS | Entities.PLAYER_PROJECTILE_BITS;
        }
        else if(bit == Entities.ENEMY_PROJECTILE_BITS) {
            fixtureDef.filter.maskBits = Entities.PLAYER_BITS;
        }
        else if(bit == Entities.PLAYER_PROJECTILE_BITS) {
            fixtureDef.filter.maskBits = Entities.ENEMY_BITS;
        }
        fixtureDef.isSensor = true;

        body.createFixture(fixtureDef);
    }
}
