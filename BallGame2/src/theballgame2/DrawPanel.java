/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author Apatuha
 */
public class DrawPanel extends JPanel implements ActionListener, KeyListener {

    private IBallGame game;

    private Timer t;

    private String name;

    private Service service;

    private JFrame okno;

    public DrawPanel(JFrame frame, String name, IBallGame game) {
        this.name = name;
        this.game = game;
        frame = okno;
        service = new Service();
        t = new Timer(20, this);
        t.start();
        game.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape sh : game.getShapeArr()) {
            sh.draw(g);
        }
        Rect r = game.getRect();
        g.setColor(r.getColor());
        g.fillRect(r.getX(), r.getY(), r.getLen(), r.getWidth());
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 72));
        g.drawString("Имя игрока: " + name, BallGame.RightLimit + 70, 100);
        g.drawString("Кол - во попыток: " + game.getStat().getTryCount(), BallGame.RightLimit + 70, 200);
        if (game.getStatus() == Status.LAST_CHANCE) {
            g.setColor(Color.red);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
            g.drawString("Можете увеличить/уменьшить площадь ракетки!", BallGame.RightLimit + 70, 300);
        }
//        g.drawString("Кол - во попаданий: " + game.getStat().getTouchCount(), 2600, 380);
        g.setColor(Color.red);
        g.drawLine(BallGame.LeftLimit, BallGame.UpperLimit, BallGame.LeftLimit, BallGame.DownLimit);
        g.drawLine(BallGame.RightLimit, BallGame.DownLimit, BallGame.RightLimit, BallGame.UpperLimit);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (game.isGameFinish()) {
            System.out.println(game.getStat().toString());
            service.saveStats(name, game.getStat().getMap());
            t.stop();
            SwingUtilities.invokeLater(() -> {
                int i = JOptionPane.showConfirmDialog(okno, "Хотите переиграть ?", "Game over", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    game.restart();
                    t.start();
                    repaint();
                } else {
                    System.exit(0);
                }
            });
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }
}
