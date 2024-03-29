package planner.db.businesspopulate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import planner.db.FinancialDatabaseOperation;
import planner.db.modal.ActualExpenseModal;
import planner.db.modal.ActualIncomeModal;
import planner.db.modal.WalletPlannerModal;

public class AmountCalculation {

    public static long expenseCalculation(Context context, ActualExpenseModal actualExpenseModal, WalletPlannerModal walletPlannerModal) {
        double currentExpenseAmount = actualExpenseModal.getAmount();
        double expense = walletPlannerModal.getExpenseBalance();
        double income = walletPlannerModal.getIncomeBalance();
        double newExpense = currentExpenseAmount + expense;
        double balance = income - newExpense;
        SQLiteDatabase dbWrite =  new FinancialDatabaseOperation(context, 1).getDatabaseWriter();
        walletPlannerModal.setExpenseBalance(newExpense);
        walletPlannerModal.setBalance(balance);
        return WalletPlannerModal.updateWallet(dbWrite, walletPlannerModal);
    }

    public static long incomeCalculation(Context context, ActualIncomeModal actualIncomeModal, WalletPlannerModal walletPlannerModal) {
        double currentIncomeAmount = actualIncomeModal.getAmount();
        double expense = walletPlannerModal.getExpenseBalance();
        double income = walletPlannerModal.getIncomeBalance();
        double newIncome = currentIncomeAmount + income;
        double balance = newIncome - expense;
        SQLiteDatabase dbWrite =  new FinancialDatabaseOperation(context, 1).getDatabaseWriter();
        walletPlannerModal.setIncomeBalance(newIncome);
        walletPlannerModal.setBalance(balance);
        return WalletPlannerModal.updateWallet(dbWrite, walletPlannerModal);
    }

    public static long expenseCalculationDelete(Context context, ActualExpenseModal actualExpenseModal) {
        SQLiteDatabase dbReader =  new FinancialDatabaseOperation(context, 1).getDatabaseReader();
        WalletPlannerModal walletPlannerModal = WalletPlannerModal.returnWallet(dbReader, actualExpenseModal.getAccountId());
        double currentExpenseAmount = actualExpenseModal.getAmount();
        double expense = walletPlannerModal.getExpenseBalance();
        double income = walletPlannerModal.getIncomeBalance();
        double newExpense = expense - currentExpenseAmount;
        double balance = income + newExpense;
        SQLiteDatabase dbWrite =  new FinancialDatabaseOperation(context, 1).getDatabaseWriter();
        walletPlannerModal.setExpenseBalance(newExpense);
        walletPlannerModal.setBalance(balance);
        return WalletPlannerModal.updateWallet(dbWrite, walletPlannerModal);
    }

    public static long incomeCalculationDelete(Context context, ActualIncomeModal actualIncomeModal) {
        SQLiteDatabase dbReader =  new FinancialDatabaseOperation(context, 1).getDatabaseReader();
        WalletPlannerModal walletPlannerModal = WalletPlannerModal.returnWallet(dbReader, actualIncomeModal.getAccountId());
        double currentIncomeAmount = actualIncomeModal.getAmount();
        double expense = walletPlannerModal.getExpenseBalance();
        double income = walletPlannerModal.getIncomeBalance();
        double newIncome = income - currentIncomeAmount;
        double balance = expense + newIncome;
        SQLiteDatabase dbWrite =  new FinancialDatabaseOperation(context, 1).getDatabaseWriter();
        walletPlannerModal.setIncomeBalance(newIncome);
        walletPlannerModal.setBalance(balance);
        return WalletPlannerModal.updateWallet(dbWrite, walletPlannerModal);
    }
}
