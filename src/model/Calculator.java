package model;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.*;
import java.util.List;

public class Calculator extends JFrame implements ActionListener {

    private int sum = 0;
    private String exceptionMessage_1 = new TooShortNumberException().exceptionMessage();
    private String exceptionMessage_2 = new TooLongNumberException().exceptionMessage();
    private List<Long> trainNumber = new ArrayList<Long>();
    private List<Long> multipliedNumber = new ArrayList<Long>();
    private List<Long> outcome = new ArrayList<Long>(multipliedNumber);
    private JButton button_left = new JButton("Oblicz");
    private JButton button_middle = new JButton("Wyczyść");
    private JButton button_right = new JButton("Zakończ");
    private JTextField upperTextField;
    private JTextArea textArea_left, textArea_right;


    public Calculator() {

        setSize(1300, 800);
        setResizable(false);
        setTitle("Rail Numbers Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        button_left.setBounds(40, 600, 200, 40);
        add(button_left);

        button_middle.setBounds(240, 600, 200, 40);
        add(button_middle);

        button_right.setBounds(440, 600, 200, 40);
        add(button_right);

        button_left.addActionListener(this);
        button_middle.addActionListener(this);
        button_right.addActionListener(this);

        JLabel upperLabel = new JLabel("Wprowadź numer identyfikacyjny: ");
        upperLabel.setBounds(40,50,200,20);
        add(upperLabel);

        JLabel leftLabel = new JLabel("Aktualne obliczenia: ");
        leftLabel.setBounds(150,110,200,20);
        add(leftLabel);

        JLabel rightLabel = new JLabel("Zapisane wyniki: ");
        rightLabel.setBounds(800,110,200,20);
        add(rightLabel);

        upperTextField = new JTextField();
        upperTextField.setBounds(290, 40, 250, 40);
        add(upperTextField);

        textArea_left = new JTextArea();
        textArea_left.setBounds(40, 140, 600, 450);
        textArea_left.setBorder(new LineBorder(Color.BLACK));
        add(textArea_left);

        textArea_right =new JTextArea();
        textArea_right.setBounds(660,140,500,500);
        textArea_right.setBorder(new LineBorder(Color.BLACK));
        //add(textArea_right);

        JScrollPane scrollPane = new JScrollPane();
        //rightScrollPane.setPreferredSize(new Dimension(200,250));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane.setBounds(660,140,500,500);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.getViewport().add(textArea_right);

        add(scrollPane);

        repaint();
        setVisible(true);

    }

    public static void main(String[] args) {
        new Calculator();
    }

    private String checkTheNumber() throws TooShortNumberException, TooLongNumberException{

        BigInteger initialNumber = BigInteger.valueOf(Long.parseLong(upperTextField.getText()));
        String givenNumber = String.valueOf(initialNumber);
        String checkNumber;

        if (givenNumber.length()<11){
            throw new TooShortNumberException();
        } else if (givenNumber.length()>11){
            throw new TooLongNumberException();
        } else {
            checkNumber = ("Podany numer, to: " + givenNumber);
            textArea_left.setText("Podany numer, to: " + givenNumber);
        }
        return checkNumber;
    }

    private void calculateTheNumber(){
        String fieldString = upperTextField.getText();
        long stringToLongValue = Long.valueOf(fieldString);

        for (long i=fieldString.length()-1; i>=0 ; i--) {
            long b = stringToLongValue %10;
            stringToLongValue = stringToLongValue /10;
            trainNumber.add(b);
        }
        Collections.reverse(trainNumber);

        System.out.println(trainNumber);

        for (int i = trainNumber.size() - 1; i >= 0; i--) {
            long multiplied;
            if (trainNumber.get(i) % 2 != 0) {
                multiplied = trainNumber.get(i) * 2;
            } else {
                multiplied = trainNumber.get(i);
            }
            multipliedNumber.add(multiplied);
        }
        Collections.reverse(multipliedNumber);
        textArea_left.append("\nNumer po przemnożeniu: " + multipliedNumber);

        for (ListIterator<Long> iter = multipliedNumber.listIterator(); iter.hasNext(); ) {
            Long s = iter.next();
            while (s >= 10) {
                long b = s % 10;
                s = s/10;
                outcome.add(1L);
                outcome.add(b);
            }
        }
        System.out.println(outcome);

        for (int i = 0; i <= multipliedNumber.size() - 1; i++) {
            if (multipliedNumber.get(i) >= 10) {
                multipliedNumber.remove(i);
            }
        }

        multipliedNumber.addAll(outcome);
        textArea_left.append("\nOtrzymane cyfry: " + multipliedNumber);
        System.out.println(multipliedNumber);

        for (long i : multipliedNumber) {
            sum += i;
        }

        int sum1 = sum % 10;
        int controlNumber = 10 - sum1;

        textArea_left.append("\nSuma otrzymanego zbioru cyfr: " + sum);
        textArea_left.append("\nCyfra samokontroli to: " + controlNumber + "\n\n");

        String abc = textArea_left.getText();
        textArea_right.append(abc);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == button_left) {
            try {
                textArea_left.setText("");
                checkTheNumber();
                calculateTheNumber();
            }
            catch (IllegalArgumentException exc) {
//               sprawdzić czy liczba
                textArea_left.setText("");
                exc.getStackTrace();
                textArea_left.setText("The given string is not a number. Try again.");
            }
            catch (TooShortNumberException exc_1){
                    textArea_left.append(exceptionMessage_1);
            }
            catch (TooLongNumberException exc_2){
                    textArea_left.append(exceptionMessage_2);
            }

            repaint();

        }       else if (source == button_middle){
                    textArea_left.removeAll();
                    textArea_left.setText("");
                }
                else if (source == button_right) {
                    dispose();
                }
            }
        }