package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAccount {
    public static void main (String[]args) throws Exception {
        AddAccount acc = new AddAccount();
        acc.addacc();
    }
    public void addacc() {
        String[] button = {"Add Account", "Remove Account", "Display Account Details",
                "Withdraw From Account", "Deposit Into Account", "Exit"};

        JLabel fname, lname;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 0));
        JFrame frame = new JFrame("Banking System - Add Account");

        fname = new JLabel("Please Enter Your First and Last Name");
        JTextField first = new JTextField();
        JTextField last = new JTextField();
        fname.setHorizontalAlignment(JLabel.CENTER);
        panel.add(fname);
        panel.add(first);
        panel.add(last);

        for (int i = 0; i < button.length; i++) {
            JButton button1 = new JButton((i + 1) + ": " + button[i]);
            button1.setActionCommand(button[i]); //Set the action command to the button text
            panel.add(button1);

            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    if (command.equals("Add Account")) {
                        AddAccount acc = new AddAccount();
                        acc.addacc();
                        frame.setVisible(false);
                    } else if (command.equals("Remove Account")) {
                        RemoveAccount remacc = new RemoveAccount();
                        remacc.remacc();
                        frame.setVisible(false);
                    } else if (command.equals("Display Account Details")) {
                        DisplayAccount disacc = new DisplayAccount();
                        disacc.disacc();
                        frame.setVisible(false);
                    } else if (command.equals("Withdraw From Account")) {
                        WithdrawAccount withacc = new WithdrawAccount();
                        withacc.withacc();;
                        frame.setVisible(false);
                    } else if (command.equals("Deposit Into Account")) {
                        DepositAccount depacc = new DepositAccount();
                        depacc.depacc();
                    } else if (command.equals("Exit")) {
                        System.exit(0);
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
