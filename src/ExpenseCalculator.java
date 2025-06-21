
public class ExpenseCalculator implements Expenser {

	@Override
	public void addExpense(Expense Ex) {
		EWalletApp.currentUser.addExpense(Ex);
	}

	@Override
	public void addMonthlyIncome(Wage W) {
		EWalletApp.currentUser.addIncome(W);
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
	}

}
