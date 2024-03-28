package msts.obj;

import msts.Hasher;
import msts.JDBCManager;
import msts.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

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
            transaction.signTransaction(getPrivateKey());

            String query = "INSERT INTO transactions (" +
                    "transaction_date, sender, receiver, medicine_id, quantity, batch_number, sub_batch_number, production_date, expiry_date, additional_info, digital_signature" +
                    ") VALUES ('" +
                    transaction.getTransactionDate() + "', '" + transaction.getSender() + "', '" + transaction.getReceiver() + "', " +
                    transaction.getMedicineId() + ", " + transaction.getQuantity() + ", '" + transaction.getBatchNumber() + "', '" + transaction.getSubBatchNumber() + "', '" +
                    transaction.getProductionDate() + "', '" + transaction.getExpiryDate() + "', '" + transaction.getAdditionalInfo() + "', '" + transaction.getDigitalSignature() + "');";
            System.out.println(query);
//            JDBCManager.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
