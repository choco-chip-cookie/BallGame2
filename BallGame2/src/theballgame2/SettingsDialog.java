
package theballgame2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Apatuha
 */
public class SettingsDialog extends JDialog implements ActionListener {

    JToggleButton toggle;
    JFrame frame;

    public SettingsDialog(JFrame frame) {
        super(frame, "Settings", true);
        this.frame = frame;
        getContentPane().add(createPanel());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(260, 120);
        setLocationRelativeTo(frame);
    }

    public JPanel createPanel() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(0, 1));
//        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        JLabel l = new JLabel("<html>" + "Нажмите, чтобы убрать/вернуть рамки у окна:" + "</html>");
        toggle = new JToggleButton("Нажмите сюда!");
        toggle.addActionListener(this);

        p.add(l);
        p.add(toggle);

        return p;
    }

    public void fullScreen(JFrame frame) {
        frame.dispose();
        frame.setUndecorated(false);
        frame.setVisible(true);
    }

    public void borderScreen(JFrame frame) {
        frame.dispose();
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!toggle.isSelected()) {
            fullScreen(frame);
            this.setVisible(false);
        } else {
            borderScreen(frame);
            this.setVisible(false);
        }
    }

}
