package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddAccount extends Database {
        public static void main(String[] args) throws Exception {
            AddAccount acc = new AddAccount();
            acc.addacc();
        }
        public void addacc() {
            variables var = new variables();
            String[] button = {"Menu", "Add Account", "Remove Account", "Display Account Details",
                    "Withdraw From Account", "Deposit Into Account", "Exit"};

            final String[] fname = new String[1];
            final String[] lname = new String[1];
            JLabel fullname;

            JPanel panel = new JPanel();
            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();

            panel.setLayout(new GridLayout(12, 0));
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            JFrame frame = new JFrame("Banking System - Add Account");

            fullname = new JLabel("Please enter your first and last name");
            JTextField first = new JTextField();
            JTextField last = new JTextField();
            fullname.setHorizontalAlignment(JLabel.CENTER);
            panel.add(fullname);
            panel.add(first);
            panel.add(last);

            JButton confirm = new JButton("Confirm");
            panel.add(confirm);
            panel.add(panel1);

            confirm.addActionListener(e -> {
                fname[0] = first.getText();
                lname[0] = last.getText();
                try {
                    Connection conn = getConnection();
                    assert conn != null;
                    PreparedStatement insert = conn.prepareStatement("INSERT INTO tablename (id, first, last, balance) VALUES ('" + var.id + "','" + fname[0] + "','" + lname[0] + "','" + var.balance + "')");
                    insert.execute();
                } catch (Exception i) {
                    panel.remove(fullname);
                    panel.revalidate();
                    panel.repaint();
                    JLabel exception = new JLabel((Icon) i);
                    panel.add(exception,1,0);
                } finally {
                    JLabel confirmation = new JLabel("You have created your account");
                    JLabel id = new JLabel("Your ID is: " + var.id);
                    JLabel accnum = new JLabel("Your balance is: " + var.balance);
                    panel.remove(fullname);
                    panel.remove(first);
                    panel.remove(last);
                    panel.remove(confirm);
                    panel.revalidate();
                    panel.repaint();
                    panel.add(accnum,2,0);
                    panel.add(id,1,0);
                    panel.add(confirmation,0,0);
                    panel.add(panel2, 5,0);
                    confirmation.setHorizontalAlignment(JLabel.CENTER);
                    id.setHorizontalAlignment(JLabel.CENTER);
                    accnum.setHorizontalAlignment(JLabel.CENTER);
                }
            });

            for (int i = 0; i < button.length; i++) {
                JButton button1 = new JButton((i + 1) + ": " + button[i]); //creates a button with the array and concatenates all items together
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
    }

