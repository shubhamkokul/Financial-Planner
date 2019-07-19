package planner.db.modal;

public class PlanTypeModal {
    private long id;

    public PlanTypeModal(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
