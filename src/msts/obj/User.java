package msts.obj;

import msts.JDBCManager;
import msts.KeyAccess;
import msts.Transaction;

import java.security.PrivateKey;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    public ArrayList<Transaction> getTransactions(){
        try {
            String query = "SELECT * FROM transactions WHERE receiver = '" + this.getUserId() + "';";
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
                        "WHERE t.batch_number = " + transaction.getBatchNumber() + " AND u.role = 1";
            }else if(role == 2){
                //get distributor
                query = "SELECT * FROM transactions t " +
                        "JOIN users u ON t.sender = u.user_id " +
                        "WHERE t.batch_number = " + transaction.getBatchNumber() + " AND t.sub_batch_number = "+ transaction.getSubBatchNumber() + " AND u.role = 2";
            }else{
                //get healthcare organisation
                query = "SELECT * FROM users WHERE user_id = '" + transaction.getSender() + "';";
            }

            ResultSet rs = JDBCManager.executeQuery(query);

            if (rs.next()) {
                return new User(
                        rs.getString("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("role"),
                        rs.getString("email"),
                        rs.getLong("contact_number")
                );
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}