/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Apatuha
 */
public class ImageUtil {
    public static BufferedImage loadResourceImage(String fileName){
        // 
        try{
            return ImageIO.read(ImageUtil.class.getResourceAsStream(fileName));
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null; 
    }
    
    public static Image loadImage(String fileName) {
        try {
            return ImageIO.read(new File(fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
