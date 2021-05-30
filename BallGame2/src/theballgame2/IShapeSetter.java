/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import java.util.ArrayList;

/**
 *
 * @author Apatuha
 */
@FunctionalInterface
public interface IShapeSetter {
    Shape setShape(Shape s, ArrayList<Shape> shapes);
}
