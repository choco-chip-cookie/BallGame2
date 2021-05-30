/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Apatuha
 */
public class BallGame implements IBallGame, ActionListener {

    final static int BottomLine = 950;
    final static int RectLength = 270;
    final static int RectWidth = 80;
    final static int minColorValue = 5;
    final static int maxColorValue = 250;
    final static int LeftLimit = 69;
    final static int RightLimit = 1801;
    final static int UpperLimit = 30;
    final static int DownLimit = BottomLine + RectWidth;
    final static int MinSize = 40;
    final static int MaxSize = 150;
    final static int Delta = 40;
    final static int RectStep = 20;
    final static int MaxTryCount = 3;

    // -1 - haven't started yet,  0 - start, 1 - missed the ball, 2 - finish
    private int gameStatus = Status.NOGAME;

    private Timer mainTimer = new Timer(20, this);
    
    private ArrayList<Shape> shapes = new ArrayList<>();
    private IGameStat stat;

//    private ArrayList<Circle> shapes = new ArrayList<>();
    private Rect r = new Rect(LeftLimit, BottomLine, Color.BLACK, RectStep, RectLength, RectWidth);

    public BallGame() {
        shapes.add(new Circle());
        shapes.add(new Star(ImageUtil.loadImage("c:/Images/Star.jpg")));
        shapes.add(new Star(ImageUtil.loadImage("c:/Images/Star.jpg")));
        shapes.add(new Star(ImageUtil.loadImage("c:/Images/Star.jpg")));
        shapes.add(new Star(ImageUtil.loadImage("c:/Images/Star.jpg")));
        shapes.add(new Circle());
        shapes.add(new Circle());
        shapes.add(new Square());
        shapes.add(new Spaceship());
        stat = new GameStat(MaxTryCount, shapes);
    }

    @Override
    public void start() {
        if(mainTimer.isRunning()){
            stopAllTimers();
            clearAll();
        }
        gameStatus = Status.RUNNING;
        stat.setTryCount(MaxTryCount);
        stat.resetTouchCount();
        
        for(Shape sh : shapes){
            sh.setShape(shapes);
        }
        
        for(Shape sh : shapes){
            System.out.println("x = " + sh.getX());
        }

        mainTimer.start();
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).getT().setInitialDelay(i * 6000);
        }
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).startTimer();
        }
    }

    @Override
    public void restart(){
        start();
    }

    @Override
    public int getStatus() {
        return gameStatus;
    }

    @Override
    public void setStatus(int newStatus) {
        if (newStatus == Status.GAMEOVER){
            stat.setTryCount(0);
        }
        gameStatus = newStatus;
    }

    @Override
    public boolean isGameFinish() {
        return getStatus() == Status.GAMEOVER;
    }
    
    @Override
    public Shape[] getShapeArr(){
        return shapes.toArray(new Shape[shapes.size()]);
    }

    @Override
    public Rect getRect() {
        return r;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        for (int i = 0; i < shapes.size(); i++) {
            Shape sh = shapes.get(i);
            if (sh.touched(r) && sh.isGoingDown()) {
                Toolkit.getDefaultToolkit().beep();
                stat.incTouchCount(sh.getClass().getName());
                sh.reverse();
            } else if (!sh.isGoingDown() && sh.getY() <= 30) {
                sh.setShape(shapes);
                sh.reverse();
            } else if (sh.isGoingDown() && sh.reachedBottom()) {
                if (stat.getTryCount() == 1) {
                    setStatus(Status.GAMEOVER);
                } else if (!isGameFinish()){
                    stat.decTryCount();
                    if(stat.getTryCount() == 1){
                        setStatus(Status.LAST_CHANCE);
                    }
                    sh.setShape(shapes);
                }
            }
        }
    }

    @Override
    public IGameStat getStat() {
        return stat;
    }
    
    @Override
    public void stopAllTimers(){
        mainTimer.stop();
        for(Shape sh: shapes){
            sh.stopTimer();
        }
        
    }
    
    @Override
    public void startAllTimers(){
        for(Shape sh: shapes){
            if(!sh.isInvisible()){
                sh.setTimerInitialDelay(0);
            }
            sh.startTimer();
        }
        mainTimer.start();
    }
    
    public void clearAll(){
        for(Shape sh: shapes){
            sh.clear();
            sh.setInvisible(true);
        }
    }
}
