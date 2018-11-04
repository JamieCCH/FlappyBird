package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class PipeDown extends ActorBeta {

    public PipeDown(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("flappybird_7.png");
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
