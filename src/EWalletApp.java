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

	private ArrayList<User> allData;
	private static ExpenseCalculator expenseCalculator = new ExpenseCalculator();
	private static User currUser = new User();

	public static void updateUserAtHand(User currentUser) {
		// expenseCalculator.userAtHand = currentUser;
	}

	private static void InitalizeLoginScreen(ExpenseCalculator expenseCalculator) {
		// Create the main frame
		JFrame jframe = new JFrame("E-Wallet App - Login");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(400, 250);
		jframe.setLocationRelativeTo(null); // Center the window

		// Create input components
		JLabel userLabel = new JLabel("Username:");
		JTextField usernameInput = new JTextField();
		usernameInput.setPreferredSize(new Dimension(200, 25));

		JLabel passLabel = new JLabel("Password:");
		JPasswordField passwordInput = new JPasswordField();
		passwordInput.setPreferredSize(new Dimension(200, 25));

		JButton confirmLoginButton = new JButton("Login");
		JLabel feedbackLabel = new JLabel(" ");
		feedbackLabel.setForeground(java.awt.Color.RED);

		// Set up panel with BoxLayout for vertical stacking
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 40, 20, 40)); // padding

		panel.add(userLabel);
		panel.add(usernameInput);
		panel.add(passLabel);
		panel.add(passwordInput);
		panel.add(javax.swing.Box.createVerticalStrut(10)); // spacing
		panel.add(confirmLoginButton);
		panel.add(javax.swing.Box.createVerticalStrut(10));
		panel.add(feedbackLabel);

		// Add action listener to login button
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
					InitalizeReportScreen();
					jframe.dispose(); // Close the login window
				}
			}
		});

		jframe.add(panel);
		jframe.setVisible(true);
	}

	private static void InitalizeReportScreen() {
		JFrame jframe = new JFrame();
		jframe.setTitle("E-Wallet App");
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		jframe.setSize(400, 300);

		JLabel incomeLabel = new JLabel("Add Income (per month)");
		JLabel expenseLabel = new JLabel("Add Expense");

		JTextField incomeInput = new JTextField();
		JTextField expenseInput = new JTextField();

		DocumentFilter numberFilter = new NumericFilter();
		((AbstractDocument) incomeInput.getDocument()).setDocumentFilter(numberFilter);
		((AbstractDocument) expenseInput.getDocument()).setDocumentFilter(numberFilter);

		JButton confirmIncomeButton = new JButton("Add");
		JButton confirmExpenseButton = new JButton("Add");

		JButton generateReportButton = new JButton("Generate Report");
		generateReportButton.addActionListener(event -> selectReport());

		JButton importReportButton = new JButton("Import Report");
		importReportButton.addActionListener(event -> importReport());

		JPanel incomePanel = new JPanel();
		incomePanel.setLayout(new BoxLayout(incomePanel, BoxLayout.Y_AXIS));
		JPanel expensePanel = new JPanel();
		expensePanel.setLayout(new BoxLayout(expensePanel, BoxLayout.Y_AXIS));
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());

		incomePanel.add(incomeLabel);
		incomePanel.add(incomeInput);
		incomePanel.add(confirmIncomeButton);

		expensePanel.add(expenseLabel);
		expensePanel.add(expenseInput);
		expensePanel.add(confirmExpenseButton);

		buttonsPanel.add(generateReportButton, BorderLayout.NORTH);
		buttonsPanel.add(importReportButton, BorderLayout.SOUTH);

		jframe.add(incomePanel, BorderLayout.NORTH);
		jframe.add(expensePanel, BorderLayout.CENTER);
		jframe.add(buttonsPanel, BorderLayout.SOUTH);

		jframe.setVisible(true);
	}

	private static void importReport() {
		JFileChooser fileChooser = new JFileChooser();
		String filePath = "";
		String reportType = "";

		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files (.csv)", "csv");
		fileChooser.setFileFilter(filter);

		int returnVal = fileChooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			filePath = fileChooser.getSelectedFile().getPath();
		}

		if (fileChooser.getSelectedFile().getName().toLowerCase().contains("expense")) {
			reportType = "Expense";
		} else if (fileChooser.getSelectedFile().getName().toLowerCase().contains("income")) {
			reportType = "Income";
		}

		if (reportType.equals("Expense")) {
			expenseCalculator.loadExpenseFile(filePath);
		} else if (reportType.equals("Income")) {
			expenseCalculator.loadIncomeFile(filePath);
		}
	}

	private static void selectReport() {
		ExpenseCalculator.userAtHand = currUser;

		if (ExpenseCalculator.userAtHand.getIncome() == null && ExpenseCalculator.userAtHand.getSpending() == null) {
			createTestUser();
		}

		JFrame frame = new JFrame("Select Report Type:");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(400, 175));
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));

		JPanel center = new JPanel();
		center.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		String[] reportTypes = { "Full Report", "Income Report", "Expense Report" };
		JComboBox<String> dropDownBox = new JComboBox<>(reportTypes);
		dropDownBox.setPreferredSize(new Dimension(250, 50));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 0, 10, 0);
		center.add(dropDownBox, gbc);

		JButton selectButton = new JButton("Generate Report");
		selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch ((String) dropDownBox.getSelectedItem()) {
				case "Full Report":
					frame.dispose();
					expenseCalculator.printFullReport();
					break;
				case "Income Report":
					frame.dispose();
					expenseCalculator.printIncomeReport();
					break;
				case "Expense Report":
					frame.dispose();
					expenseCalculator.printExpenseReport();
					break;
				}
			}
		});
		gbc.gridy = 1;
		center.add(selectButton, gbc);

		frame.add(center);
		frame.setVisible(true);
	}

	private static void createTestUser() {
		ExpenseCalculator.userAtHand = new User("Test User", "Password1");
		ExpenseCalculator.userAtHand.addIncome(new Wage("Walmart", 400.00, "May"));
		ExpenseCalculator.userAtHand.addIncome(new Wage("Walmart", 700.00, "June"));
		ExpenseCalculator.userAtHand.addIncome(new Wage("Erbert and Gerbert's", 500.00, "May"));
		ExpenseCalculator.userAtHand.addIncome(new Wage("Side hustle", 10.00, "May"));
		ExpenseCalculator.userAtHand.addIncome(new Wage("Side hustle", 40.00, "June"));
		ExpenseCalculator.userAtHand.addExpense(new Expense("Shopping", 40.00, 1));
		ExpenseCalculator.userAtHand.addExpense(new Expense("Subscription", 12.00, 12));
		ExpenseCalculator.userAtHand.addExpense(new Expense("Groceries", 100.00, 24));
	}

	public static void main(String[] args) {
		ExpenseCalculator expenseCalculator = new ExpenseCalculator();
		InitalizeLoginScreen(expenseCalculator);
	}

	static class NumericFilter extends DocumentFilter {
		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException {
			if (string.matches("\\d+")) {
				super.insertString(fb, offset, string, attr);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			if (text.matches("\\d+")) {
				super.replace(fb, offset, length, text, attrs);
			}
		}
	}
}
