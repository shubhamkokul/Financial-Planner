package planner.db.modal;

import java.util.Date;

public class ActualExpenseModal {
    private int id;
    private int accountId;
    private String accountName;
    private int planId;
    private String planName;
    private int categoryId;
    private String categoryName;
    private Date date;
    private double amount;
    private boolean planned;

    public ActualExpenseModal(int id, int accountId, String accountName, int planId, String planName, int categoryId, String categoryName, Date date, double amount, boolean planned) {
        this.id = id;
        this.accountId = accountId;
        this.accountName = accountName;
        this.planId = planId;
        this.planName = planName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.date = date;
        this.amount = amount;
        this.planned = planned;
    }

    public int getId() {
        return id;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}
