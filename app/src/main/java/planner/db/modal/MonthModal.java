package planner.db.modal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

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

}
