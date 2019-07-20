package planner.db.modal;

import java.util.Date;

public class TransactionModal {
    private long id;
    private Date date;
    private int timeStamp;
    private int planId;
    private String planName;
    private int accountId;
    private String accountName;
    private double previousAmount;
    private double currentAmount;
    private int categoryType;
    private String categoryName;
    private int transactionType;
    private String transactionName;
    private boolean planned;

    public TransactionModal(long id, Date date, int timeStamp, int planId, String planName, int accountId, String accountName, double previousAmount, double currentAmount, int categoryType, String categoryName, int transactionType, String transactionName, boolean planned) {
        this.id = id;
        this.date = date;
        this.timeStamp = timeStamp;
        this.planId = planId;
        this.planName = planName;
        this.accountId = accountId;
        this.accountName = accountName;
        this.previousAmount = previousAmount;
        this.currentAmount = currentAmount;
        this.categoryType = categoryType;
        this.categoryName = categoryName;
        this.transactionType = transactionType;
        this.transactionName = transactionName;
        this.planned = planned;
    }

    public long getId() {
        return id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
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

    public boolean isPlanned() {
        return planned;
    }

    public void setPlanned(boolean planned) {
        this.planned = planned;
    }
}
