package planner.db.modal;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PlannedIncomeModal {
    private long id;
    private long transactionID;
    private long accountId;
    private String accountName;
    private long planId;
    private String planName;
    private long categoryID;
    private String categoryName;
    private String date;
    private double amount;
    private boolean planned;
    private int categoryColor;
    private String day;
    private String month;

    public PlannedIncomeModal(long id, long transactionID, long accountId, String accountName, long planId, String planName, long categoryID, String categoryName, String date, double amount, boolean planned, int categoryColor, String day, String month) {
        this.id = id;
        this.transactionID = transactionID;
        this.accountId = accountId;
        this.accountName = accountName;
        this.planId = planId;
        this.planName = planName;
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.date = date;
        this.amount = amount;
        this.planned = planned;
        this.categoryColor = categoryColor;
        this.day = day;
        this.month = month;
    }

    public long getId() {
        return id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public long getCategoryId() {
        return categoryID;
    }

    public void setCategoryId(long categoryId) {
        this.categoryID = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPlanned() {
        return planned;
    }

    public void setPlanned(boolean planned) {
        this.planned = planned;
    }

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }


    public int getCategoryColor() {
        return categoryColor;
    }

    public void setCategoryColor(int categoryColor) {
        this.categoryColor = categoryColor;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public static long insertIntoTable(SQLiteDatabase dbWriter, PlannedIncomeModal plannedIncomeModal) {
        long returnValue = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", plannedIncomeModal.getId());
        contentValues.put("DATE", plannedIncomeModal.getDate());
        contentValues.put("TRANSACTIONID", plannedIncomeModal.getTransactionID());
        contentValues.put("PLANID", plannedIncomeModal.getPlanId());
        contentValues.put("PLANNAME", plannedIncomeModal.getPlanName());
        contentValues.put("ACCOUNTID", plannedIncomeModal.getAccountId());
        contentValues.put("ACCOUNTNAME", plannedIncomeModal.getAccountName());
        contentValues.put("AMOUNT", plannedIncomeModal.getAmount());
        contentValues.put("CATEGORYID", plannedIncomeModal.getCategoryID());
        contentValues.put("CATEGORYNAME", plannedIncomeModal.getCategoryName());
        contentValues.put("PLANNED", plannedIncomeModal.isPlanned());
        contentValues.put("CATEGORYCOLOR", plannedIncomeModal.getCategoryColor());
        contentValues.put("DAY", plannedIncomeModal.getDay());
        contentValues.put("MONTH", plannedIncomeModal.getMonth());
        returnValue = dbWriter.insert("PLANNEDINCOMETABLE", null, contentValues);
        dbWriter.close();
        return returnValue;
    }

    public static List<PlannedIncomeModal> returnMonthTransaction(SQLiteDatabase dbReader, long planID) {
        List<PlannedIncomeModal> plannedIncomeModals = new ArrayList<>();
        Cursor c = dbReader.rawQuery("SELECT * FROM PLANNEDINCOMETABLE WHERE PLANID = '" + planID + "'", null);
        if (c.moveToFirst()) {
            do {
                plannedIncomeModals.add(new PlannedIncomeModal(c.getLong(0), c.getLong(1), c.getLong(2), c.getString(3), c.getLong(4),
                        c.getString(5), c.getLong(6), c.getString(7), c.getString(8), c.getFloat(9),
                        Boolean.parseBoolean(c.getString(10)), c.getInt(11), c.getString(12), c.getString(13)));
            } while (c.moveToNext());
        }
        dbReader.close();
        return plannedIncomeModals;
    }

}