package com.amaterasu12.raidenredux.Screens;

import com.amaterasu12.raidenredux.RaidenRedux;
import com.amaterasu12.raidenredux.Tools.Manager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Carl on 2/17/2016.
 */
public class MenuScreen implements Screen {
    private final RaidenRedux game;
    private OrthographicCamera menuCamera;
    private Viewport viewPort;

    public MenuScreen(RaidenRedux gam) {
        game = gam;
        menuCamera = new OrthographicCamera();
        menuCamera.setToOrtho(false, 480, 800);
        viewPort = new FitViewport(RaidenRedux.W_WIDTH, RaidenRedux.W_HEIGHT, menuCamera);
        menuCamera.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0);
    }

    @Override
    public void show() {

    }

    public void update(float delta){
        menuCamera.update();
        Manager.batch.setProjectionMatrix(menuCamera.combined);

        //if screen tapped
        if (Gdx.input.isTouched()){
            game.setScreen(new PlayScreen(game));
            dispose();
        }
    }

    @Override
    public void render(float delta) {
        //clear screen
        Gdx.gl.glClearColor(0.1f, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //update
        update(delta);

        //render
        Manager.batch.begin();
        Manager.font.draw(Manager.batch, "RAIDEN REDUX", 140, 450);
        Manager.font.draw(Manager.batch, "TAP ANYWHERE TO BEGIN!", 50, 350);
        Manager.batch.end();


    }

    @Override
    public void resize(int width, int height) {
        viewPort.update(width, height);
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
