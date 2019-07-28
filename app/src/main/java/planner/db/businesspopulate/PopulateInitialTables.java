package planner.db.businesspopulate;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mumbai.financial.financialplanner.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import planner.db.FinancialDatabaseOperation;
import planner.db.modal.CategoryItemModal;
import planner.db.modal.ExpensePlannerModal;
import planner.db.modal.IconModal;
import planner.db.modal.IncomePlannerModal;
import planner.db.modal.MonthModal;
import planner.db.modal.PlanTypeModal;
import planner.db.modal.TransactionTypeModal;
import planner.db.modal.WalletPlannerModal;
import planner.db.modal.YearModal;
import planner.utility.Utility;

public class PopulateInitialTables {
    private static final String TAG = "PopulateInitialTables";
    private Context context;

    public PopulateInitialTables(Context context) {
        this.context = context;
        populateTransactionTypeTable();
        populateMonthTable();
        populateYearTable();
        populateCategoryItemTable();
        populatePlanTypeTable();
        populateIconTable();
        populateWalletPlanTable();
        populateExpensePlanTable();
        populateIncomePlanTable();
    }

    public void populateTransactionTypeTable() {
        SQLiteDatabase dbWriter = new FinancialDatabaseOperation(this.context, 1).getDatabaseWriter();
        List<TransactionTypeModal> transactionTypeModals = new ArrayList<>();
        long timestamp = Utility.timeStampGenerator();
        int type = 0;
        String typeName = "EXPENSE";
        transactionTypeModals.add(new TransactionTypeModal(timestamp, type, typeName));
        timestamp = Utility.timeStampGenerator();
        type = 1;
        typeName = "INCOME";
        transactionTypeModals.add(new TransactionTypeModal(timestamp, type, typeName));
        TransactionTypeModal.insertIntoTable(dbWriter, transactionTypeModals);

    }

    public void populateMonthTable() {
        SQLiteDatabase dbWriter = new FinancialDatabaseOperation(this.context, 1).getDatabaseWriter();
        List<MonthModal> monthModals = new ArrayList<>();
        long timestamp = Utility.timeStampGenerator();
        int actualPosition = 1;
        String name = "January";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = Utility.timeStampGenerator();
        actualPosition = 2;
        name = "February";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = Utility.timeStampGenerator();
        actualPosition = 3;
        name = "March";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = Utility.timeStampGenerator();
        actualPosition = 4;
        name = "April";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = Utility.timeStampGenerator();
        actualPosition = 5;
        name = "May";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = Utility.timeStampGenerator();
        actualPosition = 6;
        name = "June";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = Utility.timeStampGenerator();
        actualPosition = 7;
        name = "July";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = Utility.timeStampGenerator();
        actualPosition = 8;
        name = "August";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = Utility.timeStampGenerator();
        actualPosition = 9;
        name = "September";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = Utility.timeStampGenerator();
        actualPosition = 10;
        name = "October";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = Utility.timeStampGenerator();
        actualPosition = 11;
        name = "November";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        timestamp = Utility.timeStampGenerator();
        actualPosition = 12;
        name = "December";
        monthModals.add(new MonthModal(timestamp, actualPosition, name));
        MonthModal.insertIntoTable(dbWriter, monthModals);
    }

    public void populateYearTable() {
        SQLiteDatabase dbWriter = new FinancialDatabaseOperation(this.context, 1).getDatabaseWriter();
        List<YearModal> yearModals = new ArrayList<>();
        long timestamp = Utility.timeStampGenerator();
        int year = 2019;
        yearModals.add(new YearModal(timestamp, year));
        timestamp = Utility.timeStampGenerator();
        year = 2020;
        yearModals.add(new YearModal(timestamp, year));
        timestamp = Utility.timeStampGenerator();
        year = 2021;
        yearModals.add(new YearModal(timestamp, year));
        timestamp = Utility.timeStampGenerator();
        year = 2022;
        yearModals.add(new YearModal(timestamp, year));
        timestamp = Utility.timeStampGenerator();
        year = 2023;
        yearModals.add(new YearModal(timestamp, year));
        timestamp = Utility.timeStampGenerator();
        year = 2024;
        yearModals.add(new YearModal(timestamp, year));
        timestamp = Utility.timeStampGenerator();
        year = 2025;
        yearModals.add(new YearModal(timestamp, year));
        YearModal.insertIntoTable(dbWriter, yearModals);
    }

