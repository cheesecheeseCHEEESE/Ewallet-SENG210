
public class ExpenseCalculator implements Expenser {

	public User userAtHand= null;
	
	@Override
	public static void addExpense(Expense Ex) {
		userAtHand.addExpense(Ex);
	}

	@Override
	public void addMonthlyIncome(Wage W) {
		userAtHand.addIncome(W);
	}

	@Override
	public void printFullReport() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printExpenseReport() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printIncomeReport() {
		// TODO Auto-generated method stub
		
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
