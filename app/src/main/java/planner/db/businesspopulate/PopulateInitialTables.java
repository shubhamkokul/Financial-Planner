package planner.db.businesspopulate;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import planner.db.FinancialDatabaseWriter;
import planner.db.modal.MonthModal;
import planner.db.modal.TransactionTypeModal;
import planner.db.modal.YearModal;
import planner.utility.IdentifierGenerator;

public class PopulateInitialTables {
    private Context context;
    private FinancialDatabaseWriter financialDatabaseWriter;
    public PopulateInitialTables(Context context){
        this.context = context;
        financialDatabaseWriter = new FinancialDatabaseWriter(context, 1);
        populateTransactionTypeTable();
        populateMonthTable();
        populateYearTable();
    }
    public void populateTransactionTypeTable() {
        SQLiteDatabase db = financialDatabaseWriter.getDatabaseWriter();
        List<TransactionTypeModal> transactionTypeModals = new ArrayList<>();
        long timestamp = IdentifierGenerator.timeStampGenerator();
        int type = 0;
        String typeName = "EXPENSE";
        transactionTypeModals.add(new TransactionTypeModal(timestamp, type, typeName));
        timestamp = IdentifierGenerator.timeStampGenerator();
        type = 1;
        typeName = "INCOME";
        transactionTypeModals.add(new TransactionTypeModal(timestamp, type, typeName));
        TransactionTypeModal.insertIntoTable(db, transactionTypeModals);

    }
    public void populateMonthTable(){
        SQLiteDatabase db = financialDatabaseWriter.getDatabaseWriter();
        List<MonthModal> monthModals = new ArrayList<>();
        long timestamp = IdentifierGenerator.timeStampGenerator();
        int actualPosition = 1;
        String name = "January";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = IdentifierGenerator.timeStampGenerator();
        actualPosition = 2;
        name = "February";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = IdentifierGenerator.timeStampGenerator();
        actualPosition = 3;
        name = "March";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = IdentifierGenerator.timeStampGenerator();
        actualPosition = 4;
        name = "April";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = IdentifierGenerator.timeStampGenerator();
        actualPosition = 5;
        name = "May";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = IdentifierGenerator.timeStampGenerator();
        actualPosition = 6;
        name = "June";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = IdentifierGenerator.timeStampGenerator();
        actualPosition = 7;
        name = "July";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = IdentifierGenerator.timeStampGenerator();
        actualPosition = 8;
        name = "August";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = IdentifierGenerator.timeStampGenerator();
        actualPosition = 9;
        name = "September";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = IdentifierGenerator.timeStampGenerator();
        actualPosition = 10;
        name = "October";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = IdentifierGenerator.timeStampGenerator();
        actualPosition = 11;
        name = "November";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = IdentifierGenerator.timeStampGenerator();
        actualPosition = 12;
        name = "December";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        MonthModal.insertIntoTable(db, monthModals);
    }
    public void populateYearTable(){
        SQLiteDatabase db = financialDatabaseWriter.getDatabaseWriter();
        List<YearModal> yearModals = new ArrayList<>();
        long timestamp = IdentifierGenerator.timeStampGenerator();
        int year = 2019;
        yearModals.add(new YearModal(timestamp, year));
        timestamp = IdentifierGenerator.timeStampGenerator();
        year = 2020;
        yearModals.add(new YearModal(timestamp, year));
        timestamp = IdentifierGenerator.timeStampGenerator();
        year = 2021;
        yearModals.add(new YearModal(timestamp, year));
        timestamp = IdentifierGenerator.timeStampGenerator();
        year = 2022;
        yearModals.add(new YearModal(timestamp, year));
        timestamp = IdentifierGenerator.timeStampGenerator();
        year = 2023;
        yearModals.add(new YearModal(timestamp, year));
        timestamp = IdentifierGenerator.timeStampGenerator();
        year = 2024;
        yearModals.add(new YearModal(timestamp, year));
        timestamp = IdentifierGenerator.timeStampGenerator();
        year = 2025;
        yearModals.add(new YearModal(timestamp, year));
        YearModal.insertIntoTable(db, yearModals);
    }
}