    public void populateCategoryItemTable() {
        List<CategoryItemModal> categoryItemModals = new ArrayList<>();
        SQLiteDatabase dbReader = new FinancialDatabaseOperation(this.context, 1).getDatabaseReader();
        TransactionTypeModal transactionTypeModal = TransactionTypeModal.returnType(dbReader, "EXPENSE");
        long id = Utility.timeStampGenerator();
        String name = "Accommodation";
        int color = R.color.colorAccent;
        long typeID = transactionTypeModal.getId();
        int type = transactionTypeModal.getType();
        String typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Bills";
        color = R.color.dark_goldenrod;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Car";
        color = R.color.fire_brick;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Credit Card";
        color = R.color.sienna;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Entertainment";
        color = R.color.light_sky_blue;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Miscellaneous";
        color = R.color.olive_drab;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Fees";
        color = R.color.medium_sea_green;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Travel";
        color = R.color.aqua;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Grocery";
        color = R.color.maroon;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Food";
        color = R.color.indigo;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Tax";
        color = R.color.forest_green;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Rent";
        color = R.color.lime;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Sports and Rec";
        color = R.color.dark_blue;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Vacation";
        color = R.color.hot_pink;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Investment";
        color = R.color.cold;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Personal Care";
        color = R.color.blanched_almond;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Health";
        color = R.color.lavender_blush;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        dbReader = new FinancialDatabaseOperation(this.context, 1).getDatabaseReader();
        transactionTypeModal = TransactionTypeModal.returnType(dbReader, "INCOME");
        id = Utility.timeStampGenerator();
        name = "Gift";
        color = R.color.coral;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Salary";
        color = R.color.thistle;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        id = Utility.timeStampGenerator();
        name = "Miscellaneous";
        color = R.color.medium_orchid;
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        categoryItemModals.add(new CategoryItemModal(id, name, typeID, type, typeName, color));
        SQLiteDatabase dbWriter = new FinancialDatabaseOperation(this.context, 1).getDatabaseWriter();
        CategoryItemModal.insertIntoTable(dbWriter, categoryItemModals);
    }

    public void populatePlanTypeTable() {
        List<PlanTypeModal> planTypeModals = new ArrayList<>();
        SQLiteDatabase dbReader = new FinancialDatabaseOperation(this.context, 1).getDatabaseReader();
        TransactionTypeModal transactionTypeModal = TransactionTypeModal.returnType(dbReader, "EXPENSE");
        long id = Utility.timeStampGenerator();
        String name = "Monthly";
        long typeID = transactionTypeModal.getId();
        int type = transactionTypeModal.getType();
        String typeName = transactionTypeModal.getTypeName();
        planTypeModals.add(new PlanTypeModal(id, name, typeID, type, typeName));
        id = Utility.timeStampGenerator();
        name = "Other";
        typeID = transactionTypeModal.getId();
        type = transactionTypeModal.getType();
        typeName = transactionTypeModal.getTypeName();
        planTypeModals.add(new PlanTypeModal(id, name, typeID, type, typeName));
        SQLiteDatabase dbWriter = new FinancialDatabaseOperation(this.context, 1).getDatabaseWriter();
        PlanTypeModal.insertIntoTable(dbWriter, planTypeModals);
    }

    public void populateIconTable() {
        SQLiteDatabase dbWriter = new FinancialDatabaseOperation(this.context, 1).getDatabaseWriter();
        List<IconModal> iconModals = new ArrayList<>();
        long id = Utility.timeStampGenerator();
        int icon = R.drawable.ic_bank_icon;
        String iconName = "Bank";
        iconModals.add(new IconModal(id, icon, iconName));
        id = Utility.timeStampGenerator();
        icon = R.drawable.ic_bank_icon;
        iconName = "Cash";
        iconModals.add(new IconModal(id, icon, iconName));
        id = Utility.timeStampGenerator();
        icon = R.drawable.ic_bank_icon;
        iconName = "Credit Card";
        iconModals.add(new IconModal(id, icon, iconName));
        id = Utility.timeStampGenerator();
        icon = R.drawable.ic_bank_icon;
        iconName = "Gift Card";
        iconModals.add(new IconModal(id, icon, iconName));
        IconModal.insertIntoTable(dbWriter, iconModals);

    }

