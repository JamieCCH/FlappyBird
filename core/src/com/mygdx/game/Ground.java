package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Ground extends ActorBeta {

    public Ground(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("flappybird_11.png");
        setSpeed(150);
        setMotionAngle(180);

        this.setBoundaryRectangle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);

        if (getX()+getWidth() <= 0) {
            setPosition(getWidth(),0);
//            moveBy(2*getWidth(), 0);
        }
    }
}
