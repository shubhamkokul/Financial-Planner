package planner.db.modal;

public class CategoryItemModal {
    private int id;
    private String name;
    private int type;
    private String typeName;

    public CategoryItemModal(String name, int type, String typeName) {
        //id logic
        this.name = name;
        this.type = type;
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
