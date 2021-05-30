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
public class Rect extends Shape {

    private int width;
    private int MinLength;
    private final int MaxLength = 400;
    private final int arg = 30;

    public Rect(int x, int y, Color color, int speed, int length, int width) {
        super(x, y, color, speed, length);
        MinLength = length;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void widen() {
        if (this.getLen() + arg <= MaxLength) {
            this.setLen(this.getLen() + arg);
        }
    }

    public void narrow() {
        if (this.getLen() - arg >= MinLength) {
            this.setLen(this.getLen() - arg);
        }
    }

    public boolean canMoveToLeft() {
        return getX() - BallGame.RectStep >= BallGame.LeftLimit;
    }

    public void moveLeft() {
        if (canMoveToLeft()) {
            setX(getX() - getSpeed());
        }
    }

    public boolean canMoveToRight() {
        return getX() + BallGame.RectLength + BallGame.RectStep <= BallGame.RightLimit;
    }

    public void moveRight() {
        if (canMoveToRight()) {
            setX(getX() + getSpeed());
        }
    }

    @Override
    public void draw(Graphics g) {
    }
}
