package model;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

public class Calculator extends JFrame {

    private int sum = 0;
    private String exceptionMessage_1 = new TooShortNumberException().exceptionMessage();
    private String exceptionMessage_2 = new TooLongNumberException().exceptionMessage();
    private List<Long> trainNumber = new ArrayList<Long>();
    private List<Long> multipliedNumber = new ArrayList<Long>();
    private List<Long> outcome = new ArrayList<Long>(multipliedNumber);
    private JButton button_left = new JButton("Oblicz");
    private JButton button_middle = new JButton("Wyczyść");
    private JButton button_right = new JButton("Zapisz");
    private JTextField upperTextField = new JTextField();
    private JTextArea textArea_left = new JTextArea();
    private JTextArea textArea_right = new JTextArea();

    public JTextArea getTextArea_right() {
        return textArea_right;
    }

    public Calculator() {
        setTitle ("Rail Number Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame)e.getSource();

                int result = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to exit the application?",
                        "Exit Application",
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION)
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                if (result == JOptionPane.NO_OPTION)
                    JOptionPane.getRootFrame().dispose();

            }
        });
        setSize(1300, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setVisible(true);

        button_left.setBounds(40, 600, 200, 40);
        add(button_left);

        button_middle.setBounds(240, 600, 200, 40);
        add(button_middle);

        button_right.setBounds(440, 600, 200, 40);
        add(button_right);

        ActionListener actionListener_1 = new LeftButtonAction(textArea_left, textArea_right, upperTextField, sum,
                exceptionMessage_1, exceptionMessage_2, trainNumber, multipliedNumber, outcome);
        button_left.addActionListener(actionListener_1);

        ActionListener actionListener_2 = new MiddleButtonAction(textArea_left);
        button_middle.addActionListener(actionListener_2);

        ActionListener actionListener_3 = new RightButtonAction();
        button_right.addActionListener(actionListener_3);

        JLabel upperLabel = new JLabel("Wprowadź numer identyfikacyjny: ");
        upperLabel.setBounds(40, 50, 200, 20);
        add(upperLabel);

        JLabel leftLabel = new JLabel("Aktualne obliczenia: ");
        leftLabel.setBounds(150, 110, 200, 20);
        add(leftLabel);

        JLabel rightLabel = new JLabel("Zapisane wyniki: ");
        rightLabel.setBounds(800, 110, 200, 20);
        add(rightLabel);

        upperTextField.setBounds(290, 40, 250, 40);
        add(upperTextField);

        textArea_left.setBounds(40, 140, 600, 450);
        textArea_left.setBorder(new LineBorder(Color.BLACK));
        add(textArea_left);

        textArea_right.setBounds(660, 140, 500, 500);
        textArea_right.setBorder(new LineBorder(Color.BLACK));
        //add(textArea_right);

        JScrollPane scrollPane = new JScrollPane();
        //rightScrollPane.setPreferredSize(new Dimension(200,250));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane.setBounds(660, 140, 500, 500);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.getViewport().add(textArea_right);

        add(scrollPane);
        repaint();
    }

    public static void main(String[] args) {
        new Calculator();
    }
}