import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.text.DocumentFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EWalletApp {

    // this is the app class, has the GUI and create one object of your expense calculator class.
    // The expense calculator class is the implementation of the Expenser interface 
    private ArrayList<User> allData;
    private static ExpenseCalculator expenseCalculator = new ExpenseCalculator();
    private static User currUser = new User();

    public static void updateUserAtHand(User currentUser) {
        // expenseCalculator.userAtHand = currentUser;
    }

    private static void InitalizeLoginScreen(ExpenseCalculator expenseCalculator) {
        // Inital JFrame stuff
        JFrame jframe = new JFrame("E-Wallet App - Login");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(400, 250);
        jframe.setLocationRelativeTo(null); // center screen

        // Components
        JLabel userLabel = new JLabel("Username:");
        JTextField usernameInput = new JTextField();
        usernameInput.setPreferredSize(new Dimension(200, 25));

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passwordInput = new JPasswordField();
        passwordInput.setPreferredSize(new Dimension(200, 25));

        JButton confirmLoginButton = new JButton("Login");
        JLabel feedbackLabel = new JLabel(" ");
        feedbackLabel.setForeground(java.awt.Color.RED);

        // Panel layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 40, 20, 40));

        panel.add(userLabel);
        panel.add(usernameInput);
        panel.add(passLabel);
        panel.add(passwordInput);
        panel.add(javax.swing.Box.createVerticalStrut(10));
        panel.add(confirmLoginButton);
        panel.add(javax.swing.Box.createVerticalStrut(10));
        panel.add(feedbackLabel);

        // Action listener
        confirmLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameInput.getText().trim();
                String password = new String(passwordInput.getPassword()).trim();

                if (username.isEmpty() || password.isEmpty()) {
                    feedbackLabel.setText("Please enter both username and password.");
                } else {
                    User user = new User(username, password);
                    expenseCalculator.userAtHand = user;

                    // TEMP: Bypassing report screen to avoid build errors
                    System.out.println("Login successful! Welcome, " + username);
                    jframe.dispose();
                }
            }
        });

        jframe.add(panel);
        jframe.setVisible(true);
    }

    public static void main(String[] args) {
        ExpenseCalculator expenseCalculator = new ExpenseCalculator(); // had to create here to get around static BS
        InitalizeLoginScreen(expenseCalculator);
    }

    // Temporary stand-ins to allow code to compile for login page testing only
    static class User {
        private String username, password;

        public User() {}

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    static class ExpenseCalculator {
        public static User userAtHand;

        public void loadExpenseFile(String path) {}
        public void loadIncomeFile(String path) {}
        public void printFullReport() {}
        public void printIncomeReport() {}
        public void printExpenseReport() {}
    }

    static class NumericFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("\\d+")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text.matches("\\d+")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}
