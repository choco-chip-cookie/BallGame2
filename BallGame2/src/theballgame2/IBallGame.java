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
public interface IBallGame {
    
    // начинаем новуюю игру
    void start();
    
    void restart();
    
    // получаем статус
    int getStatus();
    
    // установить статус
    void setStatus(int newStatus);
    
    boolean isGameFinish();
    
    Shape[] getShapeArr();
    
    Rect getRect();
    
    // получаем инфо о игре
    IGameStat getStat();
    
    void stopAllTimers();
    
    void startAllTimers();
}
