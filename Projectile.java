/**
 * Created by h205p2 on 5/19/16.
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Projectile {
    int x;
    int y;
    int direction;
    int width=37;
    int height=28;
    Game game;
    private SpriteSheet spriteSheet = null;
    BufferedImage s;

    public Projectile(int StartX, int StartY, String spritePage, int direction, Game game){
        x=StartX;
        if(direction==2){
            x=StartX-300;
        }
        y=StartY-150;
        this.direction=direction;
        this.game=game;
        game.projectiles.add(this);
        BufferedImageLoader loader = new BufferedImageLoader();
        try {

            s = loader.loadImage(spritePage);

        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteSheet = new SpriteSheet(s);
    }

    public void move(){
        if(direction==2){
            x-=6;
        }

        if(direction==1){
            x+=6;
        }
    }

    public Rectangle getProjectileHitBox(){
        return new Rectangle(x, y, width, height);
    }

    public void paint(Graphics2D g){
        g.setColor(Color.blue);
        if(direction==1){
            g.drawImage(spriteSheet.grabImage(419, 652, 37, 28), x, y, width, height, null);
        }
        if(direction==2){
            g.drawImage(spriteSheet.grabImage(419,652,37,28), x , y,width,height, null);
        }
    }
}
