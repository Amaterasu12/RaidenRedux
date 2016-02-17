package com.amaterasu12.raidenredux.Screens;

import com.amaterasu12.raidenredux.RaidenRedux;
import com.amaterasu12.raidenredux.Tools.Manager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Carl on 2/17/2016.
 */
public class PlayScreen implements Screen {
    private final RaidenRedux game;
    private OrthographicCamera playCamera;

    //bg scroll variables
    private float bgY;
    private int bgScrollSpeed;

    public PlayScreen(RaidenRedux gam) {
        game = gam;
        playCamera = new OrthographicCamera();
        playCamera.setToOrtho(false, 480, 800);

        //bg scroll variables
        bgY = 0;
        bgScrollSpeed = 50;
    }

    @Override
    public void show() {
        Manager.bgm.play();
    }

    public void update(float delta){
        playCamera.update();
        Manager.batch.setProjectionMatrix(playCamera.combined);

        //update bg position
        bgY -= bgScrollSpeed * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void render(float delta) {
        //clear
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        Manager.batch.begin();
        Manager.batch.draw(Manager.bgPlay, 0, 0, 0, Math.round(bgY), 480, 800);
        Manager.batch.end();

    }

    @Override
    public void resize(int width, int height) {

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
