package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class PipeUp extends ActorBeta {

    public PipeUp(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("flappybird_12.png");
        setSpeed(150);
        setMotionAngle(180);

        this.setBoundaryRectangle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);

    }
}