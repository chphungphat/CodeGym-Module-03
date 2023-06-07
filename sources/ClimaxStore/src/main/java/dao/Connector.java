package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static final Connector connector = new Connector();

    private Connector() {}

    public static Connector getInstance() {
        return connector;
    }

    private final String jdbcURL = "jdbc:mysql://localhost:3306/CLIMAX_STORE?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "Qwert!2345";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return connection;
    }
}
