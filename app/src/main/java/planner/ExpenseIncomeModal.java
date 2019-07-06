package planner;

public class ExpenseIncomeModal {

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getFullMonthDate() {
        return fullMonthDate;
    }

    public String getMonthStatement() {
        return monthStatement;
    }

    private String year, month, fullMonthDate, monthStatement;

    public ExpenseIncomeModal(String year, String month, String fullMonthDate, String monthStatement) {
        this.year = year;
        this.month = month;
        this.fullMonthDate = fullMonthDate;
        this.monthStatement = monthStatement;
    }
}
