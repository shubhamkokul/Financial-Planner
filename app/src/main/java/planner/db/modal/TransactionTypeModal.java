package planner.db.modal;

public class TransactionTypeModal {
    private int id;
    private int type;
    private String typeName;

    public TransactionTypeModal(int type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public int getId() {
        return id;
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
