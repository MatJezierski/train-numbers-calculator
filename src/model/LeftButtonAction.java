package model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class LeftButtonAction extends JFrame implements ActionListener {

    private JTextArea textArea_left, textArea_right;
    private JTextField upperTextField;
    private int sum = 0;
    private String exceptionMessage_1 = new TooShortNumberException().exceptionMessage();
    private String exceptionMessage_2 = new TooLongNumberException().exceptionMessage();
    private List<Long> trainNumber = new ArrayList<Long>();
    private List<Long> multipliedNumber = new ArrayList<Long>();
    private List<Long> outcome = new ArrayList<Long>(multipliedNumber);

    public LeftButtonAction(JTextArea textArea_left, JTextArea textArea_right,
                            JTextField upperTextField, int sum, String exceptionMessage_1, String exceptionMessage_2,
                            List<Long> trainNumber, List<Long> multipliedNumber, List<Long> outcome) {
        this.textArea_left = textArea_left;
        this.textArea_right = textArea_right;
        this.upperTextField = upperTextField;
        this.sum = sum;
        this.exceptionMessage_1 = exceptionMessage_1;
        this.exceptionMessage_2 = exceptionMessage_2;
        this.trainNumber = trainNumber;
        this.multipliedNumber = multipliedNumber;
        this.outcome = outcome;
    }

    private String checkTheNumber() throws TooShortNumberException, TooLongNumberException {
            BigInteger initialNumber = BigInteger.valueOf(Long.parseLong(upperTextField.getText()));
            String givenNumber = String.valueOf(initialNumber);
            String checkNumber;
            if (givenNumber.length()<11){
                throw new TooShortNumberException();
            } else if (givenNumber.length()>11){
                throw new TooLongNumberException();
            } else {
                checkNumber = ("Podany numer, to: " + givenNumber);
                textArea_left.setText("");
                textArea_left.append(checkNumber);
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
    }
}
