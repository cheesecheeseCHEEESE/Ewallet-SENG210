import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class EWalletApp {
	//this is the app class, has the GUI and create one object of your expense calculator class. The expense calculator class is the implementation of the Expenser interface 
	private ArrayList<User> allData;
	
	private ExpenseCalculator expenseCalculator = new ExpenseCalculator();
	
	public void createUser(String username, String password) {
		
		// When you finish logging in/changing user, set expenseCalculator.userAtHand to the created
		
		// user
		//User newUser = new User(/*name and password*/);
		//can't initalize a new user until User has a Constructor
	}
	
	private static void InitalizeJFrame() //called to create the GUI
	{
		//Inital JFrame stuff
		JFrame jframe = new JFrame();
		jframe.setTitle("E-Wallet App");
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		jframe.setSize(400, 300);
		
		//components for the GUI here
		JTextField reportOutputArea = new JTextField("Lorum Ipsum");
		
		
		//adding all the components here
		jframe.add(reportOutputArea);
		
		
		//"wrap up" stuff for the JFrame
		jframe.pack();
		jframe.setVisible(true); //may move to Main for something if it becomes a problem
	}
	
	
	// Used to select what kind of report to display (call this when the generate report button is 
	// clicked
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
					showFullReport();
					break;

				case "Income Report":
					frame.dispose();
					showIncomeReport();
					break;

				case "Expense Report":
					frame.dispose();
					showExpenseReport();
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
	
	
	protected static void showExpenseReport() {
		// TODO Auto-generated method stub
		
	}

	protected static void showIncomeReport() {
		// TODO Auto-generated method stub
		
	}

	protected static void showFullReport() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args)
	{
		InitalizeJFrame();
	}

}
