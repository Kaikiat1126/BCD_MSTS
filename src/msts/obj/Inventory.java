package msts.obj;

public class Inventory {
    private int userId;
    private int medicineId;
    private int quantity;

    public Inventory() {}

    public Inventory(int userId, int medicineId, int quantity) {
        this.userId = userId;
        this.medicineId = medicineId;
        this.quantity = quantity;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
