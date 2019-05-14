package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.Controls;

public class Ship {

    enum State {
        IDLE, LEFT, RIGHT, SHOOT;
    }

    Vector2 position;

    int life;
    State state;
    float stateTime;
    float speed = 5;
    boolean dead= false;

    TextureRegion frame;

    Weapon weapon;

    int player;

    Ship(int initialPosition,int player){
        position = new Vector2(initialPosition, 10);
        state = State.IDLE;
        stateTime = 0;
        this.player=player;
        weapon = new Weapon();
        this.life=3;
    }



    void setFrame(Assets assets){
        switch (state){
            case IDLE:
                frame = assets.naveidle.getKeyFrame(stateTime, true);
                break;
            case LEFT:
                frame = assets.naveleft.getKeyFrame(stateTime, true);
                break;
            case RIGHT:
                frame = assets.naveright.getKeyFrame(stateTime, true);
                break;
            case SHOOT:
                frame = assets.naveshoot.getKeyFrame(stateTime, true);
                break;
            default:
                frame = assets.naveidle.getKeyFrame(stateTime, true);
                break;
        }
    }

    void render(SpriteBatch batch){
        if (!dead) {
            batch.draw(frame, position.x, position.y);

            weapon.render(batch);
        }
    }

    public void update(float delta, Assets assets) {
        stateTime += delta;

        if (player==1 && !dead) {
            if (Controls.isLeftPressed1()) {
                moveLeft();
            } else if (Controls.isRightPressed1()) {
                moveRight();
            } else {
                idle();
            }
            if (Controls.isShootPressed1()) {
                shoot();
                assets.shootSound.play();
            }
        }else if (player==2 && !dead){
            if (Controls.isLeftPressed2()) {
                moveLeft();
            } else if (Controls.isRightPressed2()) {
                moveRight();
            } else {
                idle();
            }
            if (Controls.isShootPressed2()) {
                shoot();
                assets.shootSound.play();
            }
        }

        setFrame(assets);

        weapon.update(delta, assets);
    }

    void idle(){
        state = State.IDLE;
    }

    void moveLeft(){
        position.x -= speed;
        state = State.LEFT;
    }

    void moveRight(){
        position.x += speed;
        state = State.RIGHT;
    }

    void shoot(){
        state = State.SHOOT;
        weapon.shoot(position.x +16);
    }

    public void damage() {
        if (!dead) {
            life--;
            System.out.println(life);
            if (life == 0) {
                dead = true;
            }
        }

    }
}
