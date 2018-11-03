package model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiddleButtonAction extends JFrame implements ActionListener {

    private JTextArea textArea_left;

    public MiddleButtonAction(JTextArea textArea_left) {

        this.textArea_left = textArea_left;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textArea_left.removeAll();
        textArea_left.setText("");
    }
}
