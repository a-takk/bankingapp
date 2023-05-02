package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WithdrawAccount extends Database {
    public void withacc() {
        String[] button = {"Menu", "Add Account", "Remove Account", "Display Account Details",
                "Withdraw From Account", "Deposit Into Account", "Exit"};

        int accnum[] = new int[1];
        int balnum[] = new int[1];

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        panel.setLayout(new GridLayout(12, 0));
        panel.setBorder(new EmptyBorder(10,10,10,10));
        JFrame frame = new JFrame("Banking System - Withdraw Account");

        JLabel LabelID = new JLabel("Please enter your ID and amount to withdraw");
        JTextField Accnum = new JTextField();
        JTextField Balnum = new JTextField();
        LabelID.setHorizontalAlignment(JLabel.CENTER);
        panel.add(LabelID);
        panel.add(Accnum);
        panel.add(Balnum);

        JButton confirm = new JButton("Confirm");
        panel.add(confirm);
        panel.add(panel1);

        confirm.addActionListener(e -> {
            accnum[0] = Integer.parseInt(Accnum.getText());
            balnum[0] = Integer.parseInt(Balnum.getText());
            try {
                Connection conn = getConnection();
                String query = "UPDATE tablename SET balance = balance - ? WHERE id = ?";
                assert conn != null;
                PreparedStatement stmt = conn.prepareStatement(query);

                stmt.setInt(1, balnum[0]);
                stmt.setInt(2, accnum[0]);
                stmt.executeUpdate();

                String getAccountQuery = "SELECT * FROM tablename WHERE id = ?";
                PreparedStatement getAccountQueryStmt = conn.prepareStatement(getAccountQuery);
                getAccountQueryStmt.setInt(1,accnum[0]);
                ResultSet rs = getAccountQueryStmt.executeQuery();

                if(rs.next()) {
                    accnum[0] = rs.getInt("id");
                    balnum[0] = rs.getInt("balance");
                    JLabel bal = new JLabel("Your balance is now: " + "£" + balnum[0]);
                    JLabel acc = new JLabel("Your ID is: " + accnum[0]);
                    panel.remove(Accnum);
                    panel.remove(Balnum);
                    panel.remove(LabelID);
                    panel.remove(confirm);
                    panel.repaint();
                    panel.revalidate();
                    panel.add(bal,2,0);
                    panel.add(acc,0,0);
                    acc.setHorizontalAlignment(JLabel.CENTER);
                    bal.setHorizontalAlignment(JLabel.CENTER);
                } else {
                    panel.remove(LabelID);
                    panel.revalidate();
                    panel.repaint();
                    JLabel unf = new JLabel("User not found, please enter again.");
                    panel.add(unf,1,0);
                    unf.setHorizontalAlignment(JLabel.CENTER);
                }
            } catch (Exception i) {
                JLabel getmessage = new JLabel(i.getMessage());
                JLabel error = new JLabel("You can't withdraw this amount" + getmessage);
                panel.add(error,1,0);
            }
        });

        for (int i = 0; i < button.length; i++) {
            JButton button1 = new JButton((i + 1) + ": " + button[i]);
            button1.setActionCommand(button[i]);
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
