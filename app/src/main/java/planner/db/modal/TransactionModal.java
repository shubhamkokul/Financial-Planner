package planner.db.modal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class TransactionModal {
    private long id;
    private String date;
    private long timeStamp;
    private long planId;
    private String planName;
    private long accountId;
    private String accountName;
    private double previousAmount;
    private double currentAmount;
    private long categoryID;
    private int categoryType;
    private String categoryName;
    private long transactionID;
    private int transactionType;
    private String transactionName;
    private boolean planned;

    public TransactionModal(long id, String date, long timeStamp, long planId, String planName, long accountId, String accountName, double previousAmount, double currentAmount, long categoryID, int categoryType, String categoryName, long transactionID, int transactionType, String transactionName, boolean planned) {
        this.id = id;
        this.date = date;
        this.timeStamp = timeStamp;
        this.planId = planId;
        this.planName = planName;
        this.accountId = accountId;
        this.accountName = accountName;
        this.previousAmount = previousAmount;
        this.currentAmount = currentAmount;
        this.categoryID = categoryID;
        this.categoryType = categoryType;
        this.categoryName = categoryName;
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.transactionName = transactionName;
        this.planned = planned;
    }



    public long getId() {
        return id;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
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

    public double getPreviousAmount() {
        return previousAmount;
    }

    public void setPreviousAmount(double previousAmount) {
        this.previousAmount = previousAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public int getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(int categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
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

    public static TransactionModal insertIntoTable(SQLiteDatabase dbWriter, TransactionModal transactionModal) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", transactionModal.getId());
        contentValues.put("DATE", transactionModal.getDate());
        contentValues.put("TIMESTAMP", transactionModal.getTimeStamp());
        contentValues.put("PLANID", transactionModal.getPlanId());
        contentValues.put("PLANNAME", transactionModal.getPlanName());
        contentValues.put("ACCOUNTID", transactionModal.getAccountId());
        contentValues.put("ACCOUNTNAME", transactionModal.getAccountName());
        contentValues.put("PREVIOUSAMOUNT", transactionModal.getPreviousAmount());
        contentValues.put("CURRENTAMOUNT", transactionModal.getCurrentAmount());
        contentValues.put("CATEGORYID", transactionModal.getCategoryID());
        contentValues.put("CATEGORYTYPE", transactionModal.getCategoryType());
        contentValues.put("CATEGORYNAME", transactionModal.getCategoryName());
        contentValues.put("TRANSACTIONTYPE", transactionModal.getTransactionType());
        contentValues.put("TRANSACTIONNAME", transactionModal.getTransactionName());
        contentValues.put("PLANNED", transactionModal.isPlanned());
        dbWriter.insert("TRANSACTIONTABLE", null, contentValues);
        dbWriter.close();
        return transactionModal;
    }
}
