package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by markapptist on 2018-09-26.
 */

public class MyGame extends GameBeta {

    LevelOne levelOne;
    StartScreen startScreen;

    boolean paused = false;

    @Override
    public void create() {

        super.create();

        levelOne = new LevelOne();

        startScreen = new StartScreen();

//        setScreen(levelOne);
        setScreen(startScreen);

    }

}
