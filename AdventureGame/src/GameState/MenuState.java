package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

    private Background bg;

    private int currentChoice = 0;
    private String[] options = {"START", "HELP", "QUIT"};

    private Color optionsColor, nameColor, titleColor;
    private Font titleFont, nameFont;

    private Font font;

    public MenuState(GameStateManager gsm) {

        this.gsm = gsm;

        try {

            bg = new Background("/Backgrounds/waterfall.png", 1);
            bg.setVector(-0.05, 0);

            nameColor = new Color(255,255,255);
            optionsColor = new Color(81, 83, 194, 255);
            titleColor = new Color(81, 83, 194, 255);
            titleFont = new Font("AlundraText", Font.PLAIN, 35);

            font = new Font("Pixellari", Font.PLAIN, 25);
            nameFont = new Font("Times New Roman", Font.PLAIN, 12);

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void init() {}

    public void update() {
        bg.update();
    }

    public void draw(Graphics2D g) {

        bg.draw(g);

        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("ADVENTURE GAME", 69, 85);
        g.setFont(nameFont);
        g.setColor(nameColor);
        g.drawString("2021 D. Rakhmanov", 5, 235);

        g.setFont(font);
        for(int i = 0; i < options.length; i++) {
            if(i == currentChoice) {
                g.setColor(Color.WHITE);
            }
            else {
                g.setColor(optionsColor);
            }
            g.drawString(options[i], 400, 52 + i * 30);
        }

    }

    private void select() {
        if(currentChoice == 0) {
            gsm.setState(GameStateManager.LEVEL1STATE);
         //   gsm.setState(GameStateManager.RESTARTSTATE);
        }
        if(currentChoice == 2) {
            System.exit(0);
        }
        if(currentChoice == 1) {
            gsm.setState(GameStateManager.HELPSTATE);
        }
    }

    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER){
            select();
        }
        if(k == KeyEvent.VK_UP) {
            currentChoice--;
            if(currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if(k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if(currentChoice == options.length) {
                currentChoice = 0;
            }
        }

    }
    public void keyReleased(int k) {}

}