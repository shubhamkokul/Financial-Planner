package planner.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FinancialDatabaseWriter extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "financialDB.db";

    public FinancialDatabaseWriter(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    public SQLiteDatabase getDatabaseWriter() {
        return this.getWritableDatabase();
    }

    public SQLiteDatabase getDatabaseReader() {
        return this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
