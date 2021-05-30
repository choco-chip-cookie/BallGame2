/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import java.util.Comparator;

/**
 *
 * @author Apatuha
 */
public class ShapeComparator implements Comparator<Shape>{

    @Override
    public int compare(Shape s1, Shape s2) {
//        if(s1.getX() < s2.getX()){
//            return -1;
//        } else if(s1.getX() > s2.getX()){
//            return 1;
//        }
//        return 0;
        return  s1.getX() - s2.getX();
    }
    
}
