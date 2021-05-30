/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author Apatuha
 */
public class Okno extends JFrame implements ActionListener, KeyListener {

    private String name;

    private IBallGame game = new BallGame();

    private SettingsDialog dialog;

    public Okno(String name) {
        this.name = name;
        dialog = new SettingsDialog(this);
        addKeyListener(this);
        setFocusable(true);
        setTitle("ShapeDemo");
        setBounds(0, 0, 600, 400);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().add(createPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JPanel createPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        JPanel drawp = new DrawPanel(this, name, game);

        JPanel navig = new JPanel(new FlowLayout(FlowLayout.RIGHT, 35, 20));

        //buttons
        JButton settings = new JButton("Settings");
        settings.addActionListener(this);

        JButton pause = new JButton("Pause");
        pause.addActionListener(this);

        //adding
        navig.add(pause);
        navig.add(settings);

        p.add(drawp, BorderLayout.CENTER);
        p.add(navig, BorderLayout.SOUTH);

        return p;
    }

    public void stopGameTimers() {
        game.stopAllTimers();
    }

    public void startGametimers() {
        game.startAllTimers();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Settings")) {
            dialog.setVisible(true);
            requestFocus();
        } else if (e.getActionCommand().equals("Pause")) {
            new PauseDialog(this);
            requestFocus();
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Rect r = game.getRect();
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            r.moveLeft();
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            r.moveRight();
            repaint();
        }
        if (game.getStatus() == Status.LAST_CHANCE) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                r.narrow();
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                r.widen();
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

}
