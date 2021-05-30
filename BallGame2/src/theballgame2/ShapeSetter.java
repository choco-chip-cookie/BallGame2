/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import java.util.ArrayList;
import java.util.Collections;
import static theballgame2.BallGame.LeftLimit;
import static theballgame2.BallGame.MaxSize;
import static theballgame2.BallGame.MinSize;
import static theballgame2.BallGame.RightLimit;

/**
 *
 * @author Apatuha
 */
public class ShapeSetter implements IShapeSetter {

    private static ShapeComparator comp = new ShapeComparator();

    public ArrayList<Interval> findIntervals(ArrayList<Shape> shapes) {
        ArrayList<Shape> visShapes = new ArrayList<>();
        for (Shape sh : shapes) {
            if (!sh.isInvisible()) {
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

//    public void newSetter(ArrayList<Shape> shapes) {
//        
//
//        if (canCompare(shapes)) {
//            
//            ArrayList<Interval> freeSpace = findIntervals(shapes);
//
//            int random = GameUtil.getRandomNum(0, freeSpace.size());
//            int i = 0;
//            for (; i <= 10000;) {
//                int testX = GameUtil.getRandomNum(freeSpace.get(random).getLeft(), freeSpace.get(random).getRight() - (freeSpace.get(random).getRight() - freeSpace.get(random).getLeft()));
//                setX(testX);
//                setLen(GameUtil.getRandomNum(MinSize, freeSpace.get(random).getRight() - testX));
//            }
//            if (i >= 10000) {
//                setLen(GameUtil.getRandomNum(MinSize, MaxSize));
//                setX(GameUtil.getRandomNum(LeftLimit, RightLimit - getLen()));
//            }
//        } else {
//            standartSetter();
//        }
//    }
    @Override
    public Shape setShape(Shape s, ArrayList<Shape> shapes) {
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
                int delta = (freeSpace.get(random).getRight() - freeSpace.get(random).getLeft());
                if (delta > MaxSize) {
                    System.out.println("delta = " + delta);
                    int testX = GameUtil.getRandomNum(freeSpace.get(random).getLeft(), freeSpace.get(random).getRight());
                    System.out.println("testX = " + testX);
                    s.setX(testX);
                    testXisFound = true;
                    break;
                }
            }
            if(!testXisFound){
                System.out.println("testX was not found !!!");
            }
//            }
//            if (i >= 100) {
//                int randomX = GameUtil.getRandomNum(LeftLimit, RightLimit - s.getLen()); 
//                System.out.println("Exceptional randomizing, x: " + randomX);
//                s.setLen(GameUtil.getRandomNum(MinSize, MaxSize));
//                s.setX(randomX);
//            }
        } else {
            s.setLen(GameUtil.getRandomNum(MinSize, MaxSize));
            s.setX(GameUtil.getRandomNum(LeftLimit, RightLimit - s.getLen()));
        }

        return s;
    }
}
