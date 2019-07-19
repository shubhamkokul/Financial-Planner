package planner.db.modal;

public class CategoryItemModal {
    private long id;
    private String name;
    private int type;
    private String typeName;

    public CategoryItemModal(long id, String name, int type, String typeName) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.typeName = typeName;
    }

    public long getId() {
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
