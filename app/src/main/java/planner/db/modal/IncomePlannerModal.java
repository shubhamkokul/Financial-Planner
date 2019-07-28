package planner.db.modal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IncomePlannerModal implements Serializable {
    private String description;
    private long monthID;
    private int month;
    private String monthName;
    private long yearID;
    private int yearName;
    private long id; //Timestamp
    private long planID;

    public IncomePlannerModal(long id, long planID, String description, long monthID, int month, String monthName, long yearID, int yearName) {
        this.id = id;
        this.planID = planID;
        this.description = description;
        this.monthID = monthID;
        this.month = month;
        this.monthName = monthName;
        this.yearID = yearID;
        this.yearName = yearName;
    }




    public long getMonthID() {
        return monthID;
    }

    public void setMonthID(long monthID) {
        this.monthID = monthID;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public long getPlanID() {
        return planID;
    }

    public void setPlanID(long planID) {
        this.planID = planID;
    }

    public static void insertIntoTableInitial(SQLiteDatabase dbWriter, List<IncomePlannerModal> incomePlannerModals) {
        for (IncomePlannerModal incomePlannerModal : incomePlannerModals) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", incomePlannerModal.getId());
            contentValues.put("PLANID", incomePlannerModal.getPlanID());
            contentValues.put("DESCRIPTION", incomePlannerModal.getDescription());
            contentValues.put("MONTHID", incomePlannerModal.getMonthID());
            contentValues.put("MONTH", incomePlannerModal.getMonth());
            contentValues.put("MONTHNAME", incomePlannerModal.getMonthName());
            contentValues.put("YEARID", incomePlannerModal.getYearID());
            contentValues.put("YEARNAME", incomePlannerModal.getYearName());
            dbWriter.insert("INCOMEPLANTABLE", null, contentValues);
        }
        dbWriter.close();
    }

    public static List<IncomePlannerModal> returnAll(SQLiteDatabase dbReader) {
        List<IncomePlannerModal> incomePlannerModals = new ArrayList<>();
        Cursor c = dbReader.rawQuery("SELECT * FROM INCOMEPLANTABLE", null);
        if (c.moveToFirst()) {
            do {
                incomePlannerModals.add(new IncomePlannerModal(c.getLong(0), c.getLong(1), c.getString(2),
                        c.getLong(3), c.getInt(4), c.getString(5)
                        , c.getLong(6), c.getInt(7)));
            } while (c.moveToNext());
        }
        c.close();
        dbReader.close();
        return incomePlannerModals;
    }

    public static int checkItem(SQLiteDatabase dbReader, long monthID, long yearID) {
        Cursor c = dbReader.rawQuery("SELECT * FROM INCOMEPLANTABLE WHERE MONTHID ='" + monthID + "' AND YEARID = '" + yearID + "'", null);
        int count = c.getCount();
        dbReader.close();
        return count;
    }

    public static void insertIntoTable(SQLiteDatabase dbWriter, IncomePlannerModal incomePlannerModal) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", incomePlannerModal.getId());
        contentValues.put("DESCRIPTION", incomePlannerModal.getDescription());
        contentValues.put("MONTHID", incomePlannerModal.getMonthID());
        contentValues.put("MONTH", incomePlannerModal.getMonth());
        contentValues.put("MONTHNAME", incomePlannerModal.getMonthName());
        contentValues.put("YEARID", incomePlannerModal.getYearID());
        contentValues.put("YEARNAME", incomePlannerModal.getYearName());
        dbWriter.insert("INCOMEPLANTABLE", null, contentValues);
        dbWriter.close();
    }

    public static IncomePlannerModal returnMonthTransaction(SQLiteDatabase dbReader, int month, int year) {
        Cursor c = dbReader.rawQuery("SELECT * FROM INCOMEPLANTABLE WHERE MONTH ='" + month + "' AND YEARNAME = '" + year + "'", null);
        c.moveToFirst();
        IncomePlannerModal incomePlannerModal = new IncomePlannerModal(c.getLong(0), c.getLong(1), c.getString(2),
                c.getLong(3), c.getInt(4), c.getString(5)
                , c.getLong(6), c.getInt(7));
        return incomePlannerModal;
    }
}
