package planner.db.modal;

public class PlanTypeModal {
    private int id;

    public PlanTypeModal(String name) {
        //id Logic
        this.name = name;
    }

    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
