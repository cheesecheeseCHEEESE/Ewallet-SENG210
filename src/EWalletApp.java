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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


public class EWalletApp {
	
	//this is the app class, has the GUI and create one object of your expense calculator class. The expense calculator class is the implementation of the Expenser interface 
	private ArrayList<User> allData;
	
	private static ExpenseCalculator expenseCalculator = new ExpenseCalculator();
	
	public static void updateUserAtHand(User currentUser) 
	{
		//expenseCalculator.userAtHand = currentUser;
	}
	
	
	private static void InitalizeLoginScreen(ExpenseCalculator expenseCalculator) //called to create the GUI for Login Screen
											//calculator referenced to work around static class
	{
		// Inital JFrame stuff
		JFrame jframe = new JFrame();
		jframe.setTitle("E-Wallet App");
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		jframe.setLayout(new FlowLayout());
		jframe.setSize(400, 300);
		jframe.setLayout(new BorderLayout());
		

		//components for the GUI here
		JTextArea usernameInput = new JTextArea("Username (type here)");
		JTextArea passwordInput = new JTextArea("Password (type here)");
		JButton confirmLoginButton = new JButton("Login");
		
		// Button to open generate report dialog (you can move this wherever)
		JButton generateReportButton = new JButton("Generate Report");
		generateReportButton.addActionListener(event -> selectReport());
		
		JButton importReportButton = new JButton("Import Report");
		importReportButton.addActionListener(event -> importReport());
		
		//adding all the components here

		jframe.add(usernameInput, BorderLayout.NORTH);
		jframe.add(passwordInput, BorderLayout.CENTER);
		jframe.add(confirmLoginButton, BorderLayout.SOUTH);
		
		
		//"wrap up" stuff for the JFrame
		//jframe.pack();
		confirmLoginButton.addActionListener(new ActionListener(){
					
			@Override
			public void actionPerformed(ActionEvent e) {

               if(usernameInput.getText() == null || passwordInput.getText() == null || usernameInput.getText().isEmpty() || passwordInput.getText().isEmpty())
               {
            	   //Potentially display error message. Otherwise, do nothing. No login if no info
               }
               else
               {
            	   User user = new User(usernameInput.getText(), passwordInput.getText());
            	   expenseCalculator.userAtHand = user;
            	   InitalizeReportScreen();
            	   jframe.dispose(); //destroy self now that new JFrame is here
               }
            }
				});
		jframe.setVisible(true); //may move to Main for something if it becomes a problem
	}
	
	private static void InitalizeReportScreen()
	{
		//inital Jframe stuff
		JFrame jframe = new JFrame();
		jframe.setTitle("E-Wallet App");
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		jframe.setSize(400, 300);
		
		//Creating GUI stuff
		JLabel incomeLabel = new JLabel("Add Income (per month)"); 
		JLabel expenseLabel = new JLabel("Add Expense"); 
		
		JTextField incomeInput = new JTextField();
		JTextField expenseInput = new JTextField();
		
		//Admittedly code taken from chatGPT, to prevent none numbers from being inputed
		DocumentFilter numberFilter = new NumericFilter();
		((AbstractDocument) incomeInput.getDocument()).setDocumentFilter(numberFilter);
		((AbstractDocument) expenseInput.getDocument()).setDocumentFilter(numberFilter);
		
		
		//BUTTONS DO NOT HAVE FUNCTIONALITY!!!! ADD ASAP!!!!!!
		JButton confirmIncomeButton = new JButton("Add");
		JButton confirmExpenseButton = new JButton("Add");
		JButton reportButton = new JButton("Print an Expense Report");
		
		
		//Panels, to organize page
		JPanel incomePanel = new JPanel();
		incomePanel.setLayout(new BoxLayout(incomePanel, BoxLayout.Y_AXIS));
		JPanel expensePanel = new JPanel();
		expensePanel.setLayout(new BoxLayout(expensePanel, BoxLayout.Y_AXIS));
		
		//Add features to GUI
		incomePanel.add(incomeLabel);
		incomePanel.add(incomeInput);
		incomePanel.add(confirmIncomeButton);
		
		expensePanel.add(expenseLabel);
		expensePanel.add(expenseInput);
		expensePanel.add(confirmExpenseButton);
		
		jframe.add(incomePanel, BorderLayout.NORTH);
		jframe.add(expensePanel, BorderLayout.CENTER);
		jframe.add(reportButton, BorderLayout.SOUTH);
		
		//BUTTONS DO NOT HAVE FUNCTIONALITY!!!! ADD ASAP!!!!!!
		
		//Wrap up stuff
		jframe.setVisible(true);
	}
	
	private static void importReport() {
		
		// File chooser to choose what file to import
		JFileChooser fileChooser = new JFileChooser();
		String filePath = "";
		String reportType = "";
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Comma-separated value files (.csv)", "csv");
		fileChooser.setFileFilter(filter);
		int returnVal = fileChooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			filePath = fileChooser.getSelectedFile().toString();
		}
		
		// Gets path and checks report type
		filePath = fileChooser.getSelectedFile().getPath();
		
		if (fileChooser.getSelectedFile().getName().toLowerCase().contains("expense")) {
			reportType = "Expense";
		}
		else if (fileChooser.getSelectedFile().getName().toLowerCase().contains("income")) {
			reportType = "Income";
		}
		
		// Loads file
		if (reportType == "Expense") {
			expenseCalculator.loadExpenseFile(filePath);
		}
		else if (reportType == "Income") {
			expenseCalculator.loadIncomeFile(filePath);
		}
	}

	// Used to select what kind of report to display 
	private static void selectReport() {
		
		// Initial frame settings
		JFrame frame = new JFrame("Select Report Type:");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(400, 175));
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		// Invisible panel meant to keep the rest of the components centered
		JPanel center = new JPanel();
		center.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Drop down box and array of options
		String[] reportTypes = {
				"Full Report", "Income Report", "Expense Report"
		};
		JComboBox<String> dropDownBox = new JComboBox<String>(reportTypes);
		dropDownBox.setPreferredSize(new Dimension(250,50));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 0, 10, 0);
		center.add(dropDownBox, gbc);
		
		
		// Select button and action listener
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
		
		// Adds components and sets frame visible
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
	

	public static void main(String[] args)
	{
		ExpenseCalculator expenseCalculator = new ExpenseCalculator(); //had to create here to get around static BS
		InitalizeLoginScreen(expenseCalculator);
    createTestUser();
	}
	
	
	
	//used to prevent User from putting letters into a number field
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
