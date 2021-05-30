/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import java.util.ArrayList;
import static theballgame2.BallGame.MaxSize;
import static theballgame2.BallGame.MinSize;

/**
 *
 * @author Apatuha
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(false));
        shapes.add(new Circle(false));
        shapes.add(new Circle(false));
        shapes.add(new Square(false));
        shapes.add(new Spaceship(false));
        
        //
        
        int i = 0;
        
        
        System.out.println("Before:");
        for(Shape sh : shapes){
            sh.setLen(MinSize);
            sh.setX(MaxSize * i);
            sh.printX();
            i += 2;
        }
        
        ShapeSetter setter = new ShapeSetter();
        setter.setShape(shapes.get(0), shapes);
        
//        System.out.println("After:");
//        for(Shape sh : shapes){
//            sh.printX();
//        }
//        System.out.println("Details:");
//        for(int j = 0; j < shapes.size(); j++){
//            System.out.println("index: " + j);
//            System.out.println("x: " + shapes.get(j).getX());
//            System.out.println("length: " + shapes.get(j).getLen());
//        }
    }
    
}
