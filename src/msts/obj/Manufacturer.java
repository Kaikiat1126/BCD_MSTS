package msts.obj;

import msts.Hasher;
import msts.JDBCManager;
import msts.StatusContainer;
import msts.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Manufacturer extends User{

    public Manufacturer() {
        super();
    }

    public Manufacturer(String userId) {
        super(userId);
    }

    public Manufacturer(String username, String password, int role, String email, Long contactNumber) {
        super(username, password, role, email, contactNumber);
    }

    public Manufacturer(String userId, String username, String password, int role, String email, Long contactNumber) {
        super(userId, username, password, role, email, contactNumber);
    }

    public ArrayList<Medicine> getAllMedicine() {
        try {
            ResultSet rs = JDBCManager.executeQuery("SELECT * FROM medicine WHERE manufacturer_id = '" + this.getUserId() + "';");
            ArrayList<Medicine> medicines = new ArrayList<>();
            while (rs.next()) {
                Medicine medicine = new Medicine();
                medicine.setMedicineID(rs.getInt("medicine_id"));
                medicine.setName(rs.getString("name"));
                medicine.setType(rs.getString("type"));
                medicine.setManufacturerID(rs.getString("manufacturer_id"));
                medicine.setPrice(rs.getDouble("price"));
                medicines.add(medicine);
            }
            return medicines;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNewBatch(int medicineID, int quantity, String additionalInfo) {
        try {
            LocalDate productionDate = LocalDate.now();
            LocalDate expiryDate = productionDate.plusYears(1);
            String batch = Hasher.generateUUID(
                    medicineID + quantity + getUserId() + getEmail() +
                            productionDate + expiryDate + additionalInfo
            );
            Transaction transaction = new Transaction(
                    LocalDate.now(), getUserId(), "",
                    medicineID, quantity, batch, "0",
                    productionDate, expiryDate, additionalInfo
            );
            createNewTransaction(transaction, false, false, "");
            System.out.println("\nNew Medicine Batch created successfully!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addNewMedicine(String name, String type, double price) {
        try {
            Medicine newMedicine = new Medicine();
            newMedicine.setManufacturerID(StatusContainer.currentUser.getUserId());
            newMedicine.setName(name);
            newMedicine.setType(type);
            newMedicine.setPrice(price);

            String query = "INSERT INTO bcd.medicine" + "(" +
                    "`name`,`type`,`manufacturer_id`,`price`) VALUES ('"  +
                    newMedicine.getName() + "', '" +
                    newMedicine.getType() + "', '" +
                    newMedicine.getManufacturerID() + "', '" +
                    newMedicine.getPrice()+ "');";
            JDBCManager.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
