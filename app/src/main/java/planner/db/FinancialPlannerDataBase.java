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
        createIconTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);

    }

    private void createExpensePlannerTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE EXPENSEPLANTABLE(ID BIGINT PRIMARY KEY, PLANID BIGINT, PLANTYPE INTEGER, PLANTYPENAME TEXT, DESCRIPTION TEXT, MONTHID BIGINT," +
                "MONTH INTEGER,MONTHNAME TEXT, YEARID BIGINT, YEARNAME INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    private void createIncomePlannerTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE INCOMEPLANTABLE(ID BIGINT PRIMARY KEY, PLANID BIGINT, DESCRIPTION TEXT, MONTHID BIGINT," +
                "MONTH INTEGER, MONTHNAME TEXT, YEARID BIGINT, YEARNAME INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    private void createPlanTypeTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE PLANTYPETABLE(ID BIGINT PRIMARY KEY, NAME TEXT, TYPEID BIGINT, TYPE INTEGER, TYPENAME TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    private void createWalletPlannerTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE WALLETPLANTABLE(ID BIGINT PRIMARY KEY, NAME TEXT, ICONID BIGINT, ICON INTEGER, ICONNAME TEXT, INCOMEBALANCE FLOAT, EXPENSEBALANCE FLOAT, BALANCE FLOAT)";
        db.execSQL(CREATE_TABLE);
    }

    private void createCategoryItemTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE CATEGORYITEMTABLE(ID BIGINT PRIMARY KEY, NAME TEXT, TYPEID BIGINT, TYPE INTEGER, TYPENAME TEXT, CATEGORYCOLOR INTEGER)";
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
        String CREATE_TABLE = "CREATE TABLE PLANNEDEXPENSETABLE(ID BIGINT PRIMARY KEY, TRANSACTIONID BIGINT, ACCOUNTID BIGINT, ACCOUNTNAME TEXT, PLANID BIGINT, PLANNAME TEXT" +
                ", CATEGORYID BIGINT, CATEGORYNAME TEXT, DATE TEXT, AMOUNT FLOAT, PLANNED BOOL, CATEGORYCOLOR INTEGER, DAY TEXT, MONTH TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    private void createActualExpenseTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE ACTUALEXPENSETABLE(ID BIGINT PRIMARY KEY, TRANSACTIONID BIGINT, ACCOUNTID BIGINT, ACCOUNTNAME TEXT, PLANID BIGINT, PLANNAME TEXT" +
                ", CATEGORYID BIGINT, CATEGORYNAME TEXT, DATE TEXT, AMOUNT FLOAT, PLANNED BOOL, CATEGORYCOLOR INTEGER, DAY TEXT, MONTH TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    private void createPlannedIncomeTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE PLANNEDINCOMETABLE(ID BIGINT PRIMARY KEY, TRANSACTIONID BIGINT, ACCOUNTID BIGINT, ACCOUNTNAME TEXT, PLANID BIGINT, PLANNAME TEXT" +
                ", CATEGORYID BIGINT, CATEGORYNAME TEXT, DATE TEXT, AMOUNT FLOAT, PLANNED BOOL, CATEGORYCOLOR INTEGER, DAY TEXT, MONTH TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    private void createActualIncomeTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE ACTUALINCOMETABLE(ID BIGINT PRIMARY KEY, TRANSACTIONID BIGINT, ACCOUNTID BIGINT, ACCOUNTNAME TEXT, PLANID BIGINT, PLANNAME TEXT" +
                ", CATEGORYID BIGINT, CATEGORYNAME TEXT, DATE TEXT, AMOUNT FLOAT, PLANNED BOOL, CATEGORYCOLOR INTEGER, DAY TEXT, MONTH TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    private void createTransactionTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE TRANSACTIONTABLE(ID BIGINT PRIMARY KEY, DATE TEXT, TIMESTAMP BIGINT, PLANID BIGINT, PLANNAME TEXT," +
                "ACCOUNTID BIGINT, ACCOUNTNAME TEXT, PREVIOUSAMOUNT FLOAT, CURRENTAMOUNT FLOAT, CATEGORYID BIGINT, CATEGORYTYPE INTEGER, CATEGORYNAME TEXT," +
                "TRANSACTIONTYPE INTEGER, TRANSACTIONNAME TEXT, PLANNED BOOL, CATEGORYCOLOR INTEGER, DAY TEXT, MONTH TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    private void createIconTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE ICONTABLE(ID BIGINT PRIMARY KEY, ICON INTEGER, ICONNAME TEXT)";
        db.execSQL(CREATE_TABLE);
    }
}
