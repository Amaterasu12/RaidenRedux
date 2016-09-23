package com.amaterasu12.raidenredux.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


/**
 * Created by Carl on 2/17/2016.
 */
public class Manager {
    public static final float MUSIC_VOLUME = 0.5f;
    public static final float SOUND_VOLUME = 0.7f;
    public static SpriteBatch batch;
    public static BitmapFont font;
    public static AssetManager manager;

    public static Texture bgPlay;
    public static Music bgm;

    public static Animation playerShipAnimation;
    public static Array<TextureRegion> playerShipFrames;

    public static Sprite enemyBlack4;

    public static Sprite phasorRed06;
    public static Sprite phasorGreen04;

    public static Sound phasorSound01;
    public static void create(){
        manager = new AssetManager();
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("KenFont.fnt"));
    }

    public static void load(){
        manager.load("purple.png", Texture.class);
        manager.load("bgm.mp3", Music.class);

        manager.load("sprites.txt", TextureAtlas.class);
        manager.load("laserRed06.png", Texture.class);
        manager.load("laserGreen04.png", Texture.class);

        manager.load("sfx_laser1.ogg", Sound.class);
        manager.finishLoading();
    }

    public static void done(){
        bgPlay = manager.get("purple.png", Texture.class);
        bgPlay.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        bgm = manager.get("bgm.mp3", Music.class);
        bgm.setVolume(MUSIC_VOLUME);
        bgm.setLooping(true);

        playerShipFrames = new Array<TextureRegion>();
        for(int i = 0; i < 4; i++)
            playerShipFrames.add(new TextureRegion(manager.get("sprites.txt", TextureAtlas.class).findRegion("playerShip"), i*64, 0, 64, 64));
        playerShipAnimation = new Animation(0.1f, playerShipFrames);
        playerShipAnimation.setPlayMode(Animation.PlayMode.LOOP);

        enemyBlack4 = new Sprite(manager.get("sprites.txt", TextureAtlas.class).findRegion("enemyBlack4"));

        phasorRed06 = new Sprite(manager.get("laserRed06.png", Texture.class));
        phasorGreen04 = new Sprite(manager.get("laserGreen04.png", Texture.class));


        phasorSound01 = manager.get("sfx_laser1.ogg", Sound.class);
    }

    public static void dispose(){
        manager.dispose();
    }
}
