package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DisplayAccount extends Database {
    public void disacc() {
        variables var = new variables();
        String[] button = {"Menu", "Add Account", "Remove Account", "Display Account Details",
                "Withdraw From Account", "Deposit Into Account", "Exit"};

        int[] id = new int[1];
        JLabel LabelID;
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel.setLayout(new GridLayout(12, 0));
        panel.setBorder(new EmptyBorder(10,10,10,10));
        JFrame frame = new JFrame("Banking System - Display Account");

        LabelID = new JLabel("Please enter the ID to display account details");
        LabelID.setHorizontalAlignment(JLabel.CENTER);
        JTextField ID = new JTextField();
        panel.add(LabelID);
        panel.add(ID);

        JButton confirm = new JButton("Confirm");
        panel.add(confirm);
        panel.add(panel1);
        panel.add(panel2);

        confirm.addActionListener(e -> {
            id[0] = Integer.parseInt(ID.getText());
            try {
                Connection conn = getConnection();
                String query = "SELECT * FROM tablename WHERE id = ?";
                assert conn != null;
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1,id[0]);
                ResultSet rs = stmt.executeQuery();

                if(rs.next()) {
                    var.userid = rs.getInt("id");
                    var.first = rs.getString("first");
                    var.last = rs.getString("last");
                    var.balance = rs.getInt("balance");

                    panel.remove(LabelID);
                    panel.remove(ID);
                    panel.remove(confirm);
                    panel.repaint();
                    panel.revalidate();
                    JLabel firstname = new JLabel("Your name is: " + var.first + " " + var.last);
                    JLabel userid = new JLabel("Your unique ID is: " + var.userid);
                    JLabel balance = new JLabel("Your balance is: " + var.balance);
                    panel.add(balance,0,0);
                    panel.add(userid,1,0);
                    panel.add(firstname,3,0);
                    userid.setHorizontalAlignment(JLabel.CENTER);
                    firstname.setHorizontalAlignment(JLabel.CENTER);
                    balance.setHorizontalAlignment(JLabel.CENTER);
                } else {
                    panel.remove(LabelID);
                    panel.revalidate();
                    panel.repaint();
                    JLabel unf = new JLabel("User not found, please enter again.");
                    panel.add(unf,1,0);
                    unf.setHorizontalAlignment(JLabel.CENTER);
                }
            } catch (Exception i) {
                panel.remove(LabelID);
                panel.revalidate();
                panel.repaint();
                JLabel incid = new JLabel("Incorrect ID, try again");
                panel.add(incid,1,0);
                incid.setHorizontalAlignment(JLabel.CENTER);
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
