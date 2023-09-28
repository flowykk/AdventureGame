package Entity;


import TileMap.TileMap;

public class Enemy extends MapObject{

    protected int HP;
    protected int maxHealth;
    protected boolean dead;
    protected int damage;

    protected boolean flinching;
    protected long flinchTime;

    public Enemy(TileMap tm) {
        super(tm);
    }

    public boolean isDead() {
        return dead;
    }

    public int getDamage() {
        return damage;
    }

    public void hit(int damage) {
        if(dead || flinching) return;
        HP -= damage;
        if(HP < 0) HP = 0;
        if (HP == 0) dead = true;
        flinching = true;
        flinchTime = System.nanoTime();
    }

    public void update() {

    }

}