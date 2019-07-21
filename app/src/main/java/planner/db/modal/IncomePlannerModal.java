package planner.db.modal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class IncomePlannerModal {
    private String description;
    private long monthID;
    private int month;
    private String monthName;
    private long yearID;
    private int yearName;
    private long id; //Timestamp

    public IncomePlannerModal(long id, String description, long monthID, int month, String monthName, long yearID, int yearName) {
        this.id = id;
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

    public static void insertIntoTableInitial(SQLiteDatabase dbWriter, List<IncomePlannerModal> incomePlannerModals) {
        for (IncomePlannerModal incomePlannerModal : incomePlannerModals) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", incomePlannerModal.getId());
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
    public static List<IncomePlannerModal> returnAll(SQLiteDatabase dbReader){
        List<IncomePlannerModal> incomePlannerModals = new ArrayList<>();
        Cursor c = dbReader.rawQuery("SELECT * FROM INCOMEPLANTABLE", null);
        if(c.moveToFirst()){
            do{
                incomePlannerModals.add(new IncomePlannerModal(c.getLong(0), c.getString(1),
                        c.getLong(2), c.getInt(3), c.getString(4)
                ,c.getLong(5), c.getInt(6)));
            } while(c.moveToNext());
        }
        return incomePlannerModals;
    }


}
