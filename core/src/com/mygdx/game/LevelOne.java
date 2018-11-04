package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by markapptist on 2018-09-26.
 */

public class LevelOne extends ScreenBeta {

    Plane bird;
    float birdTempY;

    Ground ground;
    Ground ground2;

//    Sky sky;
//    Sky sky2;

    static Ground[] grounds;


    ActorBeta background;

    PipeUp pipeUp;
    PipeDown pipeDown;

    int pipeNum;
    PipeUp[] pipeUpArr;
    PipeDown[] pipeDownArr;
    float iteratedGape;
    float pipeWidth;
    float pipeHeight;
    float[] randomY;
    float gape;

    @Override
    public void initialize() {

//        sky = new Sky(0, 0, mainStage);
//        sky.setScale(3);

//        sky2 = new Sky(800, 0, mainStage);
//        sky2.setScale(3);

        background = new ActorBeta(0,0, mainStage);
        background.loadTexture("flappybird_0.png");
        background.setSize(mainStage.getWidth(),mainStage.getHeight());

        ground = new Ground(0, 0, mainStage);
        ground.setWidth(mainStage.getWidth());
        ground.setBoundaryRectangle();

        ground2 = new Ground(ground.getWidth(), 0, mainStage);
        ground2.setWidth(mainStage.getWidth());
        ground2.setBoundaryRectangle();

//        Gdx.app.log("ground width",""+ground.getWidth());

        grounds = new Ground[] {ground, ground2};

        ActorBeta.setWorldBounds(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        bird = new Plane();
        bird.setPosition(250, 700);
        bird.setScale(5.0f);
        bird.setBoundaryRectangle();

        mainStage.addActor(bird);

        PipeGenerator();

        defaultSoundEffect = Gdx.audio.newSound(Gdx.files.internal("sparkle.mp3"));
        defaultBackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Prelude-and-Action.mp3"));

       // defaultBackgroundMusic.setPosition(20.0f);
        defaultBackgroundMusic.play();
        defaultBackgroundMusic.setLooping(true);
    }

    public void PipeGenerator(){

        gape = 300;
        pipeUp = new PipeUp(mainStage.getWidth(), mainStage.getHeight(), mainStage);
        pipeUp.setScale(5.0f);
        pipeUp.setBoundaryRectangle();

        pipeWidth = pipeUp.getScaleX()*pipeUp.getWidth();
        pipeHeight = pipeUp.getScaleY()*pipeUp.getHeight();


        pipeDown = new PipeDown(pipeUp.getX(),(pipeUp.getY() - pipeHeight - gape), mainStage);
        pipeDown.setScale(5.0f);
        pipeDown.setBoundaryRectangle();


        iteratedGape = mainStage.getWidth()/3 + pipeWidth;

        pipeNum = 3;
        pipeUpArr = new PipeUp[pipeNum];
        pipeDownArr = new PipeDown[pipeNum];

        randomY = new float[pipeNum];

        for(int i = 0; i < pipeNum; i++){

            randomY[i]=  MathUtils.random(pipeHeight/2);

            pipeUpArr[i] = new PipeUp(pipeUp.getX()+iteratedGape*(i+1), mainStage.getHeight()-randomY[i], mainStage);
            pipeUpArr[i].setScale(5.0f);
            pipeUpArr[i].setBoundaryRectangle();

            pipeDownArr[i] = new PipeDown(pipeUp.getX()+iteratedGape*(i+1), (pipeUpArr[i].getY() - pipeHeight - gape), mainStage);
            pipeDownArr[i].setScale(5.0f);
            pipeDownArr[i].setBoundaryRectangle();

//            Gdx.app.log("randomY[i]: ",""+randomY[i]);
        }
    }

    @Override
    public void hide() {

    }

    @Override
    public void update(float dt) {

//        this.mainStage.getCamera().position.x = Gdx.graphics.getWidth()/2;
//        this.mainStage.getCamera().position.set(bird.getX(), this.mainStage.getCamera().position.y, this.mainStage.getCamera().position.z);
        ActorBeta.setWorldBounds(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        bird.accelerateAtAngle(270);

        for(int i = 0; i < grounds.length; i++) {
            if(bird.overlaps(grounds[i])) {
                bird.preventOverlap(grounds[i]);
            }
        }

        bird.preventOverlap(pipeUp);
        bird.preventOverlap(pipeDown);

        for(int i = 0; i < pipeNum; i++){
            bird.preventOverlap(pipeUpArr[i]);
            bird.preventOverlap(pipeDownArr[i]);

            if(pipeUpArr[i].getX()+pipeWidth <= 0){
                pipeUpArr[i].setX(mainStage.getWidth()+iteratedGape-pipeWidth);
                pipeDownArr[i].setX(mainStage.getWidth()+iteratedGape-pipeWidth);

                randomY = new float[pipeNum];
                randomY[i]=  MathUtils.random(pipeHeight/2);
                Gdx.app.log("randomY[i]: ",""+randomY[i]);

                pipeUpArr[i].setY(mainStage.getHeight()-randomY[i]);
                pipeDownArr[i].setY(pipeUpArr[i].getY() - pipeHeight - gape);

            }
        }


        if(bird.getY()<birdTempY && bird.getMotionAngle()>=265){
            bird.setRotation(-60);
        }

    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);

        bird.boost();

        if(bird.getRotation()<=-50){
            bird.setRotation(0);
        }

        bird.rotateBy(45);
        defaultSoundEffect.play(1.0f);

        birdTempY = bird.getY();

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        super.touchUp(screenX, screenY, pointer, button);

        bird.rotateBy(-45);


        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        super.touchDragged(screenX, screenY, pointer);

        return true;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }
}
