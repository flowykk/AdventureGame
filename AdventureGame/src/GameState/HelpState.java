package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class HelpState extends GameState {

    private Background bg;

    private int currentChoice = 0;
    private String[] options = {"RESTART", "QUIT"
    };

    private Color optionsColor, nameColor, titleColor;
    private Font titleFont, nameFont;


    private Font font;

    public HelpState(GameStateManager gsm) {

        this.gsm = gsm;

        try {
            bg = new Background("/Backgrounds/waterfall.png", 1);
            bg.setVector(-0.05, 0);

            nameColor = new Color(255,255,255);
            optionsColor = new Color(81, 83, 194, 255);
            titleFont = new Font("AlundraText", Font.PLAIN, 35);
            font = new Font("Pixellari", Font.PLAIN, 30);

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
        g.setColor(optionsColor);
        g.setFont(titleFont);
        g.drawString("ADVENTURE GAME",150, 50 );

        g.setFont(font);
        g.setColor(Color.WHITE);
        Font fn = new Font("Pixellari", Font.PLAIN, 20);

        g.setFont(fn);

        g.drawString("You need to kill all ",380, 90 );
        g.drawString("enemies in every level",380, 110 );

        g.drawString("You will be",380, 155 );
        g.drawString("provided by tips",380, 175 );

        Font font1 = new Font("Arial", Font.BOLD, 20);
        g.setFont(font1);
        g.drawString("←, → ",20, 90 );

        g.setFont(fn);
        g.drawString(" - Moving",70, 90 );
        g.drawString("F - Iceball Attack",20, 110 );
        g.drawString("R - Sword Attack",20, 130 );
        g.drawString("R+F - All IceBalls Attack",20, 150 );
        g.drawString("E - Flying",20, 170 );
        g.drawString("W - Jump",20, 190 );

        g.drawString("Press ESC to return back",170, 230 );

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
        if(k == KeyEvent.VK_ESCAPE) {
            gsm.setState(GameStateManager.MENUSTATE);
        }

    }
    public void keyReleased(int k) {}

}