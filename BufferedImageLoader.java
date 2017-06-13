/**
 * Created by h205p2 on 5/5/16.
 */
import java.awt.image.BufferedImage;
import java.awt.Image;

import javax.imageio.ImageIO;

import java.io.IOException;

import java.applet.Applet;

import java.net.URL;

import java.awt.Toolkit;


public class BufferedImageLoader {
    private BufferedImage image;

    public BufferedImage loadImage(String path) throws IOException{
        image = ImageIO.read(getClass().getResource(path));
        return image;
    }
}
