package com.amaterasu12.raidenredux.Tools;

import com.amaterasu12.raidenredux.Components.ActiveComponent;
import com.amaterasu12.raidenredux.Components.HealthComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by Carl on 2/19/2016.
 */
public class CollisionDetect implements ContactListener {
    private ComponentMapper<HealthComponent> hm = ComponentMapper.getFor(HealthComponent.class);
    private ComponentMapper<ActiveComponent> am = ComponentMapper.getFor(ActiveComponent.class);

    @Override
    public void beginContact(Contact contact) {
        //Gdx.app.log("Contact", "Collision Detected");
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        Entity A = (Entity)fixA.getUserData();
        Entity B = (Entity)fixB.getUserData();

        if(am.has(A) && am.has(B)) {
            hm.get(A).health--;
            hm.get(B).health--;
        }
        if(hm.get(A).health < 0)
            hm.get(A).health = 0;
        if(hm.get(B).health < 0)
            hm.get(B).health = 0;
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
