package planner.db.modal;

public class ExpensePlannerModal {
    private int planType;
    private String planTypeName;
    private String description;
    private int monthId;
    private String monthName;
    private int yearId;
    private int yearName;
    private int id; //Timestamp

    public ExpensePlannerModal(int planType, String planTypeName, String description, int monthId, String monthName, int yearId, int yearName) {
        /*this.id = //timestamp logic*/
        this.planType = planType;
        this.planTypeName = planTypeName;
        this.description = description;
        this.monthId = monthId;
        this.monthName = monthName;
        this.yearId = yearId;
        this.yearName = yearName;
    }

    public int getPlanType() {
        return planType;
    }

    public void setPlanType(int planType) {
        this.planType = planType;
    }

    public String getPlanTypeName() {
        return planTypeName;
    }

    public void setPlanTypeName(String planTypeName) {
        this.planTypeName = planTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMonthId() {
        return monthId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public int getYearId() {
        return yearId;
    }

    public void setYearId(int yearId) {
        this.yearId = yearId;
    }

    public int getYearName() {
        return yearName;
    }

    public void setYearName(int yearName) {
        this.yearName = yearName;
    }

    public int getId() {
        return id;
    }

}
