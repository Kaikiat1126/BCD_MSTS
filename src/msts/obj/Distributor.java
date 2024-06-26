package msts.obj;

import msts.JDBCManager;
import msts.KeyAccess;
import msts.StatusContainer;
import msts.Transaction;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Distributor extends User{

    public Distributor() {
        super();
    }

    public Distributor(String userId) {
        super(userId);
    }

    public Distributor(String username, String password, int role, String email, Long contactNumber) {
        super(username, password, role, email, contactNumber);
    }

    public Distributor(String userId, String username, String password, int role, String email, Long contactNumber) {
        super(userId, username, password, role, email, contactNumber);
    }

    public ArrayList<ArrayList<String>> getMedicineBatchFromManufacturer() {
        String query = "SELECT t.sender, t.medicine_id, t.batch_number, t.production_date, t.expiry_date, m.*, u.username, i.quantity " +
                "FROM transactions t JOIN medicine m on t.medicine_id = m.medicine_id JOIN users u on t.sender = u.user_id " +
                "JOIN inventory i on i.batch_number = t.batch_number AND i.user_id = t.sender WHERE u.role = 1 AND i.quantity > 0;";
        ArrayList<ArrayList<String>> medicineBatch = new ArrayList<>();
        try {
            ResultSet rs = JDBCManager.executeQuery(query);
            ArrayList<String> result;
            while (rs.next()) {
                result = new ArrayList<>();
                result.add(rs.getString("sender"));
                result.add(rs.getString("username"));
                result.add(rs.getString("medicine_id"));
                result.add(rs.getString("batch_number"));
                result.add(rs.getString("production_date"));
                result.add(rs.getString("expiry_date"));
                result.add(rs.getString("name"));
                result.add(rs.getString("type"));
                result.add(rs.getString("price"));
                result.add(rs.getString("quantity"));
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
                    Integer.parseInt(medicineBatch.get(9)), medicineBatch.get(3), "0",
                    LocalDate.parse(medicineBatch.get(4)), LocalDate.parse(medicineBatch.get(5)), medicineBatch.get(10)
            );
            String updateQuery = "UPDATE inventory SET quantity = 0 WHERE user_id = '" + medicineBatch.get(0) + "' AND batch_number = '" + medicineBatch.get(3) + "';";
            createNewTransaction(transaction, false, true, updateQuery);
            System.out.println("\nNew Medicine Order placed successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
