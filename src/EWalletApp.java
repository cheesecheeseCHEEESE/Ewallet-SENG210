import java.util.ArrayList;

import javax.swing.JFrame;
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
	
	
	public static void main(String[] args)
	{
		InitalizeJFrame();
	}

}
