package com.amaterasu12.raidenredux.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Carl on 2/17/2016.
 */
public class Manager {
    public static SpriteBatch batch;
    public static BitmapFont font;
    public static AssetManager manager;

    public static Texture bgPlay;
    public static Music bgm;

    public static void create(){
        manager = new AssetManager();
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("assets/kenvector_future_thin.fnt"));
    }

    public static void load(){
        manager.load("assets/blue.png", Texture.class);
        manager.load("assets/bgm.mp3", Music.class);
        manager.finishLoading();
    }

    public static void done(){
        bgPlay = manager.get("assets/blue.png", Texture.class);
        bgPlay.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        bgm = manager.get("assets/bgm.mp3", Music.class);
        bgm.setVolume(0.5f);
        bgm.setLooping(true);
    }

    public static void dispose(){
        manager.dispose();
    }
}
