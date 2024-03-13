package msts;

import msts.obj.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {

    public static ResultSet authenticate(String username, String password) throws SQLException {
        password = Hasher.md5(password);
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "';";
        return JDBCManager.executeQuery(query);
    }

    public static User assignUserLogin(String[] credentials) {
        try {
            ResultSet rs = authenticate(credentials[0], credentials[1]);
            if (!rs.next()) return null;

            int id = rs.getInt("user_id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            int role = rs.getInt("role");
            String email = rs.getString("email");
            long contactNumber = rs.getLong("contact_number");

            if (role == 1) {
                return new Manufacturer(id, username, password, role, email, contactNumber);
            } else if (role == 2) {
                return new Distributor(id, username, password, role, email, contactNumber);
            } else if (role == 3) {
                return new HealthcareOrganisation(id, username, password, role, email, contactNumber);
            } else {
                return new Patient(id, username, password, role, email, contactNumber);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
