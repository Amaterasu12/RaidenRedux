package com.amaterasu12.raidenredux.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


/**
 * Created by Carl on 2/17/2016.
 */
public class Manager {
    public static SpriteBatch batch;
    public static BitmapFont font;
    public static AssetManager manager;

    public static Texture bgPlay;
    public static Music bgm;

    public static Animation playerShipAnimation;
    public static Array<TextureRegion> playerShipFrames;

    public static void create(){
        manager = new AssetManager();
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("kenvector_future_thin.fnt"));
    }

    public static void load(){
        manager.load("blue.png", Texture.class);
        manager.load("bgm.mp3", Music.class);

        manager.load("sprites.txt", TextureAtlas.class);

        manager.finishLoading();
    }

    public static void done(){
        bgPlay = manager.get("blue.png", Texture.class);
        bgPlay.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        bgm = manager.get("bgm.mp3", Music.class);
        bgm.setVolume(0.5f);
        bgm.setLooping(true);

        playerShipFrames = new Array<TextureRegion>();
        for(int i = 0; i < 4; i++)
            playerShipFrames.add(new TextureRegion(manager.get("sprites.txt", TextureAtlas.class).findRegion("playerShip"), i*64, 0, 64, 64));
        playerShipAnimation = new Animation(0.1f, playerShipFrames);
        playerShipAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public static void dispose(){
        manager.dispose();
    }
}
