package GameState;

import Entity.*;
import Entity.Enemies.Slugger;
import Main.GamePanel;
import TileMap.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level8State extends GameState {

    private TileMap tileMap;
    private Background one;
    private Background two;
    private Background three;

    private CosmosPlayer player;

    private ArrayList<Enemy> enemies;
    private ArrayList<Explosion> explosions;

    private cHUD hud;

    public Level8State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        one = new Background("/Backgrounds/1.png", 0.0);
        two = new Background("/Backgrounds/2.png", 0.12);
        three = new Background("/Backgrounds/longcosmos.png", 0.2);

        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/cosmos.png");
        tileMap.loadMap("/Maps/level8.map");
        tileMap.setPosition(0, 0);
        tileMap.setTween(1);

        player = new CosmosPlayer(tileMap);
        player.setPosition(40,410);
        //player.setPosition(1124,170);

        populateEnemies();

        explosions = new ArrayList<Explosion>();

        hud = new cHUD(player);
    }

    private void populateEnemies() {

        enemies = new ArrayList<Enemy>();

        Slugger s;

        Point[] points = new Point[] {};
        for(int i = 0; i < points.length; i++) {
            s = new Slugger(tileMap);
            s.setPosition(points[i].x, points[i].y);
            enemies.add(s);
        }
    }

    @Override
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

       // System.out.println(player.getx() + " " + player.gety());

        if(player.getx() == 710 && player.gety() == 170) {gsm.setState(GameStateManager.RESTARTSTATE);}

    }

    @Override
    public void draw(Graphics2D g) {
        one.draw(g);
        two.draw(g);
        three.draw(g);

        tileMap.draw(g);

        player.draw(g);

        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }

        for(int i = 0; i < explosions.size(); i++) {
            explosions.get(i).setMapPosition((int)tileMap.getx(),(int)tileMap.gety());
            explosions.get(i).draw(g);
        }

        hud.draw(g);

        g.drawString("Level 8", 525,20);
    }

    @Override
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
        if(k == KeyEvent.VK_ESCAPE) gsm.setState(GameStateManager.LEVEL8STATE);
    }
}