package com.amaterasu12.raidenredux.Screens;

import com.amaterasu12.raidenredux.Components.VelocityComponent;
import com.amaterasu12.raidenredux.Entities.Entities;
import com.amaterasu12.raidenredux.RaidenRedux;
import com.amaterasu12.raidenredux.Systems.MovementSystem;
import com.amaterasu12.raidenredux.Systems.RenderingSystem;
import com.amaterasu12.raidenredux.Tools.Manager;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Carl on 2/17/2016.
 */
public class PlayScreen implements Screen {
    //constants and variables
    public static float playTime;
    private final int BG_SCROLL_SPEED = 50;
    private final float PLAYER_MOVE_SPEED = 300;
    private float bgY;

    //cameras
    private final RaidenRedux game;
    private OrthographicCamera playCamera;
    public static Viewport gamePort;

    //systems
    private RenderingSystem renderingSystem;
    private MovementSystem movementSystem;

    //player entity
    private Entity playerShip;

    public PlayScreen(RaidenRedux gam) {
        playTime = 0;
        game = gam;
        playCamera = new OrthographicCamera();
        playCamera.setToOrtho(false, 480, 800);
        gamePort = new FitViewport(RaidenRedux.W_WIDTH, RaidenRedux.W_HEIGHT, playCamera);
        playCamera.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);
        //systems
        renderingSystem = new RenderingSystem();
        movementSystem = new MovementSystem();

        //add systems
        RaidenRedux.engine.addSystem(renderingSystem);
        RaidenRedux.engine.addSystem(movementSystem);

        //bg scroll variables
        bgY = 0;

        //create ships
        playerShip = Entities.createPlayer();
        RaidenRedux.engine.addEntity(playerShip);
    }

    @Override
    public void show() {
        Manager.bgm.play();
    }

    public void update(float delta){
        playTime += delta;

        handleInput(delta);
        //update bg position
        bgY -= BG_SCROLL_SPEED * Gdx.graphics.getDeltaTime();
    }

    public void handleInput(float delta){
        VelocityComponent playerVel = playerShip.getComponent(VelocityComponent.class);
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            playerVel.y = PLAYER_MOVE_SPEED;
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
                playerVel.x = -PLAYER_MOVE_SPEED;
            else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                playerVel.x = PLAYER_MOVE_SPEED;
            else
                playerVel.x = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            playerVel.y = -PLAYER_MOVE_SPEED;
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
                playerVel.x = -PLAYER_MOVE_SPEED;
            else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                playerVel.x = PLAYER_MOVE_SPEED;
            else
                playerVel.x = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            playerVel.x = -PLAYER_MOVE_SPEED;
            if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
                playerVel.y = -PLAYER_MOVE_SPEED;
            else if(Gdx.input.isKeyPressed(Input.Keys.UP))
                playerVel.y = PLAYER_MOVE_SPEED;
            else
                playerVel.y = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            playerVel.x = PLAYER_MOVE_SPEED;
            if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
                playerVel.y = -PLAYER_MOVE_SPEED;
            else if(Gdx.input.isKeyPressed(Input.Keys.UP))
                playerVel.y = PLAYER_MOVE_SPEED;
            else
                playerVel.y = 0;
        }
        else{
            playerVel.x = 0;
            playerVel.y = 0;
        }

    }

    @Override
    public void render(float delta) {
        //clear
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Manager.batch.setProjectionMatrix(playCamera.combined);
        Manager.batch.begin();
        Manager.batch.draw(Manager.bgPlay, 0, 0, 0, Math.round(bgY), 480, 800);
        Manager.batch.end();

        update(delta);
        RaidenRedux.engine.update(delta);


    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
