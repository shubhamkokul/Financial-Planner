package planner;

public class WalletModal {


    public String getAccountName() {
        return accountName;
    }

    public String getIncome() {
        return income;
    }

    public String getExpense() {
        return expense;
    }

    public String getBalance() {
        return balance;
    }

    public int getImage() {
        return image;
    }

    private String accountName, income, expense, balance;
    private int image;

    public WalletModal(String accountName, String income, String expense, String balance, int image) {
        this.accountName = accountName;
        this.income = income;
        this.expense = expense;
        this.balance = balance;
        this.image = image;
    }
}
