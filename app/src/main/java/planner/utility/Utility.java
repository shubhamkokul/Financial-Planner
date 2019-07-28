package planner.utility;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import planner.db.modal.ActualExpenseModal;
import planner.db.modal.ActualIncomeModal;
import planner.db.modal.PlannedExpenseModal;
import planner.db.modal.PlannedIncomeModal;

public class Utility {

    public static long timeStampGenerator() {
        return new Timestamp(System.currentTimeMillis()).getTime() + new Random().nextInt();
    }

    public static HashMap<String, String> mergeCategoriesActualExpense(List<ActualExpenseModal> actualExpenseModals) {
        HashMap<String, String> pieMerge = new HashMap<>();
        for (ActualExpenseModal actualExpenseModal : actualExpenseModals) {
            if (pieMerge.containsKey(actualExpenseModal.getCategoryName())) {
                double amount = actualExpenseModal.getAmount();
                double amountValue = Double.parseDouble(pieMerge.get(actualExpenseModal.getCategoryName()));
                double newAmount = amount + amountValue;
                pieMerge.replace(actualExpenseModal.getCategoryName(), newAmount + "");
            } else {
                pieMerge.put(actualExpenseModal.getCategoryName(), actualExpenseModal.getAmount() + "");
            }
        }
        return pieMerge;
    }

    public static List<Integer> mergeColorActualExpense(List<ActualExpenseModal> actualExpenseModals) {
        List mergeColor = new ArrayList();
        HashMap<String, Double> pieMerge = new HashMap<>();
        for (ActualExpenseModal actualExpenseModal : actualExpenseModals) {
            if (pieMerge.containsKey(actualExpenseModal.getCategoryName())) {
            } else {
                pieMerge.put(actualExpenseModal.getCategoryName(), actualExpenseModal.getAmount());
                mergeColor.add(actualExpenseModal.getCategoryColor());
            }
        }
        return mergeColor;
    }

    public static HashMap<String, String> mergeCategoriesPlannedExpense(List<PlannedExpenseModal> plannedExpenseModals) {
        HashMap<String, String> pieMerge = new HashMap<>();
        for (PlannedExpenseModal plannedExpenseModal : plannedExpenseModals) {
            if (pieMerge.containsKey(plannedExpenseModal.getCategoryName())) {
                double amount = plannedExpenseModal.getAmount();
                double amountValue = Double.parseDouble(pieMerge.get(plannedExpenseModal.getCategoryName()));
                double newAmount = amount + amountValue;
                pieMerge.replace(plannedExpenseModal.getCategoryName(), newAmount + "");
            } else {
                pieMerge.put(plannedExpenseModal.getCategoryName(), plannedExpenseModal.getAmount() + "");
            }
        }
        return pieMerge;
    }

    public static List<Integer> mergeColorPlannedExpense(List<PlannedExpenseModal> plannedExpenseModals) {
        List mergeColor = new ArrayList();
        HashMap<String, Double> pieMerge = new HashMap<>();
        for (PlannedExpenseModal plannedExpenseModal : plannedExpenseModals) {
            if (pieMerge.containsKey(plannedExpenseModal.getCategoryName())) {
            } else {
                pieMerge.put(plannedExpenseModal.getCategoryName(), plannedExpenseModal.getAmount());
                mergeColor.add(plannedExpenseModal.getCategoryColor());
            }
        }
        return mergeColor;
    }

    public static HashMap<String, String> mergeCategoriesActualIncome(List<ActualIncomeModal> actualIncomeModals) {
        HashMap<String, String> pieMerge = new HashMap<>();
        for (ActualIncomeModal actualIncomeModal : actualIncomeModals) {
            if (pieMerge.containsKey(actualIncomeModal.getCategoryName())) {
                double amount = actualIncomeModal.getAmount();
                double amountValue = Double.parseDouble(pieMerge.get(actualIncomeModal.getCategoryName()));
                double newAmount = amount + amountValue;
                pieMerge.replace(actualIncomeModal.getCategoryName(), newAmount + "");
            } else {
                pieMerge.put(actualIncomeModal.getCategoryName(), actualIncomeModal.getAmount() + "");
            }
        }
        return pieMerge;
    }

    public static List<Integer> mergeColorActualIncome(List<ActualIncomeModal> actualIncomeModals) {
        List mergeColor = new ArrayList();
        HashMap<String, Double> pieMerge = new HashMap<>();
        for (ActualIncomeModal actualIncomeModal : actualIncomeModals) {
            if (pieMerge.containsKey(actualIncomeModal.getCategoryName())) {
            } else {
                pieMerge.put(actualIncomeModal.getCategoryName(), actualIncomeModal.getAmount());
                mergeColor.add(actualIncomeModal.getCategoryColor());
            }
        }
        return mergeColor;
    }

    public static HashMap<String, String> mergeCategoriesPlannedIncome(List<PlannedIncomeModal> plannedIncomeModals) {
        HashMap<String, String> pieMerge = new HashMap<>();
        for (PlannedIncomeModal plannedIncomeModal : plannedIncomeModals) {
            if (pieMerge.containsKey(plannedIncomeModal.getCategoryName())) {
                double amount = plannedIncomeModal.getAmount();
                double amountValue = Double.parseDouble(pieMerge.get(plannedIncomeModal.getCategoryName()));
                double newAmount = amount + amountValue;
                pieMerge.replace(plannedIncomeModal.getCategoryName(), newAmount + "");
            } else {
                pieMerge.put(plannedIncomeModal.getCategoryName(), plannedIncomeModal.getAmount() + "");
            }
        }
        return pieMerge;
    }

    public static List<Integer> mergeColorPlannedIncome(List<PlannedIncomeModal> plannedIncomeModals) {
        List mergeColor = new ArrayList();
        HashMap<String, Double> pieMerge = new HashMap<>();
        for (PlannedIncomeModal plannedIncomeModal : plannedIncomeModals) {
            if (pieMerge.containsKey(plannedIncomeModal.getCategoryName())) {
            } else {
                pieMerge.put(plannedIncomeModal.getCategoryName(), plannedIncomeModal.getAmount());
                mergeColor.add(plannedIncomeModal.getCategoryColor());
            }
        }
        return mergeColor;
    }

    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime().getMonth() + 1;
    }
    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime().getYear() + 1900;
    }

}
