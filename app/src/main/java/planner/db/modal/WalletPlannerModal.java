package planner.db.modal;

public class WalletPlannerModal {
    private long id;
    private String name;
    private int icon;
    private String iconName;
    private double amount;

    public WalletPlannerModal(long id, String name, int icon, String iconName, double amount) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.iconName = iconName;
        this.amount = amount;
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
