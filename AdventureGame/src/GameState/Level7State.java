package GameState;

import Entity.*;
import Entity.Enemies.Rocket;
import Entity.Enemies.Slugger;
import Main.GamePanel;
import TileMap.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Level7State extends GameState {

    private TileMap tileMap;
    // private Background sky;
    private Background one;
    private Background two;
    private Background three;
    private Background four;
    private Background five;
    private int enemycount;


    private CosmosPlayer player;

    private ArrayList<Enemy> enemies;
    private ArrayList<Explosion> explosions;

    private cHUD hud;
    private BufferedImage hageonText;

    private boolean eventDead;

    public Level7State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {

        one = new Background("/Backgrounds/1.png", 0.0);
        two = new Background("/Backgrounds/2.png", 0.12);
        three = new Background("/Backgrounds/cosmos.png", 0.2);
        //four = new Background("/Backgrounds/4.png", 0.2);
        //five = new Background("/Backgrounds/5.png", 0.2);


        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/cosmos.png");
        tileMap.loadMap("/Maps/level7.map");
        tileMap.setPosition(0, 0);
        tileMap.setTween(1);



        player = new CosmosPlayer(tileMap);
        player.setPosition(100,200);

        populateEnemies();

        explosions = new ArrayList<Explosion>();

        hud = new cHUD(player);

    }

    private void populateEnemies() {

        enemies = new ArrayList<Enemy>();

        Rocket s;

        Point[] points = new Point[] {
            new Point(300, 200),
            new Point(1450, 200),
            new Point(1470, 200),
            new Point(1740, 200),
            new Point(1760, 200),
            new Point(3090,160),
            new Point(3110,160),
            new Point(1125,120),
            new Point(1145,120),

        };
        for(int i = 0; i < points.length; i++) {
            s = new Rocket(tileMap);
            s.setPosition(points[i].x, points[i].y);
            enemies.add(s);
        }
    }

    public void update() {
        player.update();
        tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());

        three.setPosition(tileMap.getx(), tileMap.gety());
        two.setPosition(tileMap.getx(), tileMap.gety());
        player.checkAttack(enemies);

        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.update();
            if(e.isDead()) {
                enemies.remove(i);
                enemycount += 1;
                i--;
                explosions.add(new Explosion(e.getx(), e.gety()));
            }
        }

        for(int i = 0; i < explosions.size(); i++) {
            explosions.get(i).update();
            if(explosions.get(i).shouldRemove()) {
                explosions.remove(i);
                i--;
            }
        }


        if(player.getHealth() == 0) { enemycount = 0;gsm.setState(GameStateManager.LEVEL7STATE);  }

        if(player.getx() == 3170 && player.gety() == 170 && enemycount == 9) {gsm.setState(GameStateManager.LEVEL8STATE );}

        if(player.gety() >= 222) { enemycount = 0; gsm.setState(GameStateManager.LEVEL7STATE); }
    }

    public void draw(Graphics2D g) {

        one.draw(g);
        two.draw(g);
        three.draw(g);

        tileMap.draw(g);

        player.draw(g);

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        for (Explosion explosion : explosions) {
            explosion.setMapPosition((int) tileMap.getx(), (int) tileMap.gety());
            explosion.draw(g);
        }

        hud.draw(g);

        g.drawString("Level 7", 525,20);

        g.drawString("enemies: " + enemycount + "/9", 10,55);

    }

    public void keyPressed(int k) {
        if(k == KeyEvent.VK_LEFT) player.setLeft(true);
        if(k == KeyEvent.VK_RIGHT) player.setRight(true);
        if(k == KeyEvent.VK_UP) player.setUp(true);
        if(k == KeyEvent.VK_DOWN) player.setDown(true);
        if(k == KeyEvent.VK_W) player.setJumping(true);
        if(k == KeyEvent.VK_E) player.setGliding(true);
        if(k == KeyEvent.VK_R) player.setScratching();
        if(k == KeyEvent.VK_F) player.setFiring();
    }

    public void keyReleased(int k) {
        if(k == KeyEvent.VK_LEFT) player.setLeft(false);
        if(k == KeyEvent.VK_RIGHT) player.setRight(false);
        if(k == KeyEvent.VK_UP) player.setUp(false);
        if(k == KeyEvent.VK_DOWN) player.setDown(false);
        if(k == KeyEvent.VK_W) player.setJumping(false);
        if(k == KeyEvent.VK_E) player.setGliding(false);
    }

}
