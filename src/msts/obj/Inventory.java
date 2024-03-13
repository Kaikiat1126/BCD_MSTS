package msts.obj;

public class Inventory {
    private int userId;
    private int medicineId;
    private int quantity;
    private int batchNumber;

    public Inventory() {}

    public Inventory(int userId, int medicineId, int quantity, int batchNumber) {
        this.userId = userId;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.batchNumber = batchNumber;
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

    public int getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }
}
