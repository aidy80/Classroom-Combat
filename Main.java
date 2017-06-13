import javax.swing.*;
import java.awt.*;

/**
 * Created by h205p2 on 4/27/16.
 */

public class Main {
    public static void main(String args[]) throws InterruptedException {
        final int WIDTH = 600;
        final int HEIGHT = WIDTH/14 * 9;
        final int SCALE = 2;

        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        while(true){
            game.tick();
            Thread.sleep(10);
        }

    }
}
