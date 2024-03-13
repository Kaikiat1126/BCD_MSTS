package msts;

import java.sql.*;

public class JDBCManager {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/bcd";
    private static final String user = "root";
    private static final String password = "Password";

    private static Connection conn = null;

    private static void connect() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(url, user, password);
        }
    }

    private static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to execute SQL queries that don't return a result set (e.g., INSERT, UPDATE, DELETE)
    public static int executeUpdate(String query) throws SQLException {
        connect();

        try (Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(query);
        } finally {
            closeConnection();
        }
    }

    // Method to execute SQL queries that return a result set (e.g., SELECT)
    public static ResultSet executeQuery(String query) throws SQLException {
        connect();

        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            throw e;
        }
    }
}
