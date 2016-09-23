package com.amaterasu12.raidenredux.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by Carl on 2/17/2016.
 */
public class RenderableComponent implements Component {
    public Animation animation;
    public Texture texture;

    public RenderableComponent(Animation animation){
        this.animation = animation;
        texture = null;
    }

    public RenderableComponent(Texture texture){
        animation = null;
        this.texture = texture;
    }
}
