package planner.db.modal;

public class IncomePlannerModal {
    private String description;
    private int monthId;
    private String monthName;
    private int yearId;
    private int yearName;
    private long id; //Timestamp

    public IncomePlannerModal(long id, String description, int monthId, String monthName, int yearId, int yearName) {
        this.id = id;
        this.description = description;
        this.monthId = monthId;
        this.monthName = monthName;
        this.yearId = yearId;
        this.yearName = yearName;
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

    public long getId() {
        return id;
    }
}
