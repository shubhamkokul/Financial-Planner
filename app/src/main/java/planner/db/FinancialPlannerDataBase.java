package planner.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FinancialPlannerDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "financialDB.db";

    public FinancialPlannerDataBase(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
        SQLiteDatabase db = this.getWritableDatabase();

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

    }
    public void createExpensePlannerTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE EXPENSEPLANTABLE(ID INTEGER PRIMARY KEY, PLANTYPE INTEGER, PLANTYPENAME TEXT, DESCRIPTION TEXT, MONTHID INTEGER," +
                "MONTHNAME TEXT, YEARID INTEGER, YEARNAME INTEGER)";
        db.execSQL(CREATE_TABLE);
    }
    public void createIncomePlannerTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE INCOMEPLANTABLE(ID INTEGER PRIMARY KEY, DESCRIPTION TEXT, MONTHID INTEGER," +
                "MONTHNAME TEXT, YEARID INTEGER, YEARNAME INTEGER)";
        db.execSQL(CREATE_TABLE);
    }
    public void createPlanTypeTable(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE PLAYTYPETABLE(ID INTEGER PRIMARY KEY, NAME TEXT)";
        db.execSQL(CREATE_TABLE);
    }
    public void createWalletPlannerTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE WALLETPLANTABLE(ID INTEGER PRIMARY KEY, NAME TEXT, ICON INTEGER, ICONNAME TEXT, AMOUNT FLOAT)";
        db.execSQL(CREATE_TABLE);
    }
    public void createCategoryItemTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE CATEGORYITEMTABLE(ID INTEGER PRIMARY KEY, NAME TEXT, TYPE INTEGER, TYPENAME TEXT)";
        db.execSQL(CREATE_TABLE);
    }
    public void createMonthTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE MONTHTABLE(ID INTEGER PRIMARY KEY, ACTUALPOSITION INTEGER, NAME TEXT)";
        db.execSQL(CREATE_TABLE);
    }
    public void createYearTable(SQLiteDatabase db) {
        String CREATE_TABLE ="CREATE TABLE YEARTABLE(ID INTEGER PRIMARY KEY, YEAR INTEGER)";
        db.execSQL(CREATE_TABLE);
    }
    public void createTransactionTypeTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE TRANSACTIONTYPETABLE(ID INTEGER PRIMARY KEY, TYPE INTEGER, TYPENAME TEXT)";
        db.execSQL(CREATE_TABLE);
    }
    public void createPlannedExpenseTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE PLANNEDEXPENSETABLE(ID INTEGER PRIMARY KEY, ACCOUNTID INTEGER, ACCOUNTNAME TEXT, PLANID INTEGER, PLANNAME TEXT" +
                ", CATEGORYID INTEGER, CATEGORYNAME TEXT, DATE DATE, AMOUNT FLOAT, PLANNED BOOL)";
        db.execSQL(CREATE_TABLE);
    }
    public void createActualExpenseTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE ACTUALEXPENSETABLE(ID INTEGER PRIMARY KEY, ACCOUNTID INTEGER, ACCOUNTNAME TEXT, PLANID INTEGER, PLANNAME TEXT" +
                ", CATEGORYID INTEGER, CATEGORYNAME TEXT, DATE DATE, AMOUNT FLOAT, PLANNED BOOL)";
        db.execSQL(CREATE_TABLE);
    }
    public void createPlannedIncomeTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE PLANNEDINCOMETABLE(ID INTEGER PRIMARY KEY, ACCOUNTID INTEGER, ACCOUNTNAME TEXT, PLANID INTEGER, PLANNAME TEXT" +
                ", CATEGORYID INTEGER, CATEGORYNAME TEXT, DATE DATE, AMOUNT FLOAT, PLANNED BOOL)";
        db.execSQL(CREATE_TABLE);
    }
    public void createActualIncomeTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE ACTUALINCOMETABLE(ID INTEGER PRIMARY KEY, ACCOUNTID INTEGER, ACCOUNTNAME TEXT, PLANID INTEGER, PLANNAME TEXT" +
                ", CATEGORYID INTEGER, CATEGORYNAME TEXT, DATE DATE, AMOUNT FLOAT, PLANNED BOOL)";
        db.execSQL(CREATE_TABLE);
    }
    public void createTransactionTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE TRANSACTIONTABLE(ID INTEGER PRIMARY KEY, DATE DATE, TIMESTAMP INTEGER, PLANID INTEGER, PLANNAME TEXT," +
                "ACCOUNTID INTEGER, ACCOUNTNAME TEXT, PREVIOUSAMOUNT FLOAT, CURRENTAMOUNT FLOAT, CATEGORYTYPE INTEGER, CATEGORYNAME TEXT," +
                "TRANSACTIONTYPE INTEGER, TRANSACTIONNAME TEXT, PLANNED BOOL)";
        db.execSQL(CREATE_TABLE);
    }
}
