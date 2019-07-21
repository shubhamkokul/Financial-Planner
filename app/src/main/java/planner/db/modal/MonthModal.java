package planner.db.modal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MonthModal {
    private long id;
    private int actualPosition;
    private String name;

    public MonthModal(long id, int actualPosition, String name) {
        this.id = id;
        this.actualPosition = actualPosition;
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public int getActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(int actualPosition) {
        this.actualPosition = actualPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void insertIntoTable(SQLiteDatabase db, List<MonthModal> monthModals) {
        for(MonthModal monthModal : monthModals){
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", monthModal.getId());
            contentValues.put("ACTUALPOSITION", monthModal.getActualPosition());
            contentValues.put("NAME", monthModal.getName());
            db.insert("MONTHTABLE", null, contentValues);
        }
        db.close();
    }

    public static List<MonthModal> returnAll(SQLiteDatabase db) {
        List<MonthModal> monthModals = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM MONTHTABLE", null);
        if (c.moveToFirst()){
            do {
                monthModals.add(new MonthModal(c.getLong(0), c.getInt(1), c.getString(2)));
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return monthModals;
    }

}
