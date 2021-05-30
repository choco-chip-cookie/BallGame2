/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Apatuha
 */
public class Star extends Shape {

    final static int DEFAULTSPEED = 10;
    
    private Image img;

    public Star(Image img) {
        this.img = img;
        setSpeed(DEFAULTSPEED);
    }

    @Override
    public void draw(Graphics g) {
        if (!isInvisible()) {
            g.drawImage(img, getX(), getY(), getLen(), getLen(), null);
        }
    }
}
