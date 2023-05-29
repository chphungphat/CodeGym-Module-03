package DAO;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final ConnectionDB connectionDB = new ConnectionDB();

    private ConnectionDB() {}

    public static ConnectionDB getInstance() {
        return connectionDB;
    }

    private final String URL = "jdbc:mysql://localhost:3306/SHOP_MANAGEMENT";
    private final String UserName = "root";
    private final String Password = "Qwert!2345";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, UserName, Password);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return connection;
    }

    public void printSQLException(SQLException exception) {
        for (Throwable e : exception) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQL State: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = exception.getCause();
                while (t != null) {
                    System.out.println("Cause" + t);
                    t = t.getCause();
                }
            }
        }
    }
}
