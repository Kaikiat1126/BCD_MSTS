package msts.obj;

import msts.JDBCManager;
import msts.KeyAccess;
import msts.StatusContainer;
import msts.Transaction;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {
    protected String userId;
    protected String username;
    protected String password;
    protected int role;
    protected String email;
    protected Long contactNumber;

    public User() {}

    public User(String userId) {
        this.userId = userId;
    }

    public User(String username, String password, int role, String email, Long contactNumber) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    public User(String userId, String username, String password, int role, String email, Long contactNumber) {
        this(username, password, role, email, contactNumber);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public Long getContactNumber() {
        return contactNumber;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public PrivateKey getPrivateKey() throws Exception {
        return KeyAccess.getPrivateKey(userId);
    }

    public PublicKey getPublicKey() throws Exception {
        return KeyAccess.getPublicKey(userId);
    }

    public void createNewTransaction(Transaction transaction, boolean useSubBatch, boolean needUpdate, String updateQuery) {
        try {
            transaction.signTransaction(KeyAccess.getPrivateKey(transaction.getSender()));

            String txQuery = "INSERT INTO transactions (" +
                    "transaction_date, sender, receiver, medicine_id, quantity, batch_number, sub_batch_number, production_date, expiry_date, additional_info, digital_signature" +
                    ") VALUES ('" +
                    transaction.getTransactionDate() + "', '" + transaction.getSender() + "', '" + transaction.getReceiver() + "', " +
                    transaction.getMedicineId() + ", " + transaction.getQuantity() + ", '" + transaction.getBatchNumber() + "', '" + transaction.getSubBatchNumber() + "', '" +
                    transaction.getProductionDate() + "', '" + transaction.getExpiryDate() + "', '" + transaction.getAdditionalInfo() + "', '" + transaction.getDigitalSignature() + "');";
            String batchNumber = useSubBatch ? transaction.getSubBatchNumber() : transaction.getBatchNumber();
            String insertQuery = "INSERT INTO inventory (medicine_id, user_id, quantity, batch_number) VALUES (" + transaction.getMedicineId() + ", '" + getUserId() + "', " + transaction.getQuantity() + ", '" + batchNumber + "');";

            String[] queries = needUpdate ? new String[]{txQuery, insertQuery, updateQuery} : new String[]{txQuery, insertQuery};
            for (String query : queries) {
                JDBCManager.executeUpdate(query);
            }
            StatusContainer.blockChain.addNewTransaction(transaction);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Transaction> getTransactions(){
        try {
            String query = "SELECT * FROM transactions WHERE receiver = '" + this.getUserId() + "';";
            if(this.getRole() == 1){
                query = "SELECT * FROM transactions WHERE sender = '" + this.getUserId() + "' AND receiver = '';";
            }
            ResultSet rs = JDBCManager.executeQuery(query);
            ArrayList<Transaction> transactions = new ArrayList<>();
            while (rs.next()) {
                Transaction transaction = new Transaction(
                        rs.getDate("transaction_date").toLocalDate(),
                        rs.getString("sender"),
                        rs.getString("receiver"),
                        rs.getInt("medicine_id"),
                        rs.getInt("quantity"),
                        rs.getString("batch_number"),
                        rs.getString("sub_batch_number"),
                        rs.getDate("production_date").toLocalDate(),
                        rs.getDate("expiry_date").toLocalDate(),
                        rs.getString("additional_info"),
                        rs.getString("digital_signature")
                );
                transactions.add(transaction);
            }
            return transactions;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User getOrigin(Transaction transaction, int role){
        try {
            String query = "";

            if(role == 1){
                //get manufacture
                query = "SELECT * FROM transactions t " +
                        "JOIN users u ON t.sender = u.user_id " +
                        "WHERE t.batch_number = '" + transaction.getBatchNumber() + "' AND u.role = 1";
            }else if(role == 2){
                //get distributor
                query = "SELECT * FROM transactions t " +
                        "JOIN users u ON t.sender = u.user_id " +
                        "WHERE t.batch_number = '" + transaction.getBatchNumber() + "' AND t.sub_batch_number = '"+ transaction.getSubBatchNumber() + "' AND u.role = 2";
            }else{
                //get healthcare organisation
                query = "SELECT * FROM users WHERE user_id = '" + transaction.getSender() + "';";
            }

            ResultSet rs = JDBCManager.executeQuery(query);

            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUserById(String userId){
        try {
            String query = "SELECT * FROM users WHERE user_id = '" + userId + "';";
            ResultSet rs = JDBCManager.executeQuery(query);
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static User extractUserFromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getString("user_id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getInt("role"),
                rs.getString("email"),
                rs.getLong("contact_number")
        );
    }
}