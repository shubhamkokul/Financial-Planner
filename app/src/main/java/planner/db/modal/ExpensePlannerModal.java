package planner.db.modal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
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

    public static void insertIntoTable(SQLiteDatabase dbWriter, ExpensePlannerModal expensePlannerModal) {
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
        dbWriter.close();
    }

    public static List<ExpensePlannerModal> returnAll(SQLiteDatabase dbReader) {
        List<ExpensePlannerModal> expensePlannerModals = new ArrayList<>();
        Cursor c = dbReader.rawQuery("SELECT * FROM EXPENSEPLANTABLE", null);
        if (c.moveToFirst()) {
            do {
                expensePlannerModals.add(new ExpensePlannerModal(c.getLong(0), c.getLong(1), c.getInt(2), c.getString(3), c.getString(4), c.getLong(5)
                        , c.getInt(6), c.getString(7), c.getLong(8), c.getInt(9)));
            } while (c.moveToNext());
        }
        dbReader.close();
        return expensePlannerModals;
    }

    public static int checkItem(SQLiteDatabase dbReader, long planID, long monthID, long yearID) {
        Cursor c = dbReader.rawQuery("SELECT * FROM EXPENSEPLANTABLE WHERE PLANID ='" + planID + "' AND MONTHID ='" + monthID + "' AND YEARID = '" + yearID + "'", null);
        int count = c.getCount();
        dbReader.close();
        return count;
    }

}
