package planner.utility;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import planner.db.modal.ActualExpenseModal;
import planner.db.modal.PlannedExpenseModal;

public class IdentifierGenerator {

    public static long timeStampGenerator(){
        return new Timestamp(System.currentTimeMillis()).getTime() + new Random().nextInt();
    }
    public static HashMap<String, Double> mergeCategoriesActualExpense(List<ActualExpenseModal> actualExpenseModals) {
        HashMap<String, Double> pieMerge = new HashMap<>();
        for (ActualExpenseModal actualExpenseModal : actualExpenseModals) {
            if (pieMerge.containsKey(actualExpenseModal.getCategoryName())) {
                double amount = actualExpenseModal.getAmount();
                double amountValue = pieMerge.get(actualExpenseModal.getCategoryName());
                double newAmount = amount + amountValue;
                pieMerge.replace(actualExpenseModal.getCategoryName(), newAmount);
            } else {
                pieMerge.put(actualExpenseModal.getCategoryName(), actualExpenseModal.getAmount());
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

    public static HashMap<String, Double> mergeCategoriesPlannedExpense(List<PlannedExpenseModal> plannedExpenseModals) {
        HashMap<String, Double> pieMerge = new HashMap<>();
        for (PlannedExpenseModal plannedExpenseModal : plannedExpenseModals) {
            if (pieMerge.containsKey(plannedExpenseModal.getCategoryName())) {
                double amount = plannedExpenseModal.getAmount();
                double amountValue = pieMerge.get(plannedExpenseModal.getCategoryName());
                double newAmount = amount + amountValue;
                pieMerge.replace(plannedExpenseModal.getCategoryName(), newAmount);
            } else {
                pieMerge.put(plannedExpenseModal.getCategoryName(), plannedExpenseModal.getAmount());
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
}
