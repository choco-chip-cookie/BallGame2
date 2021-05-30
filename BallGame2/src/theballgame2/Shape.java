/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.Timer;
import static theballgame2.BallGame.BottomLine;
import static theballgame2.BallGame.LeftLimit;
import static theballgame2.BallGame.MaxSize;
import static theballgame2.BallGame.MinSize;
import static theballgame2.BallGame.RightLimit;
import static theballgame2.BallGame.UpperLimit;
import static theballgame2.BallGame.maxColorValue;
import static theballgame2.BallGame.minColorValue;

/**
 *
 * @author Apatuha
 */
public abstract class Shape implements ActionListener {

    private int x, y, speed, len;
    private Color color;
    private Timer t = new Timer(200, this);
    private boolean goesDown = true;
    private boolean invisible = true;
    private static ShapeComparator comp = new ShapeComparator();

    public Shape() {
    }

    public Shape(boolean invis) {
        invisible = invis;
    }

    public Shape(int x, int y, Color color, int speed, int len) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.speed = speed;
        this.len = len;
    }

    public abstract void draw(Graphics g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Timer getT() {
        return t;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public boolean isGoingDown() {
        return goesDown;
    }

    public void reverse() {
        goesDown = !goesDown;
    }

    public boolean isInvisible() {
        return invisible;
    }

//    public void toggleVisibility() {
//        invisible = !invisible;
//    }
    public void setInvisible(boolean b) {
        invisible = b;
    }

    public void setT(Timer t) {
        this.t = t;
    }

    public void startTimer() {
        t.start();
    }

    public void stopTimer() {
        t.stop();
    }

    public void setTimerInitialDelay(int initDelay) {
        t.setInitialDelay(initDelay);
    }

    public void clear() {
        x = 0;
        y = UpperLimit;
    }

    public ArrayList<Interval> findIntervals(ArrayList<Shape> shapes) {
        ArrayList<Shape> visShapes = new ArrayList<>();
        for (Shape sh : shapes) {
            if (!sh.isInvisible() && sh != this) {
                visShapes.add(sh);
            }
        }

        System.out.println("Before sorting :");
        int num = 0;
        for (Shape s : visShapes) {
            System.out.println("Shape " + num + ": x = " + s.getX() + " len = " + s.getLen());
            num++;
        }
        // sorting
        Collections.sort(visShapes, comp);

        System.out.println("Visible shapes after sorting: ");
        num = 0;
        for (Shape s : visShapes) {
            System.out.println("Shape " + num + ": x = " + s.getX() + " len = " + s.getLen());
            num++;
        }
        ArrayList<Interval> freeSpace = new ArrayList<>();
        if (visShapes.get(0).getX() - LeftLimit > MaxSize) {
            freeSpace.add(new Interval(LeftLimit, visShapes.get(0).getX()));
        }
        System.out.println("isMoreThan1 = " + isMoreThan1(shapes));
        if (isMoreThan1(shapes)) {
            for (int i = 0; i < visShapes.size() - 1; i++) {
                if (visShapes.get(i + 1).getX() - (visShapes.get(i).getX() + visShapes.get(i).getLen()) > MaxSize) {
                    freeSpace.add(new Interval(visShapes.get(i).getX() + visShapes.get(i).getLen(), visShapes.get(i + 1).getX()));
                }
            }
        }
        if (RightLimit - (visShapes.get(visShapes.size() - 1).getX() + visShapes.get(visShapes.size() - 1).getLen()) > MaxSize) {
            freeSpace.add(new Interval(visShapes.get(visShapes.size() - 1).getX(), RightLimit));
        }

        return freeSpace;
    }

    public boolean canCompare(ArrayList<Shape> shapes) {
        for (Shape sh : shapes) {
            if (!sh.isInvisible()) {
                return true;
            }
        }
        return false;
    }

    public boolean isMoreThan1(ArrayList<Shape> shapes) {
        int visibleShapes = 0;
        for (Shape sh : shapes) {
            if (!sh.isInvisible()) {
                visibleShapes++;
            }
        }
        return visibleShapes >= 2;
    }

    public void setShape(ArrayList<Shape> shapes) {
        System.out.println(canCompare(shapes));
        if (canCompare(shapes)) {

            ArrayList<Interval> freeSpace = findIntervals(shapes);

            System.out.println("freeSpace.size() = " + freeSpace.size());
            int num = 0;
            for (Interval i : freeSpace) {
                System.out.println("Interval " + num + ": " + i.getLeft() + " - " + i.getRight());
                num++;
            }
            // есть вероятность попасть в тот же интервал
            boolean testXisFound = false;
            for (int j = 0; j <= freeSpace.size(); j++) {
                int random = GameUtil.getRandomNum(0, freeSpace.size());
                int i = 0;
                System.out.println("random = " + random);
                int rightBorder = 0;
                try {
                    rightBorder = freeSpace.get(random).getRight();
                } catch (Exception exc) {
                    exc.printStackTrace();
                    System.out.println(random);
                }
                int delta = (rightBorder - freeSpace.get(random).getLeft());
                if (delta > MaxSize) {
                    System.out.println("delta = " + delta);
                    int testX = GameUtil.getRandomNum(freeSpace.get(random).getLeft(), freeSpace.get(random).getRight());
                    System.out.println("testX = " + testX);
                    setX(testX);
                    int testLen = GameUtil.getRandomNum(MinSize, MaxSize);
                    setLen(testLen);
                    setColor(new Color(GameUtil.getRandomNum(minColorValue, maxColorValue), GameUtil.getRandomNum(minColorValue, maxColorValue), GameUtil.getRandomNum(minColorValue, maxColorValue)));
                    setY(UpperLimit);
                    testXisFound = true;
                    break;
                }
            }
            if (!testXisFound) {
                System.out.println("testX was not found !!!");
            }
        } else {
            setLen(GameUtil.getRandomNum(MinSize, MaxSize));
            setX(GameUtil.getRandomNum(LeftLimit, RightLimit - getLen()));
            setColor(new Color(GameUtil.getRandomNum(minColorValue, maxColorValue), GameUtil.getRandomNum(minColorValue, maxColorValue), GameUtil.getRandomNum(minColorValue, maxColorValue)));
            setY(UpperLimit);
        }
    }

    public void moveDown() {
        setY(getY() + getSpeed());
    }

    public void moveUp() {
        setY(getY() - getSpeed());
    }

    // нужно избавится от BottomLine
    public boolean reachedBottom() {
        return getY() + getLen() == BottomLine || getY() + getLen() > BottomLine;
    }

    public boolean touched(Rect r) {
        return reachedBottom() && (getX() < (r.getX() + r.getLen())) && (getX() > (r.getX() - getLen()));
    }

    public void printX() {
        System.out.println(x);
    }

    public void printLen() {
        System.out.println(len);
    }

    // нужно избавится от UpperLimit
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (getY() > BallGame.UpperLimit) {
            setInvisible(false);
        }

        if (isGoingDown()) {
            moveDown();
        } else {
            moveUp();
        }
    }
}
