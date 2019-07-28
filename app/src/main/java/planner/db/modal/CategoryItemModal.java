package planner.db.modal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CategoryItemModal {
    private long id;
    private String name;
    private long typeID;
    private int type;
    private String typeName;
    private int color;

    public CategoryItemModal(long id, String name, long typeID, int type, String typeName, int color) {
        this.id = id;
        this.name = name;
        this.typeID = typeID;
        this.type = type;
        this.typeName = typeName;
        this.color = color;
    }


    public long getTypeID() {
        return typeID;
    }

    public void setTypeID(long typeID) {
        this.typeID = typeID;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public static void insertIntoTable(SQLiteDatabase db, List<CategoryItemModal> categoryItemModals) {
        for (CategoryItemModal categoryItemModal : categoryItemModals) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", categoryItemModal.getId());
            contentValues.put("NAME", categoryItemModal.getName());
            contentValues.put("TYPEID", categoryItemModal.getTypeID());
            contentValues.put("TYPE", categoryItemModal.getType());
            contentValues.put("TYPENAME", categoryItemModal.getTypeName());
            contentValues.put("CATEGORYCOLOR", categoryItemModal.getColor());
            db.insert("CATEGORYITEMTABLE", null, contentValues);
        }
        db.close();
    }

    public static List<CategoryItemModal> returnAll(SQLiteDatabase dbReader, int type) {
        List<CategoryItemModal> categoryItemModals = new ArrayList<>();
        Cursor c = dbReader.rawQuery("SELECT * FROM CATEGORYITEMTABLE WHERE TYPE = '" + type + "'", null);
        if (c.moveToFirst()) {
            do {
                categoryItemModals.add(new CategoryItemModal(c.getLong(0), c.getString(1), c.getLong(2), c.getInt(3), c.getString(4), c.getInt(5)));
            } while (c.moveToNext());
        }
        dbReader.close();
        return categoryItemModals;
    }

}
