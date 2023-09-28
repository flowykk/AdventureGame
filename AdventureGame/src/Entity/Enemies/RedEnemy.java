package Entity.Enemies;

import Entity.Animation;
import Entity.Enemy;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RedEnemy extends Enemy {

    private BufferedImage[] sprites;

    public RedEnemy(TileMap tm) {
        super(tm);

        moveSpeed = 0.3;
        maxSpeed = 0.3;
        fallSpeed = 0.2;
        maxFallSpeed = 10.0;

        width = 30;
        height = 30;
        cwidth = 20;
        cheight = 20;

        HP = maxHealth = 2;
        damage = 1;

        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/redenemy.png"));

            sprites = new BufferedImage[3];
            for(int i = 0; i<sprites.length;i++) {
                sprites[i] = spritesheet.getSubimage(i*width, 0, width, height);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(150);

        right = true;
        facingRight = true;

    }

    private void getNextPosition() {

        if(left) {
            dx -= moveSpeed;
            if(dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        }
        else if(right) {
            dx += moveSpeed;
            if(dx > maxSpeed) {
                dx = maxSpeed;
            }
        }

        if(falling) {
            dy += fallSpeed;
            if(dy > 0) jumping = false;
        }

    }

    public void update() {
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        if(flinching) {
            long elapsed = (System.nanoTime() - flinchTime) / 1000000;
            if(elapsed > 400) {
                flinching = false;
            }
        }

        if(right && dx == 0) {
            right = false;
            left = true;
            facingRight = false;
        } else if(left && dx == 0){
            right = true;
            left = false;
            facingRight = true;
        }

        animation.update();

    }

    public void draw(Graphics2D g) {

        setMapPosition();

        super.draw(g);
    }

}
