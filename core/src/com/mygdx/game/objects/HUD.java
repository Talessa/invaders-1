package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class HUD {
    BitmapFont font = new BitmapFont();
    static String VIDA="Vidas: ";
    String life;
    static String PUNTOS="Puntuacion: ";
    int score;
    int player;
    Vector2 positionLife = new Vector2();
    Vector2 positionScore = new Vector2();

    public HUD(String life, int WORLD_HEIGHT,int player) {
        this.player=player;
        this.life = life;
        this.score=0;
        this.positionLife.x = 10;
        this.positionLife.y = WORLD_HEIGHT;
        this.positionScore.x = 85;
        this.positionScore.y = WORLD_HEIGHT;
    }
    public HUD(String life, int WORLD_HEIGHT,int player, int lifeX,int scoreX) {
        this.player=player;
        this.life = life;
        this.score=0;
        this.positionLife.x = lifeX;
        this.positionLife.y = WORLD_HEIGHT;
        this.positionScore.x = scoreX;
        this.positionScore.y = WORLD_HEIGHT;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    void render(SpriteBatch batch){
        String vidas="J"+player+" "+VIDA+life;
        font.draw(batch,vidas,positionLife.x,positionLife.y);
        String puntos=PUNTOS+score;
        font.draw(batch,puntos,positionScore.x,positionScore.y);
    }


}
