package planner.db.modal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class CategoryItemModal {
    private long id;
    private String name;
    private long typeID;
    private int type;
    private String typeName;



    public CategoryItemModal(long id, String name, long typeID, int type, String typeName) {
        this.id = id;
        this.name = name;
        this.typeID = typeID;
        this.type = type;
        this.typeName = typeName;
    }

    public static void insertIntoTable(SQLiteDatabase dbWriter, List<CategoryItemModal> categoryItemModals) {
        for(CategoryItemModal categoryItemModal  : categoryItemModals){
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", categoryItemModal.getId());
            contentValues.put("NAME", categoryItemModal.getName());
            contentValues.put("TYPEID", categoryItemModal.getTypeID());
            contentValues.put("TYPE", categoryItemModal.getType());
            contentValues.put("TYPENAME", categoryItemModal.getTypeName());
            dbWriter.insert("CATEGORYITEMTABLE", null, contentValues);
        }
        dbWriter.close();
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

    public long getTypeID() {
        return typeID;
    }

    public void setTypeID(long typeID) {
        this.typeID = typeID;
    }

}
