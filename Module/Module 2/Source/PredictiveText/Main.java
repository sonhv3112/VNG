package PredictiveText;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;

import PredictiveText.Dictionary.*;

public class Main { 
    static JFrame frame; 
    static JTextField textField;
    static JTextArea textArea; 
    static Dictionary dict = null; 
    
    public static void createGUI() { 
        frame = new JFrame("Predictive text");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);

        textField = new JTextField(); 
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                System.out.println("Change");
            }
            
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            public void insertUpdate(DocumentEvent e) {
                search();
            }

            public void search() { 
                System.out.println("[?] " + textField.getText());
                List<String> listString = dict.searchKNeareastString(textField.getText(), 5); 
                StringBuilder result = new StringBuilder("Top " + listString.size() + " word neareast\n"); 
                for (String str : listString) { 
                    result.append(str + "\n");
                }
                textArea.setText(result.toString());
            }
        }); 

        textArea = new JTextArea();
        textArea.setEditable(false);

        textField.setBounds(0, 0, 300, 50);
        textArea.setBounds(0, 50, 300, 250);

        frame.getContentPane().add(textField);
        frame.getContentPane().add(textArea); 

        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) { 
        if (args.length != 1) 
            return; 

        long startTimeAll = System.currentTimeMillis(); 

        try { 
            dict = new TrieDictionary(args[0]);
        } catch (FileNotFoundException e) { 
            System.out.println("File not found!");
        }

        if (dict != null) 
            System.out.println("[+] Build dictionary from file " + args[0] + " successful in " + (System.currentTimeMillis() - startTimeAll) + "ms"); 
        else { 
            System.out.println("[-] Build dictionary from file " + args[0] + " failed in " + (System.currentTimeMillis() - startTimeAll) + "ms"); 
            return; 
        }

        createGUI(); 
    }   
}