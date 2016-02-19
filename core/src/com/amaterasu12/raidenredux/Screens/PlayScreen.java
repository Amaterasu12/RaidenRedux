package com.amaterasu12.raidenredux.Screens;

import com.amaterasu12.raidenredux.Components.ActiveComponent;
import com.amaterasu12.raidenredux.Components.BodyComponent;
import com.amaterasu12.raidenredux.Components.PositionComponent;
import com.amaterasu12.raidenredux.Components.RenderableComponent;
import com.amaterasu12.raidenredux.Components.VelocityComponent;
import com.amaterasu12.raidenredux.Systems.CleanupSystem;
import com.amaterasu12.raidenredux.Tools.CollisionDetect;
import com.amaterasu12.raidenredux.Tools.Entities;
import com.amaterasu12.raidenredux.RaidenRedux;
import com.amaterasu12.raidenredux.Systems.MovementSystem;
import com.amaterasu12.raidenredux.Systems.RenderingSystem;
import com.amaterasu12.raidenredux.Tools.Manager;
import com.amaterasu12.raidenredux.Tools.Tools;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Carl on 2/17/2016.
 */
public class PlayScreen implements Screen {
    //constants and variables
    public static float playTime = 0;
    private final int BG_SCROLL_SPEED = 50;
    private final float PLAYER_MOVE_SPEED = 300;
    private final float PLAYER_BULLET_FREQUENCY = Gdx.graphics.getDeltaTime()*5;
    private final float PLAYER_BULLET_VELOCITY = 600f;
    private float bulletElapsedTime = 0;
    private float bgY = 0;
    private VelocityComponent playerVel;
    private PositionComponent playerPos;

    //cameras
    private final RaidenRedux game;
    private OrthographicCamera playCamera;
    public static Viewport gamePort;

    //systems
    private RenderingSystem renderingSystem;
    private MovementSystem movementSystem;
    private CleanupSystem cleanupSystem;

    //box2d
    public static World world;


    //player entity
    private Entity playerShip;

    //test ship
    private Entity testEnemy;

    public PlayScreen(RaidenRedux gam) {
        this.game = gam;
        playCamera = new OrthographicCamera();
        playCamera.setToOrtho(false, RaidenRedux.W_WIDTH, RaidenRedux.W_HEIGHT);
        gamePort = new FitViewport(RaidenRedux.W_WIDTH, RaidenRedux.W_HEIGHT, playCamera);
        playCamera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        //systems
        renderingSystem = new RenderingSystem();
        movementSystem = new MovementSystem();
        cleanupSystem = new CleanupSystem();


        //add systems
        RaidenRedux.engine.addSystem(renderingSystem);
        RaidenRedux.engine.addSystem(movementSystem);
        RaidenRedux.engine.addSystem(cleanupSystem);

        //box2d
        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new CollisionDetect());

        //create ships
        playerShip = Entities.createPlayer();
        RaidenRedux.engine.addEntity(playerShip);
        playerVel = playerShip.getComponent(VelocityComponent.class);
        playerPos = playerShip.getComponent(PositionComponent.class);

        RenderableComponent enemySprite = new RenderableComponent(Manager.enemyBlack4);
        testEnemy = Entities.createShip(240, 650, 50, enemySprite, enemySprite.sprite.getRegionWidth());
        testEnemy.getComponent(BodyComponent.class).body.setActive(true);
        testEnemy.add(new ActiveComponent());
        RaidenRedux.engine.addEntity(testEnemy);
    }

    @Override
    public void show() {
        playTime = 0;
        Manager.bgm.play();
    }

    public void update(float delta){
        playTime += delta;
        playerVel = playerShip.getComponent(VelocityComponent.class);
        playerPos = playerShip.getComponent(PositionComponent.class);

        //spawn player bullets
        bulletElapsedTime += delta;
        if(bulletElapsedTime >= PLAYER_BULLET_FREQUENCY){
            RaidenRedux.engine.addEntity(Entities.createBullet(Entities.PROJECTILE_TYPE.PLAYER_PHASOR, playerPos.x-10, playerPos.y+10, Entities.PLAYER_BULLET_Z, 0, PLAYER_BULLET_VELOCITY));
            RaidenRedux.engine.addEntity(Entities.createBullet(Entities.PROJECTILE_TYPE.PLAYER_PHASOR, playerPos.x+10, playerPos.y+10, Entities.PLAYER_BULLET_Z, 0, PLAYER_BULLET_VELOCITY));
            bulletElapsedTime = 0;
        }

        handleInput(delta);

        //update bg position
        bgY -= BG_SCROLL_SPEED * Gdx.graphics.getDeltaTime();
    }

    public void handleInput(float delta){

        int edgePadding = 32;
        Tools.DIRECTION xBoundCheck = Tools.outOfBoundsX(playerPos, edgePadding);
        Tools.DIRECTION yBoundCheck = Tools.outOfBoundsY(playerPos, edgePadding);
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && (yBoundCheck != Tools.DIRECTION.UP)){
            playerVel.y = PLAYER_MOVE_SPEED;
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && (xBoundCheck != Tools.DIRECTION.LEFT))
                playerVel.x = -PLAYER_MOVE_SPEED;
            else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (xBoundCheck != Tools.DIRECTION.RIGHT))
                playerVel.x = PLAYER_MOVE_SPEED;
            else
                playerVel.x = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && (yBoundCheck != Tools.DIRECTION.DOWN)){
            playerVel.y = -PLAYER_MOVE_SPEED;
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && (xBoundCheck != Tools.DIRECTION.LEFT))
                playerVel.x = -PLAYER_MOVE_SPEED;
            else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (xBoundCheck != Tools.DIRECTION.RIGHT))
                playerVel.x = PLAYER_MOVE_SPEED;
            else
                playerVel.x = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && (xBoundCheck != Tools.DIRECTION.LEFT)){
            playerVel.x = -PLAYER_MOVE_SPEED;
            if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && (yBoundCheck != Tools.DIRECTION.DOWN))
                playerVel.y = -PLAYER_MOVE_SPEED;
            else if(Gdx.input.isKeyPressed(Input.Keys.UP) && (yBoundCheck != Tools.DIRECTION.UP))
                playerVel.y = PLAYER_MOVE_SPEED;
            else
                playerVel.y = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (xBoundCheck != Tools.DIRECTION.RIGHT)){
            playerVel.x = PLAYER_MOVE_SPEED;
            if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && (yBoundCheck != Tools.DIRECTION.DOWN))
                playerVel.y = -PLAYER_MOVE_SPEED;
            else if(Gdx.input.isKeyPressed(Input.Keys.UP) && (yBoundCheck != Tools.DIRECTION.UP))
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
        Manager.batch.draw(Manager.bgPlay, 0, 0, 0, Math.round(bgY), RaidenRedux.W_WIDTH, RaidenRedux.W_HEIGHT);
        Manager.batch.end();

        update(delta);
        RaidenRedux.engine.update(delta);

        world.step(1 / 45f, 10, 10);
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
        world.dispose();
    }

}
