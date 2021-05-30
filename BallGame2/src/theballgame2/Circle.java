/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import java.awt.Color;
import java.awt.Graphics;
import static theballgame2.BallGame.BottomLine;

/**
 *
 * @author Apatuha
 */
public class Circle extends Shape {

    final static int DEFAULTSPEED = 10;

    public Circle() {
        setSpeed(DEFAULTSPEED);
    }
    
    public Circle(boolean invis){
        super(invis);
    }

    public Circle(int x, int y, Color color, int diam) {
        super(x, y, color, DEFAULTSPEED, diam);
    }

    @Override
    public void draw(Graphics g) {
        if (!isInvisible()) {
            g.setColor(getColor());
            g.fillOval(getX(), getY(), getLen(), getLen());
        }
    }
}
