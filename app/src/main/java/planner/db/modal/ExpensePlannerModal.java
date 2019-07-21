package planner.db.modal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class ExpensePlannerModal {
    private long id;
    private long planID;
    private int planType;
    private String planTypeName;
    private String description;
    private long monthID;
    private int month;
    private String monthName;
    private long yearID;
    private int yearName;


    public ExpensePlannerModal(long id, long planID, int planType, String planTypeName, String description, long monthID, int month, String monthName, long yearID, int yearName) {
        this.id = id;
        this.planID = planID;
        this.planType = planType;
        this.planTypeName = planTypeName;
        this.description = description;
        this.monthID = monthID;
        this.month = month;
        this.monthName = monthName;
        this.yearID = yearID;
        this.yearName = yearName;
    }




    public long getPlanID() {
        return planID;
    }

    public void setPlanID(long planID) {
        this.planID = planID;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getPlanType() {
        return planType;
    }

    public void setPlanType(int planType) {
        this.planType = planType;
    }

    public String getPlanTypeName() {
        return planTypeName;
    }

    public void setPlanTypeName(String planTypeName) {
        this.planTypeName = planTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getMonthID() {
        return monthID;
    }

    public void setMonthID(long monthID) {
        this.monthID = monthID;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public long getYearID() {
        return yearID;
    }

    public void setYearID(long yearID) {
        this.yearID = yearID;
    }

    public int getYearName() {
        return yearName;
    }

    public void setYearName(int yearName) {
        this.yearName = yearName;
    }

    public long getId() {
        return id;
    }

    public static void insertIntoTableInitial(SQLiteDatabase dbWriter, List<ExpensePlannerModal> expensePlannerModals) {
        for (ExpensePlannerModal expensePlannerModal : expensePlannerModals) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", expensePlannerModal.getId());
            contentValues.put("PLANID", expensePlannerModal.getPlanID());
            contentValues.put("PLANTYPE", expensePlannerModal.getPlanType());
            contentValues.put("PLANTYPENAME", expensePlannerModal.getPlanTypeName());
            contentValues.put("DESCRIPTION", expensePlannerModal.getDescription());
            contentValues.put("MONTHID", expensePlannerModal.getMonthID());
            contentValues.put("MONTH", expensePlannerModal.getMonth());
            contentValues.put("MONTHNAME", expensePlannerModal.getMonthName());
            contentValues.put("YEARID", expensePlannerModal.getYearID());
            contentValues.put("YEARNAME", expensePlannerModal.getYearName());
            dbWriter.insert("EXPENSEPLANTABLE", null, contentValues);
        }
        dbWriter.close();
    }

}
