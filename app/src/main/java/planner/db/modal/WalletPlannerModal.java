package planner.db.modal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class WalletPlannerModal {
    private long id;
    private String name;


    private long iconID;
    private int icon;
    private String iconName;
    private double amount;

    public WalletPlannerModal(long id, String name, long iconID, int icon, String iconName, double amount) {
        this.id = id;
        this.name = name;
        this.iconID = iconID;
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

    public long getIconID() {
        return iconID;
    }

    public void setIconID(long iconID) {
        this.iconID = iconID;
    }

    public static void insertIntoTableInitial(SQLiteDatabase dbWriter, List<WalletPlannerModal> walletPlannerModals) {
        for (WalletPlannerModal walletPlannerModal : walletPlannerModals) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", walletPlannerModal.getId());
            contentValues.put("NAME", walletPlannerModal.getIcon());
            contentValues.put("ICONID", walletPlannerModal.getIconID());
            contentValues.put("ICON", walletPlannerModal.getIcon());
            contentValues.put("ICONNAME", walletPlannerModal.getIconName());
            contentValues.put("AMOUNT", walletPlannerModal.getAmount());
            dbWriter.insert("WALLETPLANTABLE", null, contentValues);
        }
        dbWriter.close();
    }
}
