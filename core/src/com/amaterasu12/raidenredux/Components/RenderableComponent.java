package com.amaterasu12.raidenredux.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Carl on 2/17/2016.
 */
public class RenderableComponent implements Component {
    public Animation animation = null;
    public Texture texture = null;
    public Sprite sprite = null;

    public RenderableComponent(Animation animation){
        this.animation = animation;
    }

    public RenderableComponent(Texture texture){
        this.texture = texture;
    }

    public RenderableComponent(Sprite sprite){
        this.sprite = sprite;
    }
}
