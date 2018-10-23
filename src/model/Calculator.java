package model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.*;

public class Calculator extends JFrame implements ActionListener {

    private int sum = 0;
    private List<Long> trainNumber = new ArrayList<Long>();
    private List<Long> multipliedNumber = new ArrayList<Long>();
    private List<Long> outcome = new ArrayList<Long>(multipliedNumber);
    private JButton button_left, button_right;
    private JTextField upperTextField;
    private JTextArea textArea;

    public Calculator() {

        setSize(700, 500);
        setTitle("Rail Numbers Calculator");
        setLayout(null);

        button_left = new JButton("Oblicz");
        add(button_left);
        button_left.setBounds(100, 300, 200, 40);

        button_right = new JButton("Zakończ");
        add(button_right);
        button_right.setBounds(300, 300, 200, 40);

        JLabel upperLabel = new JLabel("Wprowadź numer identyfikacyjny: ");
        add(upperLabel);
        upperLabel.setBounds(50,50,200,20);

        upperTextField = new JTextField();
        add(upperTextField);
        upperTextField.setBounds(300, 40, 250, 40);

        textArea = new JTextArea();
        add(textArea);
        textArea.setBounds(40, 100, 500, 150);

        button_left.addActionListener(this);
        button_right.addActionListener(this);
    }

    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculator.setVisible(true);

    }

    public String checkTheNumber(){
        BigInteger initialNumber = BigInteger.valueOf(Long.parseLong(upperTextField.getText()));
        String givenNumber = String.valueOf(initialNumber);
        String checkNumber;
        if (givenNumber.length()<11){
            checkNumber = ("The given number is too short. Try again.");
            textArea.setText("");
            textArea.append(checkNumber);
        } else if (givenNumber.length()>11){
            checkNumber = ("The given number is too long. Try again.");
            textArea.setText("");
            textArea.append(checkNumber);
        } else {
            checkNumber = ("Podany numer, to: " + givenNumber);
            textArea.setText("");
            textArea.append(checkNumber);
        }
        return checkNumber;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == button_left) {
            try {
                checkTheNumber();

                String fieldString = upperTextField.getText();
                long a = Long.valueOf(fieldString);

                for (long i=fieldString.length()-1; i>=0 ; i--) {
                    long b = a%10;
                    a = a/10;
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
                textArea.append("\nNumer po przemnożeniu: " + multipliedNumber);

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
                    for (;multipliedNumber.get(i) >= 10;) {
                        multipliedNumber.remove(i);
                    }
                }

                multipliedNumber.addAll(outcome);
                System.out.println(multipliedNumber);
                textArea.append("\nOtrzymane cyfry: " + multipliedNumber);

                for (long i : multipliedNumber) {
                    sum += i;
                }

                int sum1 = sum % 10;
                int controlNumber = 10 - sum1;

                textArea.append("\nSuma otrzymanego zbioru cyfr: " + sum);
                textArea.append("\nCyfra samokontroli to: " + controlNumber);
                repaint();

            } catch (IllegalArgumentException exc) {
                textArea.setText("");
                exc.getStackTrace();
                textArea.append("The given string is not a number. Try again.");
            }
        }
                else if (source == button_right) {
                    dispose();
                }
            }
        }