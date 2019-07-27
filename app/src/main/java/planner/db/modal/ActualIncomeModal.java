package planner.db.modal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;


public class ActualIncomeModal {
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

    public ActualIncomeModal(long id, long transactionID, long accountId, String accountName, long planId, String planName, long categoryID, String categoryName, String date, double amount, boolean planned) {
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

    public static long insertIntoTable(SQLiteDatabase dbWriter, ActualIncomeModal actualIncomeModal) {
        long returnValue = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", actualIncomeModal.getId());
        contentValues.put("DATE", actualIncomeModal.getDate());
        contentValues.put("TRANSACTIONID", actualIncomeModal.getTransactionID());
        contentValues.put("PLANID", actualIncomeModal.getPlanId());
        contentValues.put("PLANNAME", actualIncomeModal.getPlanName());
        contentValues.put("ACCOUNTID", actualIncomeModal.getAccountId());
        contentValues.put("ACCOUNTNAME", actualIncomeModal.getAccountName());
        contentValues.put("AMOUNT", actualIncomeModal.getAmount());
        contentValues.put("CATEGORYID", actualIncomeModal.getCategoryID());
        contentValues.put("CATEGORYNAME", actualIncomeModal.getCategoryName());
        contentValues.put("PLANNED", actualIncomeModal.isPlanned());
        returnValue = dbWriter.insert("ACTUALINCOMETABLE", null, contentValues);
        dbWriter.close();
        return returnValue;
    }

}