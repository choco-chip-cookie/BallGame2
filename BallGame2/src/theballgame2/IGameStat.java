/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Apatuha
 */
public interface IGameStat {
    
    int getTryCount();
    
    void setTryCount(int newTryCount);
    
    void decTryCount();
    
    boolean canDecTryCount();
    
    void setDecTryCount(boolean b);
    
//    ArrayList<ObjectCounter> getCounters();
    Map<String, Counter> getMap();
    
    void resetTouchCount();
    
    void incTouchCount(String name);
}
