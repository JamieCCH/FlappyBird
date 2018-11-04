package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Title extends ActorBeta {

    Animation<TextureRegion> titleFlappy;
    Sprite tittleBird;

    public Title(float x, float y, Stage s) {
        super(x, y, s);

        titleFlappy = loadTexture("flappybird_30.png");
        loadTexture("flappybird_31.png");
        setSpeed(150);
        setMotionAngle(180);


    }

    @Override
    public void act(float dt) {
        super.act(dt);

    }

}
