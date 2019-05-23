package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Assets;


public class World {
    Space space;
    Ship ship;
    Ship ship2;
    AlienArmy alienArmy;
    HUD hud1;
    HUD hud2;
    public boolean lose=false;
    public boolean win = false;


    int WORLD_WIDTH, WORLD_HEIGHT;

    public World(int WORLD_WIDTH, int WORLD_HEIGHT){
        this.WORLD_WIDTH = WORLD_WIDTH;
        this.WORLD_HEIGHT = WORLD_HEIGHT;

        space = new Space();
        ship = new Ship(WORLD_WIDTH/4,1);
        ship2 = new Ship(WORLD_WIDTH/3,2);
        alienArmy = new AlienArmy(WORLD_WIDTH, WORLD_HEIGHT);
        hud1 = new HUD(String.valueOf(ship.life),WORLD_HEIGHT,1);
        hud2= new HUD(String.valueOf(ship2.life),WORLD_HEIGHT,2,200,285);
       // 384;
    }

    public void render(float delta, SpriteBatch batch, Assets assets){

        update(delta, assets);
        batch.begin();
        space.render(batch);
        ship.render(batch);
        ship2.render(batch);
        alienArmy.render(batch);
        hud1.render(batch);
        hud2.render(batch);
        batch.end();
    }

    void update(float delta, Assets assets){
        space.update(delta, assets);
        ship.update(delta, assets);
        ship2.update(delta,assets);
        alienArmy.update(delta, assets);
        checkCollisions(assets);
        winPlay();
        losePlay();
        hud1.life= String.valueOf(ship.life);
        hud2.life=String.valueOf(ship2.life);

    }

    private void checkCollisions(Assets assets) {
        checkNaveInWorld();
        checkShootsInWorld();
        checkShootsToAlien(assets);
        checkShootsToShip();
    }

    private void checkShootsToShip() {
        Rectangle shipRectangle = new Rectangle(ship.position.x, ship.position.y, ship.frame.getRegionWidth(), ship.frame.getRegionHeight());
        Rectangle shipRectangle2 = new Rectangle(ship2.position.x, ship2.position.y, ship2.frame.getRegionWidth(), ship2.frame.getRegionHeight());

        for(AlienShoot shoot: alienArmy.shoots){
            Rectangle shootRectangle = new Rectangle(shoot.position.x, shoot.position.y, shoot.frame.getRegionWidth(), shoot.frame.getRegionHeight());

            if (Intersector.overlaps(shootRectangle, shipRectangle)) {
                ship.damage();
                shoot.remove();
            }
            if (Intersector.overlaps(shootRectangle, shipRectangle2)) {
                ship2.damage();
                shoot.remove();
            }
        }
    }

    private void checkShootsToAlien(Assets assets) {
        for(Shoot shoot: ship.weapon.shoots){
            Rectangle shootRectangle = new Rectangle(shoot.position.x, shoot.position.y, shoot.frame.getRegionWidth(), shoot.frame.getRegionHeight());
            for(Alien alien: alienArmy.aliens){
                if(alien.isAlive()) {
                    Rectangle alienRectangle = new Rectangle(alien.position.x, alien.position.y, alien.frame.getRegionWidth(), alien.frame.getRegionHeight());

                    if (Intersector.overlaps(shootRectangle, alienRectangle)) {
                        alien.kill();
                        shoot.remove();
                        assets.aliendieSound.play();
                        hud1.score+=10;
                    }
                }
            }
        }
        for(Shoot shoot: ship2.weapon.shoots){
            Rectangle shootRectangle = new Rectangle(shoot.position.x, shoot.position.y, shoot.frame.getRegionWidth(), shoot.frame.getRegionHeight());
            for(Alien alien: alienArmy.aliens){
                if(alien.isAlive()) {
                    Rectangle alienRectangle = new Rectangle(alien.position.x, alien.position.y, alien.frame.getRegionWidth(), alien.frame.getRegionHeight());

                    if (Intersector.overlaps(shootRectangle, alienRectangle)) {
                        alien.kill();
                        shoot.remove();
                        assets.aliendieSound.play();
                        hud2.score+=10;
                    }
                }
            }
        }
    }

    private void checkShootsInWorld() {
        for(Shoot shoot: ship.weapon.shoots){
            if(shoot.position.y > WORLD_HEIGHT){
                shoot.remove();
            }
        }

        for(Shoot shoot: ship2.weapon.shoots){
            if(shoot.position.y > WORLD_HEIGHT){
                shoot.remove();
            }
        }

        for(AlienShoot shoot: alienArmy.shoots){
            if(shoot.position.y < 0){
                shoot.remove();
            }
        }
    }

    private void checkNaveInWorld() {
        if(ship.position.x > WORLD_WIDTH-32){
            ship.position.x = WORLD_WIDTH-32;
        } else if(ship.position.x < 0){
            ship.position.x = 0;
        }

        if(ship2.position.x > WORLD_WIDTH-32){
            ship2.position.x = WORLD_WIDTH-32;
        } else if(ship2.position.x < 0){
            ship2.position.x = 0;
        }
    }

    private void winPlay(){
        if (alienArmy.aliens.size==0){
           win=true;
           System.out.println("Habéis ganado");
         //  System.exit(0);
        }
    }

    private void losePlay(){

        if (ship.dead && ship2.dead ){
            lose=true;
            System.out.println("Habéis perdido");
           // System.exit(0);
        }

    }
}
