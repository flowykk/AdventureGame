package Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class pHUD {

    private RedPlayer player;

    private BufferedImage heart;
    private BufferedImage life;

    private Font font;

    public pHUD(RedPlayer p) {
        player = p;
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/HUD/test.png"));
            heart = image.getSubimage(0, 0, 13, 12);
            life = image.getSubimage(0, 12, 12, 11);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < player.getHealth(); i++) {
            g.drawImage(heart, 10 + i * 15, 10, null);
        }
        for(int i = 0; i < player.getFire()/200; i++) {
            g.drawImage(life, 10 + i * 15, 25, null);
        }
        g.setColor(java.awt.Color.WHITE);
        g.setFont(font = new Font("Pixellari", Font.PLAIN, 16));

        g.drawString(player.getTimeToString(), 525, 40);
    }
}