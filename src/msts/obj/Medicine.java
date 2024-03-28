package msts.obj;

public class Medicine {
    private int medicineID;
    private String name;
    private String type;
    private String manufacturerID;
    private double price;

    public Medicine() {}

    public int getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(int medicineID) {
        this.medicineID = medicineID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(String manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
