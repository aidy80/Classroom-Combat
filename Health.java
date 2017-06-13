import java.awt.*;

/**
 * Created by h205p2 on 5/23/16.
 */

public class Health {
    int playerNumber;
    int life;

    public Health(Character c)
    {
        playerNumber=c.playerNumber;
        life=c.getHealth();
    }

    boolean loss=false;

    public int getLife() {
        return life;
    }

    public void damage(int damage)
    {
        if (life-damage >=1)
        {
            life = life - damage;
        }
        else{
            life=0;
            loss=true;
        }

    }
    public void paint(Graphics2D g)
    {  if (loss == false)
        {
            int x = life;
            g.setColor(Color.GREEN);
            if(playerNumber==1) {
                g.fillRect(10, 10, 300, 20);
                g.setColor(Color.RED);
                g.fillRect(10+3*x, 10, 300-3*x, 20);
        }
            if(playerNumber==2) {
                g.fillRect(890, 10, 300, 20);
                g.setColor(Color.RED);
                g.fillRect(890+3*x, 10, 300-3*x, 20);
            }
        }
        else if (loss == true)
        {
            if (g instanceof Graphics2D)
            {
                Graphics2D g2d = (Graphics2D)g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.drawString("You Lose", 50, 50);
            }
        }
    }
}
