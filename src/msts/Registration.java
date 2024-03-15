package msts;

import java.sql.SQLException;

public class Registration {

    public static int register(String username, String password, int role, String email, Long contactNumber) throws SQLException {
        password = Hasher.md5(password);
        String uuid = Hasher.generateUUID(username);
        String query = "INSERT INTO users (user_id, username, password, role, email, contact_number) VALUES ('" + uuid + "', '" + username + "', '" + password + "', " + role + ", '" + email + "', " + contactNumber + ")";
        KeyPairMaker.generateKeyPair(uuid);
        return JDBCManager.executeUpdate(query);
    }
}
