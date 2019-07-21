package planner.db.modal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PlanTypeModal {
    private static final String TAG = "PlanTypeModal";
    private long id;
    private String name;
    private long typeID;
    private int type;
    private String typeName;


    public PlanTypeModal(long id, String name, long typeID, int type, String typeName) {
        this.id = id;
        this.name = name;
        this.typeID = typeID;
        this.type = type;
        this.typeName = typeName;
    }



    public long getTypeID() {
        return typeID;
    }

    public void setTypeID(long typeID) {
        this.typeID = typeID;
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

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void insertIntoTable(SQLiteDatabase db, List<PlanTypeModal> planTypeModals) {
        for (PlanTypeModal planTypeModal : planTypeModals) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", planTypeModal.getId());
            contentValues.put("NAME", planTypeModal.getName());
            contentValues.put("TYPEID", planTypeModal.getTypeID());
            contentValues.put("TYPE", planTypeModal.getType());
            contentValues.put("TYPENAME", planTypeModal.getTypeName());
            db.insert("PLANTYPETABLE", null, contentValues);
        }
        db.close();
    }

    public static PlanTypeModal returnPlanType(SQLiteDatabase db, String planType) {
        Cursor c = db.rawQuery("SELECT * FROM PLANTYPETABLE WHERE NAME ='"+planType+"'" ,null);
        c.moveToFirst();
        PlanTypeModal planTypeModal = new PlanTypeModal(c.getLong(0), c.getString(1), c.getInt(2), c.getInt(3), c.getString(4));
        c.close();
        db.close();
        return planTypeModal;
    }

    public static List<PlanTypeModal> returnAll(SQLiteDatabase dbReader) {
        List<PlanTypeModal> planTypeModals = new ArrayList<>();
        Cursor c = dbReader.rawQuery("SELECT * FROM PLANTYPETABLE", null);
        if (c.moveToFirst()){
            do {
                planTypeModals.add(new PlanTypeModal(c.getLong(0), c.getString(1), c.getLong(2), c.getInt(3), c.getString(4)));
            } while(c.moveToNext());
        }
        c.close();
        dbReader.close();
        return planTypeModals;
    }
}
