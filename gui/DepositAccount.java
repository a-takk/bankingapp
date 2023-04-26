package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositAccount {
    public static void main (String[]args) throws Exception {
        DepositAccount acc = new DepositAccount();
        acc.depacc();
    }
    public void depacc() {
        String[] button = {"Add Account", "Remove Account", "Display Account Details",
                "Withdraw From Account", "Deposit Into Account", "Exit"};

        JLabel labelid;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 0));
        JFrame frame = new JFrame("Banking System - Deposit Account");

        labelid = new JLabel("Please enter the ID to deposit money");
        labelid.setHorizontalAlignment(JLabel.CENTER);
        panel.add(labelid);

        for (int i = 0; i < button.length; i++) {
            JButton button1 = new JButton((i + 1) + ": " + button[i]);
            button1.setActionCommand(button[i]); //Set the action command to the button text
            panel.add(button1);

            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    switch (command) {
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
                            DepositAccount disacc = new DepositAccount();
                            disacc.depacc();
                            frame.setVisible(false);
                        }
                        case "Withdraw From Account" -> {
                            WithdrawAccount withacc = new WithdrawAccount();
                            withacc.withacc();
                            ;
                            frame.setVisible(false);
                        }
                        case "Deposit Into Account" -> {
                            DepositAccount depacc = new DepositAccount();
                            depacc.depacc();
                        }
                        case "Exit" -> System.exit(0);
                    }
                }
            });
    }
        frame.add(panel);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation
        frame.setSize(350, 500); // Set the size of the frame
    }
}
