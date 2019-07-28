package planner.db.modal;


public class IncomeComparisionModal {
    private String categoryName;
    private String actualValue;
    private String plannedValue;

    public IncomeComparisionModal(String categoryName, String actualValue, String plannedValue) {
        this.categoryName = categoryName;
        this.actualValue = actualValue;
        this.plannedValue = plannedValue;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getPlannedValue() {
        return plannedValue;
    }

    public void setPlannedValue(String plannedValue) {
        this.plannedValue = plannedValue;
    }


}
