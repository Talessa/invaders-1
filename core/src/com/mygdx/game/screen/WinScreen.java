package com.mygdx.game.screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceInvaders;

public class WinScreen extends SpaceInvadersScreen {

    SpriteBatch batch;
    BitmapFont font ;


    public WinScreen(SpaceInvaders game) {
        super(game);
    }

    @Override
    public void show() {
        font = new BitmapFont();
        batch = new SpriteBatch();

    }

    @Override
    public void render(float delta) {
        batch.begin();
        font.draw(batch,"Habéis Ganado",270, 280);
        batch.end();
    }


}
