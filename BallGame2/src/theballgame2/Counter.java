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
public class Counter {
    
    private int touchCount = 0;

    public Counter() {
    }
    
    public void incTouchCount(){
        touchCount++;
    }
    
    public void resetTouchCount(){
        touchCount = 0;
    }
    
    public int getTouchCount(){
        return touchCount;
    }
}
