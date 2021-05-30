/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Apatuha
 */
public class GameStat implements IGameStat {
    
    private int tryCount;
    private boolean decTryCount = true;
//    private ArrayList<ObjectCounter> counters = new ArrayList<>(); 
    private Map<String, Counter> m = new HashMap<>();
    
    public GameStat(int initialTryCount, ArrayList<Shape> shapes){
        tryCount = initialTryCount;
        for(Shape s : shapes){
            m.put(s.getClass().getName(), new Counter());
        }
    }
    
//    @Override
//    public ArrayList<ObjectCounter> getCounters(){
//        return counters;
//    }
    
    @Override
    public void resetTouchCount(){
        for(Counter c : m.values()){
            c.resetTouchCount();
        }
    }
    
    @Override 
    public Map<String, Counter> getMap(){
        return m;
    }
    
    @Override
    public int getTryCount() {
        return tryCount;
    }
    
    @Override
    public void setTryCount(int newTryCount) {
        tryCount = newTryCount;
    }
    
    @Override
    public void incTouchCount(String name){
        m.get(name).incTouchCount();
    }

    @Override
    public void decTryCount() {
        tryCount--;
    }
    
    @Override
    public boolean canDecTryCount() {
        return decTryCount;
    }
    
    @Override
    public void setDecTryCount(boolean b){
        decTryCount = b;
    }
    
    @Override
    public String toString(){
        String res = "";
        for(Counter num : m.values()){
            res += num + ", ";
        }
        return res;
    }
}
