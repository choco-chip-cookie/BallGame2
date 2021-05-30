   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Apatuha
 */
public class Spaceship extends Shape {

    final static int DEFAULTSPEED = 10;

    public Spaceship() {
        setSpeed(DEFAULTSPEED);
    }
    
    public Spaceship(boolean invis){
        super(invis);
    }

    public Spaceship(int x, int y, Color color, int len) {
        super(x, y, color, DEFAULTSPEED, len);
    }

    @Override
    public void draw(Graphics g) {
        if (!isInvisible()) {
            g.setColor(getColor());
            g.fillRect(getX(), getY(), getLen() / 4, getLen());
            g.fillRect(getX() + ((getLen() / 4) * 3), getY(), getLen() / 4, getLen());
            g.fillRect(getX(), getY() + getLen() / 2, getLen(), getLen() / 4);
        }
    }

}
