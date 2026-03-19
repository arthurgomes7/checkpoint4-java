package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static Connection connection;

    public ConexaoDB() {
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                String user = "rm560771";
                String password = "fiap@2025";

                connection = DriverManager.getConnection(url, user, password);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
