package msts;

import java.sql.SQLException;

public class Registration {

    public static int register(String username, String password, int role, String email, Long contactNumber) throws SQLException {
        password = Hasher.md5(password);
        String query = "INSERT INTO users (username, password, role, email, contact_number) VALUES ('" + username + "', '" + password + "', " + role + ", '" + email + "', " + contactNumber + ")";
        KeyPairMaker.generateKeyPair(username);
        return JDBCManager.executeUpdate(query);
    }
}
