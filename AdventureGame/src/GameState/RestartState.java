package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RestartState extends GameState {

    private Background bg;

    private int currentChoice = 0;
    private String[] options = {"RESTART", "QUIT"
    };

    private Color optionsColor, nameColor, titleColor;
    private Font titleFont, nameFont;

    private Font font, font1;

    public RestartState(GameStateManager gsm) {

        this.gsm = gsm;

        try {
            bg = new Background("/Backgrounds/waterfall.png", 1);
            bg.setVector(-0.05, 0);

            nameColor = new Color(255,255,255);
            optionsColor = new Color(81, 83, 194, 255);
            titleColor = new Color(81, 83, 194, 255);
            font = new Font("Pixellari", Font.BOLD, 30);
            font1 = new Font("Pixellari", Font.BOLD, 60);

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
        g.setFont(font1);
        g.drawString("You Win!", 40, 90);

        g.setFont(font);
        for(int i = 0; i < options.length; i++) {
            if(i == currentChoice) {
                g.setColor(Color.WHITE);
            }
            else {
                g.setColor(optionsColor);
            }
            g.drawString(options[i], 360, 60 + i * 40);
        }
    }

    private void select() {
        if(currentChoice == 0) {
            gsm.setState(GameStateManager.LEVEL1STATE);
        }
        if(currentChoice == 1) {
            System.exit(0);
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