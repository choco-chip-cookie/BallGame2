/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import javax.swing.JOptionPane;

/**
 *
 * @author Apatuha
 */
public class TheBallGame2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (;;) {
            String name = JOptionPane.showInputDialog(null, "Введите имя игрока:\n(CANCEL - выйти из игры)", "Имя игрока", JOptionPane.QUESTION_MESSAGE);
            if (name.isBlank()) {
                for (;;) {
                    int result = JOptionPane.showInternalConfirmDialog(null, "Имя не введено!\nOK - попробовать ещё раз; CANCEL - выйти из игры.", "Ошибка!", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.CANCEL_OPTION) {
                        System.exit(0);
                    } else if(result == JOptionPane.OK_OPTION){
                        break;
                    }
                }
            } else {
                Okno a = new Okno(name);
                break;
            }
        }
    }

}
