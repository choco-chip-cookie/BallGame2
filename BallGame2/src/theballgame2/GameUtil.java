/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

/**
 *
 * @author Apatuha
 */
public class GameUtil {
    public static int getRandomNum(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }
    
    public boolean canFit(Interval i){
        return i.getDelta() >= BallGame.MinSize + BallGame.Delta;
    }
}
