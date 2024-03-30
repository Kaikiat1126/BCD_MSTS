package msts.obj;

import msts.*;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Patient extends User{

    public Patient() {
        super();
    }

    public Patient(String userId) {
        super(userId);
    }

    public Patient(String username, String password, int role, String email, Long contactNumber) {
        super(username, password, role, email, contactNumber);
    }

    public Patient(String userId, String username, String password, int role, String email, Long contactNumber) {
        super(userId, username, password, role, email, contactNumber);
    }

    public ArrayList<ArrayList<String>> getMedicineBatchFromHealthcareOrganisation() {
        String query = "SELECT t.receiver, t.medicine_id, t.batch_number, t.sub_batch_number, t.production_date, t.expiry_date, m.*, u.username, i.quantity " +
                "FROM transactions t JOIN medicine m on t.medicine_id = m.medicine_id JOIN users u on t.receiver = u.user_id " +
                "JOIN inventory i on i.batch_number = t.sub_batch_number AND i.user_id = t.receiver WHERE u.role = 3 AND i.quantity > 0;";
        ArrayList<ArrayList<String>> medicineBatch = new ArrayList<>();
        try {
            ResultSet rs = JDBCManager.executeQuery(query);
            ArrayList<String> result;
            while (rs.next()) {
                result = new ArrayList<>();
                result.add(rs.getString("receiver"));
                result.add(rs.getString("username"));
                result.add(rs.getString("medicine_id"));
                result.add(rs.getString("batch_number"));
                result.add(rs.getString("production_date"));
                result.add(rs.getString("expiry_date"));
                result.add(rs.getString("name"));
                result.add(rs.getString("type"));
                result.add(rs.getString("price"));
                result.add(rs.getString("quantity"));
                result.add(rs.getString("sub_batch_number"));
                medicineBatch.add(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medicineBatch;
    }

    public void createNewTransaction(ArrayList<String> medicineBatch) {
        try {
            Transaction transaction = new Transaction(
                    LocalDate.now(), medicineBatch.get(0), getUserId(), Integer.parseInt(medicineBatch.get(2)),
                    Integer.parseInt(medicineBatch.get(11)), medicineBatch.get(3), medicineBatch.get(10),
                    LocalDate.parse(medicineBatch.get(4)), LocalDate.parse(medicineBatch.get(5)), medicineBatch.get(12)
            );
            int updatedInventoryQuantity = Integer.parseInt(medicineBatch.get(9)) - Integer.parseInt(medicineBatch.get(11));
            String updateQuery = "UPDATE inventory SET quantity = " + updatedInventoryQuantity + " WHERE user_id = '" + transaction.getSender() + "' AND batch_number = '" + medicineBatch.get(10) + "';";
            createNewTransaction(transaction, true, true, updateQuery);

            System.out.println("\nNew Medicine Purchased successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
