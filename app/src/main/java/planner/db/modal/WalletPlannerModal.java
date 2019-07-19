package planner.db.modal;

public class WalletPlannerModal {
    private int id;

    public WalletPlannerModal(String name, int icon, String iconName, double amount) {
        //id logic
        this.name = name;
        this.icon = icon;
        this.iconName = iconName;
        this.amount = amount;
    }

    private String name;
    private int icon;
    private String iconName;
    private double amount;

    public int getId() {
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
