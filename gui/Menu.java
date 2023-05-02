package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Random;

class Database {
    public static Connection getConnection() throws Exception {
        try {
            String url = "jdbc:mysql://localhost:3306/BankingApp";
            String username = "root";
            String password = "";
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
class variables {
    String first;
    String last;
    Random rand = new Random();
    int min = 1000000;
    int max = 9999999;
    int id = rand.nextInt((max - min) + 1) + min;
    int balance = 0;
    int userid = 0;
}

public class Menu {
    public void menus() {
        String[] button = {"Menu","Add Account", "Remove Account", "Display Account Details",
                "Withdraw From Account", "Deposit Into Account", "Exit"};

        JLabel welcome, option;
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        panel.setLayout(new GridLayout(12,0));
        panel.setBorder(new EmptyBorder(10,10,10,10));
        JFrame frame = new JFrame("Banking System - Main Menu");

        welcome = new JLabel("Welcome To The Banking System");
        welcome.setHorizontalAlignment(JLabel.CENTER);
        panel.add(welcome);

        option = new JLabel("Please Pick An Option");
        option.setHorizontalAlignment(JLabel.CENTER);
        panel.add(option);
        panel.add(panel1);

        for (int i = 0; i < button.length; i++) {
            JButton button1 = new JButton((i + 1) + ": " + button[i]);
            button1.setActionCommand(button[i]); //Set the action// command to the button text
            panel.add(button1);
            button1.addActionListener(e -> {
                String command = e.getActionCommand();
                switch (command) {
                    case "Menu" -> {
                        Menu menu = new Menu();
                        menu.menus();
                        frame.setVisible(false);
                    }
                    case "Add Account" -> {
                        AddAccount acc = new AddAccount();
                        acc.addacc();
                        frame.setVisible(false);
                    }
                    case "Remove Account" -> {
                        RemoveAccount remacc = new RemoveAccount();
                        remacc.remacc();
                        frame.setVisible(false);
                    }
                    case "Display Account Details" -> {
                        DisplayAccount disacc = new DisplayAccount();
                        disacc.disacc();
                        frame.setVisible(false);
                    }
                    case "Withdraw From Account" -> {
                        WithdrawAccount withacc = new WithdrawAccount();
                        withacc.withacc();
                        frame.setVisible(false);
                    }
                    case "Deposit Into Account" -> {
                        DepositAccount depacc = new DepositAccount();
                        depacc.depacc();
                        frame.setVisible(false);
                    }
                    case "Exit" -> System.exit(0);
                }
            });
        }
        frame.add(panel);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation
        frame.setSize(350, 500); // Set the size of the frame
    }
    public static void main (String[]args) {
        Menu menu = new Menu();
        menu.menus();
    }
}

