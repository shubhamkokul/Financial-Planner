package planner.db.modal;

public class YearModal {
    private int id;
    private int year;

    public YearModal(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


}
