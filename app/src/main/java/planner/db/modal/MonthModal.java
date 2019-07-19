package planner.db.modal;

public class MonthModal {
    private int id;
    private int actualPosition;
    private int name;

    public MonthModal(int actualPosition, int name) {
        this.actualPosition = actualPosition;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(int actualPosition) {
        this.actualPosition = actualPosition;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }


}
