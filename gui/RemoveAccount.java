package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RemoveAccount extends Database {
    public void remacc() {
        String[] button = {"Menu", "Add Account", "Remove Account", "Display Account Details",
                "Withdraw From Account", "Deposit Into Account", "Exit"};

        final int[] id = new int[1];
        JLabel LabelID;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 0));
        panel.setBorder(new EmptyBorder(10,10,10,10));
        JFrame frame = new JFrame("Banking System - Remove Account");

        LabelID = new JLabel("Please enter the ID to remove account");
        JTextField ID = new JTextField();
        LabelID.setHorizontalAlignment(JLabel.CENTER);
        panel.add(LabelID);
        panel.add(ID);

        JButton confirm = new JButton("Confirm");
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel.add(confirm);
        panel.add(panel1);
        panel.add(panel2);

        confirm.addActionListener(e -> {
            id[0] = Integer.parseInt(ID.getText());
            try {
                Connection conn = getConnection();
                assert conn != null;
                PreparedStatement insert = conn.prepareStatement("DELETE FROM tablename WHERE id = ?");
                insert.setInt(1, id[0]);
                insert.executeUpdate();
            } catch (Exception i) {
                panel.remove(LabelID);
                panel.revalidate();
                panel.repaint();
                JLabel exception = new JLabel((Icon) i);
                panel.add(exception,1,0);
            } finally {
                JLabel confirmation = new JLabel("You have removed your account");
                panel.remove(LabelID);
                panel.add(confirmation, 1,0);
                panel.revalidate();
                panel.repaint();
                confirmation.setHorizontalAlignment(JLabel.CENTER);
            }
        });

        for (int i = 0; i < button.length; i++) {
            JButton button1 = new JButton((i + 1) + ": " + button[i]);
            button1.setActionCommand(button[i]); //Set the action command to the button text
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
                        AddAccount addacc = new AddAccount();
                        addacc.addacc();
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
}
