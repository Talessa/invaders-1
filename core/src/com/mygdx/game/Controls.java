package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Controls {
    public static boolean isLeftPressed1() {
        return Gdx.input.isKeyPressed(Input.Keys.A);
    }

    public static boolean isRightPressed1() {
        return Gdx.input.isKeyPressed(Input.Keys.D);
    }

    public static boolean isShootPressed1() {
        return Gdx.input.isKeyPressed(Input.Keys.W);
    }

    public static boolean isLeftPressed2() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT) ;
    }
    public static boolean isRightPressed2() {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT) ;
    }

    public static boolean isShootPressed2() {
        return Gdx.input.isKeyPressed(Input.Keys.UP);
    }
}
