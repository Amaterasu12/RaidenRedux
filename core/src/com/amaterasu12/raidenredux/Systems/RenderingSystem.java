package com.amaterasu12.raidenredux.Systems;

import com.amaterasu12.raidenredux.Components.ActiveComponent;
import com.amaterasu12.raidenredux.Components.PositionComponent;
import com.amaterasu12.raidenredux.Components.RenderableComponent;
import com.amaterasu12.raidenredux.Components.VelocityComponent;
import com.amaterasu12.raidenredux.RaidenRedux;
import com.amaterasu12.raidenredux.Screens.PlayScreen;
import com.amaterasu12.raidenredux.Tools.Manager;
import com.amaterasu12.raidenredux.Tools.Tools;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import java.util.Comparator;


/**
 * Created by Carl on 2/17/2016.
 */
public class RenderingSystem extends SortedIteratingSystem {
    private ComponentMapper<RenderableComponent> rm;
    private ComponentMapper<PositionComponent> pm;
    private ComponentMapper<VelocityComponent> vm;
    private OrthographicCamera cam;
    private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    public RenderingSystem(){
        super(Family.all(RenderableComponent.class, PositionComponent.class, ActiveComponent.class).get(),
                new Comparator<Entity>() {
                    @Override
                    public int compare(Entity o1, Entity o2) {
                        return (int)Math.signum(ComponentMapper.getFor(PositionComponent.class).get(o1).z - ComponentMapper.getFor(PositionComponent.class).get(o2).z);
                    }
                });
        rm = ComponentMapper.getFor(RenderableComponent.class);
        pm = ComponentMapper.getFor(PositionComponent.class);
        vm = ComponentMapper.getFor(VelocityComponent.class);

        cam = new OrthographicCamera();
        cam.setToOrtho(false, RaidenRedux.W_WIDTH, RaidenRedux.W_HEIGHT);
        cam.position.set(PlayScreen.gamePort.getWorldWidth() / 2, PlayScreen.gamePort.getWorldHeight() / 2, 0);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RenderableComponent render = rm.get(entity);
        PositionComponent position = pm.get(entity);
        VelocityComponent velocity = vm.get(entity);

        cam.update();
        Manager.batch.setProjectionMatrix(cam.combined);
        Manager.batch.begin();
        if(render.texture != null){
            int srcWidth = render.texture.getWidth();
            int srcHeight = render.texture.getHeight();
            Manager.batch.draw(render.texture, position.x-srcWidth/2, position.y-srcHeight/2, 0, 0,
                    srcWidth, srcHeight, 1, 1, Tools.getRotationAngle(velocity), 0, 0, srcWidth, srcHeight, false, false);
        }
        else if(render.animation != null){
            TextureRegion keyFrame = render.animation.getKeyFrame(PlayScreen.playTime, true);
            Manager.batch.draw(keyFrame, position.x-keyFrame.getRegionWidth()/2, position.y-keyFrame.getRegionHeight()/2);
        }
        else if(render.sprite != null){
            float srcWidth = render.sprite.getWidth();
            float srcHeight = render.sprite.getHeight();
            render.sprite.setPosition(position.x-srcWidth/2, position.y-srcHeight/2);
            render.sprite.setOrigin(srcWidth / 2, srcHeight/2);
            render.sprite.setRotation(Tools.getRotationAngle(velocity));
            render.sprite.draw(Manager.batch);
        }
        Manager.batch.end();
        debugRenderer.render(PlayScreen.world, cam.combined);
    }

}
