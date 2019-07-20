package planner.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FinancialPlannerDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "financialDB.db";

    public FinancialPlannerDataBase(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
        this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createExpensePlannerTable(db);
        createIncomePlannerTable(db);
        createPlanTypeTable(db);
        createWalletPlannerTable(db);
        createCategoryItemTable(db);
        createMonthTable(db);
        createYearTable(db);
        createTransactionTypeTable(db);
        createPlannedExpenseTable(db);
        createActualExpenseTable(db);
        createPlannedIncomeTable(db);
        createActualIncomeTable(db);
        createTransactionTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);

    }

    private void createExpensePlannerTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE EXPENSEPLANTABLE(ID BIGINT PRIMARY KEY, PLANTYPE INTEGER, PLANTYPENAME TEXT, DESCRIPTION TEXT, MONTHID INTEGER," +
                "MONTHNAME TEXT, YEARID INTEGER, YEARNAME INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    private void createIncomePlannerTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE INCOMEPLANTABLE(ID BIGINT PRIMARY KEY, DESCRIPTION TEXT, MONTHID INTEGER," +
                "MONTHNAME TEXT, YEARID INTEGER, YEARNAME INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    private void createPlanTypeTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE PLAYTYPETABLE(ID BIGINT PRIMARY KEY, NAME TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    private void createWalletPlannerTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE WALLETPLANTABLE(ID BIGINT PRIMARY KEY, NAME TEXT, ICON INTEGER, ICONNAME TEXT, AMOUNT FLOAT)";
        db.execSQL(CREATE_TABLE);
    }

    private void createCategoryItemTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE CATEGORYITEMTABLE(ID BIGINT PRIMARY KEY, NAME TEXT, TYPE INTEGER, TYPENAME TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    private void createMonthTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE MONTHTABLE(ID BIGINT PRIMARY KEY, ACTUALPOSITION INTEGER, NAME TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    private void createYearTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE YEARTABLE(ID BIGINT PRIMARY KEY, YEAR INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    private void createTransactionTypeTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE TRANSACTIONTYPETABLE(ID BIGINT PRIMARY KEY, TYPE INTEGER, TYPENAME TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    private void createPlannedExpenseTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE PLANNEDEXPENSETABLE(ID BIGINT PRIMARY KEY, ACCOUNTID INTEGER, ACCOUNTNAME TEXT, PLANID INTEGER, PLANNAME TEXT" +
                ", CATEGORYID INTEGER, CATEGORYNAME TEXT, DATE DATE, AMOUNT FLOAT, PLANNED BOOL)";
        db.execSQL(CREATE_TABLE);
    }

    private void createActualExpenseTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE ACTUALEXPENSETABLE(ID BIGINT PRIMARY KEY, ACCOUNTID INTEGER, ACCOUNTNAME TEXT, PLANID INTEGER, PLANNAME TEXT" +
                ", CATEGORYID INTEGER, CATEGORYNAME TEXT, DATE DATE, AMOUNT FLOAT, PLANNED BOOL)";
        db.execSQL(CREATE_TABLE);
    }

    private void createPlannedIncomeTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE PLANNEDINCOMETABLE(ID BIGINT PRIMARY KEY, ACCOUNTID INTEGER, ACCOUNTNAME TEXT, PLANID INTEGER, PLANNAME TEXT" +
                ", CATEGORYID INTEGER, CATEGORYNAME TEXT, DATE DATE, AMOUNT FLOAT, PLANNED BOOL)";
        db.execSQL(CREATE_TABLE);
    }

    private void createActualIncomeTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE ACTUALINCOMETABLE(ID BIGINT PRIMARY KEY, ACCOUNTID INTEGER, ACCOUNTNAME TEXT, PLANID INTEGER, PLANNAME TEXT" +
                ", CATEGORYID INTEGER, CATEGORYNAME TEXT, DATE DATE, AMOUNT FLOAT, PLANNED BOOL)";
        db.execSQL(CREATE_TABLE);
    }

    private void createTransactionTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE TRANSACTIONTABLE(ID BIGINT PRIMARY KEY, DATE DATE, TIMESTAMP INTEGER, PLANID INTEGER, PLANNAME TEXT," +
                "ACCOUNTID INTEGER, ACCOUNTNAME TEXT, PREVIOUSAMOUNT FLOAT, CURRENTAMOUNT FLOAT, CATEGORYTYPE INTEGER, CATEGORYNAME TEXT," +
                "TRANSACTIONTYPE INTEGER, TRANSACTIONNAME TEXT, PLANNED BOOL)";
        db.execSQL(CREATE_TABLE);
    }
}
