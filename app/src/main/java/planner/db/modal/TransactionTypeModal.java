package planner.db.modal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class TransactionTypeModal {
    private long id;
    private int type;
    private String typeName;

    public TransactionTypeModal(long id, int type, String typeName) {
        this.id = id;
        this.type = type;
        this.typeName = typeName;
    }

    public long getId() {
        return id;
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

    public static void insertIntoTable(SQLiteDatabase db, List<TransactionTypeModal> transactionTypeModals) {
        for(TransactionTypeModal transactionTypeModal : transactionTypeModals){
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", transactionTypeModal.getId());
            contentValues.put("TYPE", transactionTypeModal.getType());
            contentValues.put("TYPENAME", transactionTypeModal.getTypeName());
            db.insert("TRANSACTIONTYPETABLE", null, contentValues);
        }
        db.close();
    }
}
