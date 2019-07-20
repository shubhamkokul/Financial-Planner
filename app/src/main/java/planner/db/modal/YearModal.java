package planner.db.modal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class YearModal {
    private long id;
    private int year;

    public YearModal(long id, int year) {
        this.id = id;
        this.year = year;
    }

    public static void insertIntoTable(SQLiteDatabase db, List<YearModal> yearModals) {
        for(YearModal yearModal : yearModals){
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", yearModal.getId());
            contentValues.put("YEAR", yearModal.getYear());
            db.insert("YEARTABLE", null, contentValues);
        }
        db.close();
    }

    public long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


}