    public void populateWalletPlanTable() {
        SQLiteDatabase dbReader = new FinancialDatabaseOperation(this.context, 1).getDatabaseReader();
        List<WalletPlannerModal> walletPlannerModals = new ArrayList<>();
        List<IconModal> iconModals = IconModal.returnAll(dbReader);
        SQLiteDatabase dbWriter = new FinancialDatabaseOperation(this.context, 1).getDatabaseWriter();
        int i = 0;
        while (i < 2) {
            long id = Utility.timeStampGenerator();
            String name = iconModals.get(i).getIconName();
            long iconID = iconModals.get(i).getId();
            int icon = iconModals.get(i).getIcon();
            String iconName = iconModals.get(i).getIconName();
            double incomeBalance = 0;
            double expenseBalance = 0;
            double balance = 0;
            walletPlannerModals.add(new WalletPlannerModal(id, name, iconID, icon, iconName, incomeBalance, expenseBalance, balance));
            i++;
        }
        WalletPlannerModal.insertIntoTableInitial(dbWriter, walletPlannerModals);

    }

    public void populateExpensePlanTable() {
        SQLiteDatabase dbWriter = new FinancialDatabaseOperation(this.context, 1).getDatabaseWriter();
        List<ExpensePlannerModal> expensePlannerModals = new ArrayList<>();
        Date date = new Date();
        SQLiteDatabase dbReader = new FinancialDatabaseOperation(this.context, 1).getDatabaseReader();
        List<MonthModal> monthModals = MonthModal.returnAll(dbReader);
        dbReader = new FinancialDatabaseOperation(this.context, 1).getDatabaseReader();
        PlanTypeModal planTypeModal = PlanTypeModal.returnPlanType(dbReader, "Monthly");
        dbReader = new FinancialDatabaseOperation(this.context, 1).getDatabaseReader();
        YearModal yearModal = YearModal.returnYear(dbReader, Calendar.getInstance().get(Calendar.YEAR));
        int i = 0;
        while (i < 3) {
            long id = Utility.timeStampGenerator();
            long planID = planTypeModal.getId();
            int planType = planTypeModal.getType();
            String planTypeName = planTypeModal.getName();
            String description = "All my " + planTypeModal.getTypeName().toLowerCase() + " for " + monthModals.get(date.getMonth() + i).getName() + " " + yearModal.getYear();
            long monthID = monthModals.get(date.getMonth() + i).getId();
            int month = monthModals.get(date.getMonth() + i).getActualPosition();
            String monthName = monthModals.get(date.getMonth() + i).getName();
            long yearID = yearModal.getId();
            int yearName = yearModal.getYear();
            expensePlannerModals.add(new ExpensePlannerModal(id, planID, planType, planTypeName, description, monthID, month, monthName, yearID, yearName));
            i++;
        }
        ExpensePlannerModal.insertIntoTableInitial(dbWriter, expensePlannerModals);
    }

    public void populateIncomePlanTable() {
        List<IncomePlannerModal> incomePlannerModals = new ArrayList<>();
        Date date = new Date();
        SQLiteDatabase dbReader = new FinancialDatabaseOperation(this.context, 1).getDatabaseReader();
        List<MonthModal> monthModals = MonthModal.returnAll(dbReader);
        dbReader = new FinancialDatabaseOperation(this.context, 1).getDatabaseReader();
        YearModal yearModal = YearModal.returnYear(dbReader, Calendar.getInstance().get(Calendar.YEAR));
        int i = 0;
        while (i < 3) {
            long id = Utility.timeStampGenerator();
            long planID = 999999999;
            String description = "All my income for " + monthModals.get(date.getMonth() + i).getName() + " " + yearModal.getYear();
            long monthID = monthModals.get(date.getMonth() + i).getId();
            int month = monthModals.get(date.getMonth() + i).getActualPosition();
            String monthName = monthModals.get(date.getMonth() + i).getName();
            long yearID = yearModal.getId();
            int yearName = yearModal.getYear();
            incomePlannerModals.add(new IncomePlannerModal(id, 999999999, description, monthID, month, monthName, yearID, yearName));
            i++;
        }
        SQLiteDatabase dbWriter = new FinancialDatabaseOperation(this.context, 1).getDatabaseWriter();
        IncomePlannerModal.insertIntoTableInitial(dbWriter, incomePlannerModals);
    }
}
