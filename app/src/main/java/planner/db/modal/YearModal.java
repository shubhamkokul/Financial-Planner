package planner.db.modal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class YearModal {
    private static String TAG = "YearModal";
    private long id;
    private int year;

    public YearModal(long id, int year) {
        this.id = id;
        this.year = year;
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

    public static void insertIntoTable(SQLiteDatabase db, List<YearModal> yearModals) {
        for (YearModal yearModal : yearModals) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", yearModal.getId());
            contentValues.put("YEAR", yearModal.getYear());
            db.insert("YEARTABLE", null, contentValues);
        }
        db.close();
    }
  
    public static YearModal returnYear(SQLiteDatabase dbReader, int year) {
        Cursor c = dbReader.rawQuery("SELECT * FROM YEARTABLE WHERE YEAR ='" + year + "'", null);
        c.moveToFirst();
        YearModal yearModal = new YearModal(c.getLong(0), c.getInt(1));
        c.close();
        dbReader.close();
        return yearModal;
    }

    public static List<YearModal> returnAll(SQLiteDatabase dbReader) {
        List<YearModal> yearModals = new ArrayList<>();
        Cursor c = dbReader.rawQuery("SELECT * FROM YEARTABLE", null);
        if(c.moveToFirst()){
            do{
                yearModals.add(new YearModal(c.getLong(0), c.getInt(1)));
            } while(c.moveToNext());
        }
        return yearModals;
    }

}
