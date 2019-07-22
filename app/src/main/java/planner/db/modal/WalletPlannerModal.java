package planner.db.modal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class WalletPlannerModal {
    private long id;
    private String name;
    private long iconID;
    private int icon;
    private String iconName;
    private double incomeBalance;
    private double expenseBalance;
    private double balance;


    public WalletPlannerModal(long id, String name, long iconID, int icon, String iconName, double incomeBalance, double expenseBalance, double balance) {
        this.id = id;
        this.name = name;
        this.iconID = iconID;
        this.icon = icon;
        this.iconName = iconName;
        this.incomeBalance = incomeBalance;
        this.expenseBalance = expenseBalance;
        this.balance = balance;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getIconID() {
        return iconID;
    }

    public void setIconID(long iconID) {
        this.iconID = iconID;
    }

    public double getIncomeBalance() {
        return incomeBalance;
    }

    public void setIncomeBalance(double incomeBalance) {
        this.incomeBalance = incomeBalance;
    }

    public double getExpenseBalance() {
        return expenseBalance;
    }

    public void setExpenseBalance(double expenseBalance) {
        this.expenseBalance = expenseBalance;
    }

    public static void insertIntoTableInitial(SQLiteDatabase db, List<WalletPlannerModal> walletPlannerModals) {
        for (WalletPlannerModal walletPlannerModal : walletPlannerModals) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", walletPlannerModal.getId());
            contentValues.put("NAME", walletPlannerModal.getName().toUpperCase());
            contentValues.put("ICONID", walletPlannerModal.getIconID());
            contentValues.put("ICON", walletPlannerModal.getIcon());
            contentValues.put("ICONNAME", walletPlannerModal.getIconName());
            contentValues.put("INCOMEBALANCE", walletPlannerModal.getBalance());
            contentValues.put("EXPENSEBALANCE", walletPlannerModal.getBalance());
            contentValues.put("BALANCE", walletPlannerModal.getBalance());
            db.insert("WALLETPLANTABLE", null, contentValues);
        }
        db.close();
    }

    public static List<WalletPlannerModal> returnAll(SQLiteDatabase dbReader) {
        List<WalletPlannerModal> walletPlannerModals = new ArrayList<>();
        Cursor c = dbReader.rawQuery("SELECT * FROM WALLETPLANTABLE", null);
        if(c.moveToFirst()){
            do{
                walletPlannerModals.add(new WalletPlannerModal(c.getLong(0), c.getString(1), c.getLong(2), c.getInt(3), c.getString(4), c.getFloat(5)
                ,c.getFloat(6),c.getFloat(7)));
            } while(c.moveToNext());
        }
        return walletPlannerModals;
    }

    public static int checkItem(SQLiteDatabase dbReader, String name, int icon) {
        Cursor c = dbReader.rawQuery("SELECT * FROM WALLETPLANTABLE WHERE NAME ='" + name + "' AND ICON = '" + icon + "'", null);
        int count = c.getCount();
        dbReader.close();
        return count;
    }

    public static void insertIntoTable(SQLiteDatabase dbWriter, WalletPlannerModal walletPlannerModal) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", walletPlannerModal.getId());
        contentValues.put("NAME", walletPlannerModal.getName().toUpperCase());
        contentValues.put("ICONID", walletPlannerModal.getIconID());
        contentValues.put("ICON", walletPlannerModal.getIcon());
        contentValues.put("ICONNAME", walletPlannerModal.getIconName());
        contentValues.put("INCOMEBALANCE", walletPlannerModal.getBalance());
        contentValues.put("EXPENSEBALANCE", walletPlannerModal.getBalance());
        contentValues.put("BALANCE", walletPlannerModal.getBalance());
        dbWriter.insert("WALLETPLANTABLE", null, contentValues);
        dbWriter.close();
    }

}
