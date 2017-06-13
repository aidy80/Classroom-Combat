/**
 * Created by h205p2 on 4/20/16.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
//Test
public class Menu {
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.red);
        g.drawString("CLASSROOM COMBAT", Game.WIDTH / 2, 100);

    }

}