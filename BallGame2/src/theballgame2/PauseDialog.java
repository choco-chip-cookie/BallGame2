/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Apatuha
 */
public class PauseDialog extends JDialog implements ActionListener{
    
    Okno okno;
    
    public PauseDialog(Okno okno) {
        super(okno, "Settings", true);
        this.okno = okno;
        okno.stopGameTimers();
        getContentPane().add(createPanel());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(260, 120);
        setLocationRelativeTo(okno);
        setVisible(true);
    }

    public JPanel createPanel() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(0, 1));
//        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        JLabel l = new JLabel("<html>" + "Продолжить?" + "</html>");
        JButton resume = new JButton("Да!");
        resume.addActionListener(this);

        p.add(l);
        p.add(resume);

        return p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        okno.startGametimers();
        dispose();
    }
    
}
