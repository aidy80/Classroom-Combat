/**
 * Created by h205p2 on 4/22/16.
 */


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends Canvas{
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "2D Classroom Combat";
    ArrayList<Character> characters = new ArrayList<Character>();
    ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    ArrayList<Health> healths = new ArrayList<Health>();

    private BufferedImage backImage;
    private BufferedImage s;
    private SpriteSheet spriteSheet = null;


    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                for (Character player : characters) {
                    player.keyReleased(e);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                for (Character player : characters) {
                    player.keyPressed(e);
                }
            }
        });
        setFocusable(true);

        Character playerOne = new Character(this,1, "RyuBetter.png");
        Character playerTwo = new Character(this,2, "KenBetter.png");
        characters.add(playerOne);
        characters.add(playerTwo);
        Health playerOneHealth = new Health(playerOne);
        Health playerTwoHealth = new Health(playerTwo);
        healths.add(playerOneHealth);
        healths.add(playerTwoHealth);
        BufferedImageLoader loader = new BufferedImageLoader();
        try {

            s = loader.loadImage("back.jpg");

        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteSheet = new SpriteSheet(s);
        //init();
    }

    //public void init() {
        //BufferedImageLoader loader = new BufferedImageLoader();
        //try {

            //spriteSheet = loader.loadImage("MM-MegamanXP2.png");

        //} catch (IOException e) {
            //e.printStackTrace();
        //}

        //SpriteSheet s = new SpriteSheet(spriteSheet);
    //}

    public void tick(){
        move();
        repaint();
        for(Character player: characters){
            player.tick();
        }
    }

    public void move(){
        for(Character player: characters){
            player.move();
        }
        for(Projectile x: projectiles){
            x.move();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.setColor(Color.RED);
        g.drawImage(spriteSheet.grabImage(0,0,1024,768),0,0, WIDTH * SCALE, HEIGHT * SCALE,null);
        for(Character player: characters){
            player.paint(g2d);
        }
        for(Projectile x: projectiles){
            x.paint(g2d);
        }
        for(Health x: healths){
            x.paint(g2d);
        }
    }
}
