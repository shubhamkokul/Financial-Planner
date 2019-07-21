package planner.db.modal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class IconModal {
    private final static String TAG = "IconModal";
    private long id;
    private int icon;
    private String iconName;


    public long getId() {
        return id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public IconModal(long id, int icon, String iconName) {
        this.id = id;
        this.icon = icon;
        this.iconName = iconName;
    }

    public static void insertIntoTable(SQLiteDatabase dbWriter, List<IconModal> iconModals) {
        for (IconModal iconModal : iconModals) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", iconModal.getId());
            contentValues.put("ICON", iconModal.getIcon());
            contentValues.put("ICONNAME", iconModal.getIconName());
            dbWriter.insert("ICONTABLE", null, contentValues);
        }
        dbWriter.close();
    }


    public static List<IconModal> returnAll(SQLiteDatabase dbReader) {
        List<IconModal> iconModals = new ArrayList<>();
        Cursor c = dbReader.rawQuery("SELECT * FROM ICONTABLE", null);
        if (c.moveToFirst()){
            do {
                iconModals.add(new IconModal(c.getLong(0), c.getInt(1), c.getString(2)));
            } while(c.moveToNext());
        }
        c.close();
        dbReader.close();
        return iconModals;
    }
}
