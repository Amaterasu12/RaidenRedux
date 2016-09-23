package com.amaterasu12.raidenredux.Systems;

import com.amaterasu12.raidenredux.Components.PositionComponent;
import com.amaterasu12.raidenredux.Components.RenderableComponent;
import com.amaterasu12.raidenredux.RaidenRedux;
import com.amaterasu12.raidenredux.Screens.PlayScreen;
import com.amaterasu12.raidenredux.Tools.Manager;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Comparator;


/**
 * Created by Carl on 2/17/2016.
 */
public class RenderingSystem extends SortedIteratingSystem {
    private ComponentMapper<RenderableComponent> rm;
    private ComponentMapper<PositionComponent> pm;
    private OrthographicCamera cam;

    public RenderingSystem(){
        super(Family.all(RenderableComponent.class, PositionComponent.class).get(),
                new Comparator<Entity>() {
                    @Override
                    public int compare(Entity o1, Entity o2) {
                        return (int)Math.signum(ComponentMapper.getFor(PositionComponent.class).get(o1).z - ComponentMapper.getFor(PositionComponent.class).get(o2).z);
                    }
                });
        rm = ComponentMapper.getFor(RenderableComponent.class);
        pm = ComponentMapper.getFor(PositionComponent.class);

        cam = new OrthographicCamera();
        cam.setToOrtho(false, RaidenRedux.W_WIDTH, RaidenRedux.W_HEIGHT);
        cam.position.set(PlayScreen.gamePort.getWorldWidth() / 2, PlayScreen.gamePort.getWorldHeight() / 2, 0);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RenderableComponent render = rm.get(entity);
        PositionComponent position = pm.get(entity);

        cam.update();
        Manager.batch.setProjectionMatrix(cam.combined);
        Manager.batch.begin();
        if(render.texture != null){
            Manager.batch.draw(render.texture, position.x-render.texture.getWidth()/2, position.y-render.texture.getHeight()/2);
        }
        else if(render.animation != null){
            TextureRegion keyFrame = render.animation.getKeyFrame(PlayScreen.playTime, true);
            Manager.batch.draw(keyFrame, position.x-keyFrame.getRegionWidth()/2, position.y-keyFrame.getRegionHeight()/2);
        }
        Manager.batch.end();
    }

}
