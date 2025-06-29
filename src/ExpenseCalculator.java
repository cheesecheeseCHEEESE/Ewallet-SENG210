import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ExpenseCalculator implements Expenser {
	
	public static User userAtHand = null;

	@Override
	public void addExpense(Expense Ex) {
		userAtHand.addExpense(Ex);
	}

	@Override
	public void addMonthlyIncome(Wage W) {
		userAtHand.addIncome(W);
	}

	@Override
	public void printFullReport() {
		JFrame frame = new JFrame("Full Report");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		
		JLabel reportLabel = new JLabel("Full report for " + userAtHand.username + ":");
		
		frame.add(reportLabel);
		
		JLabel incomeLabel = new JLabel("Income: ");
		frame.add(incomeLabel);
		
		generateIncomeTable(frame);
		
		JLabel expenseLabel = new JLabel("Expenses: ");
		frame.add(expenseLabel);
		
		generateExpenseTable(frame);
		   
		frame.pack();
		
		
	}

	@Override
	public void printExpenseReport() {
		JFrame frame = new JFrame("Expense Report");
		frame.setVisible(true);
	}
	
	public void generateExpenseTable(JFrame frame) {
		String[] expenseTableColumnNames = {
				"Category",
				"Amount",
				"Frequency"
		};
		
		ArrayList<String[]> expenseTableDataArrayList = new ArrayList<String[]>();
		
		// Reads data from the current user's income and adds to array
		for (Expense expense : userAtHand.getSpending()) {
			String[] row = new String[3];
			row[0] = expense.getCategory();
			row[1] = "$" + Double.toString(expense.getAmount());

			switch (expense.getYearlyFrequency()) {
			
			case 1:
				row[2] = "Yearly";
				break;
				
			case 12:
				row[2] = "Monthly";
				break;
				
			case 24:
				row[2] = "Biweekly";
				break;
				
			case 52:
				row[2] = "Weekly";
				break;
				
			default:
				row[2] = Integer.toString(expense.getYearlyFrequency()) + " times / year";
				break;
			}
			expenseTableDataArrayList.add(row);
		}
		
		String[][] expenseTableData = expenseTableDataArrayList.toArray(new String[0][0]);
		
		JTable incomeTable = new JTable(new ReportTableModel(expenseTableData, expenseTableColumnNames));
		
		JScrollPane incomeScrollPane = new JScrollPane(incomeTable);
		frame.add(incomeScrollPane);
	}

	@Override
	public void printIncomeReport() {
		JFrame frame = new JFrame("Income Report");
		frame.setVisible(true);
	}
	
	// This version adds the income report as a component to a JFrame
	public void generateIncomeTable(JFrame frame) {
		
		String[] incomeTableColumnNames = {
				"Source",
				"Amount",
				"Month"
		};
		
		ArrayList<String[]> incomeTableDataArrayList = new ArrayList<String[]>();
		
		// Reads data from the current user's income and adds to array
		for (Wage incomeSource : userAtHand.getIncome()) {
			String[] row = new String[3];
			row[0] = incomeSource.getSource();
			row[1] = "$" + Double.toString(incomeSource.getAmount());
			row[2] = incomeSource.getMonth();
			incomeTableDataArrayList.add(row);
		}
		
		String[][] incomeTableData = incomeTableDataArrayList.toArray(new String[0][0]);
		
		JTable incomeTable = new JTable(new ReportTableModel(incomeTableData, incomeTableColumnNames));
		
		JScrollPane incomeScrollPane = new JScrollPane(incomeTable);
		frame.add(incomeScrollPane);
		
	}

	@Override
	public void printIncomeReportByType() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printExpenseByType() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportReport(String reportTitle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Currency convertForeignCurrency(Currency C, double amount) {
    		double exchangeRate = C.getRate();
    		double convertedAmount = amount * exchangeRate;
    		return new Currency(C.getName(), convertedAmount, exchangeRate);
	}

	@Override
	public boolean loadExpenseFile(String filePath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loadIncomeFile(String filePath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int whenCanIBuy(String itemname, double price) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateMonthlySavings() {
		
		// Initialize calculators
		int totalMonthlyIncome = 0;
		int totalMonthlySpending = 0;

		// Calculate total monthly income (can replace this with user.balance later)
		for (Wage incomeSource : userAtHand.getIncome()) {
			totalMonthlyIncome += incomeSource.getAmount();
		}
		
		// Calculate total monthly spending
		for (Expense expense : userAtHand.getSpending()) {
			totalMonthlySpending += expense.getAmount();
		}
		
		// Calculate total monthly savings
		int totalMonthlySavings = totalMonthlyIncome - totalMonthlySpending;
		
		// Updates user variables
		userAtHand.balance = totalMonthlyIncome;
		userAtHand.monthlySavings = totalMonthlySavings;
		
	}

}
