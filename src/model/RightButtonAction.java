package model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RightButtonAction extends JFrame implements ActionListener {

    public void exportTxt() {
        JFileChooser zapiszOkno = new JFileChooser();
        FileNameExtensionFilter zapiszFiltr = new FileNameExtensionFilter("Pliki tekstowe *.txt", "txt");
        zapiszOkno.setFileFilter(zapiszFiltr);

        JTextArea raport_txt = new JTextArea();
        Calculator calculator = new Calculator();

        raport_txt = calculator.getTextArea_right();
        int wynik_zapisz = zapiszOkno.showSaveDialog(zapiszOkno);
        File zapiszFile = zapiszOkno.getSelectedFile();
//      Sprawdzenie czy właczone jest zawijanie wierszy bo jeśli tak to trzeba to wyłączyć
        boolean stanZawijania = raport_txt.getLineWrap();
        raport_txt.setLineWrap(false);//brak zawjania
        String tekst = raport_txt.getText();
//      przechwytywanie wyjątku i zapis danych
        String adresZapisz1 = String.valueOf(zapiszFile).replace(".txt", "");
        if (wynik_zapisz != 0) {
            JOptionPane.showMessageDialog(null, "Nie podano / wskazano nazwy pliku.\nPrzerwano działanie funkcji Export...", "Export do 'Notatnik' format *.txt", 2);

        } else {
            int ile_wierszy = raport_txt.getLineCount();// odczyt ilości wierszy
//      zapis danych do pliku
            try {
                BufferedWriter zapisDane = new BufferedWriter(new FileWriter(adresZapisz1 + ".txt"));
                for (int i = 0; i < ile_wierszy; i++) {
                    int dd2 = raport_txt.getLineStartOffset(i);
                    int dd3 = raport_txt.getLineEndOffset(i);
                    String odczytanaLinia = tekst.substring(dd2, dd3);
                    zapisDane.write(odczytanaLinia);
                    zapisDane.newLine();
                }
                zapisDane.flush();
                zapisDane.close();//zamknięcie strumieni

            } catch (Exception e) {
                raport_txt.setLineWrap(stanZawijania);//brak zawjania
                System.err.println("Unable to create output file.");
                System.err.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Błąd utworzenia pliku *.odt !!!" + "\nBłąd: " + e.getMessage(), "UWAGA !!!", 2);
            }
            raport_txt.setLineWrap(stanZawijania);//brak zawjania
            JOptionPane.showMessageDialog(null, "Operacja Exportu wyników\nzakończona powodzeniem.", "Export do 'Notatnik' format *.txt", 1);
        }//koniec else
    }//koniec metody

    @Override
    public void actionPerformed(ActionEvent e) {
            exportTxt();
        }
    }
