package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Plane extends ActorBeta {

    Plane() {

        String[] planeAnim = {"flappybird_17.png", "flappybird_22.png"};

        loadAnimationFromFiles(planeAnim, 0.1f, true);

        this.setBoundaryRectangle();

        setMaxSpeed(800);

    }

    @Override
    public void act(float dt) {

        super.act(dt);

        setAcceleration(800);
//        accelerateAtAngle(270);
        applyPhysics(dt);

        boundToWorld();
   }

    public void boost() {
        setSpeed(600);
        setMotionAngle(90);
//        rotateBy(45);
    }
}
