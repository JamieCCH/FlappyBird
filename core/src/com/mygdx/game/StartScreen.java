package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class StartScreen extends ScreenBeta {

    ActorBeta background, startBtn, scoreBtn, titleFlappy, titleBird;
    Plane bird;

    Ground ground;
    Ground ground2;

    static Ground[] grounds;

    float stageCentre,stageCentreY;


    public void initialize()
    {
        ActorBeta.setWorldBounds(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stageCentre = mainStage.getWidth()/2;
        stageCentreY = mainStage.getHeight()/2;

        background = new ActorBeta(0,0, mainStage);
        background.loadTexture("flappybird_0.png");
        background.setSize(mainStage.getWidth(),mainStage.getHeight());

        ground = new Ground(0, 0, mainStage);
        ground.setSize(mainStage.getWidth(),100);
        ground.setBoundaryRectangle();

        ground2 = new Ground(ground.getWidth(), 0, mainStage);
        ground2.setSize(mainStage.getWidth(),100);
        ground2.setBoundaryRectangle();

        grounds = new Ground[] {ground, ground2};

        startBtn = new ActorBeta(stageCentre - 250,150, mainStage);
        startBtn.loadTexture("flappybird_41.png");
        startBtn.setSize(180,80);

        scoreBtn = new ActorBeta(stageCentre + 70,150, mainStage);
        scoreBtn.loadTexture("flappybird_32.png");
        scoreBtn.setSize(180,80);

        titleFlappy = new ActorBeta(stageCentre - 365,stageCentreY+100, mainStage);
        titleFlappy.loadTexture("flappybird_30.png");
        titleFlappy.setSize(350,130);

        titleBird = new ActorBeta(stageCentre ,stageCentreY+120, mainStage);
        titleBird.loadTexture("flappybird_31.png");
        titleBird.setSize(250,100);

        bird = new Plane();
        bird.setPosition(titleBird.getX()+titleBird.getWidth()+45, stageCentreY+170);
        bird.setScale(5.0f);
        mainStage.addActor(bird);

        startBtn.addListener(new ActorGestureListener() {
            @Override
            public void touchDown (InputEvent event, float x, float y, int pointer, int
                    button) {
                super.touchDown(event, x, y, pointer, button);
                startBtn.setY(145);
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int
                    button) {
                super.touchDown(event, x, y, pointer, button);
                MyGame.setActiveScreen(new LevelOne());
            }

        });

    }

    public void update(float dt)
    {
        titleFlappy.applyPhysics(dt);
        titleBird.applyPhysics(dt);
        bird.applyPhysics(dt);

        titleFlappy.setAcceleration(100);
        titleFlappy.accelerateAtAngle(270);

        titleBird.setAcceleration(100);
        titleBird.accelerateAtAngle(270);

        bird.setAcceleration(100);
        bird.accelerateAtAngle(270);


        if(bird.getY() <= stageCentreY+150){
            bird.setSpeed(50);
            bird.setMotionAngle(90);

            titleFlappy.setSpeed(50);
            titleFlappy.setMotionAngle(90);

            titleBird.setSpeed(50);
            titleBird.setMotionAngle(90);
        }

    }

}
