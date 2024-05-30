package connectSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

public class connect {

    public static Connection getConnection() {
        Connection c = null;
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://ANDREW\\SQLEXPRESS:1433;databaseName=QuanLyThongTinBenhAn;trustServerCertificate=true";
            String username = "sa";
            String password = "123456";
            c = DriverManager.getConnection(connectionUrl, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null && !c.isClosed()) {
                c.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printInfo(Connection c) {
        try {
            if (c != null) {
                DatabaseMetaData metadt = c.getMetaData();
                System.out.println("Database Product Name: "
                        + metadt.getDatabaseProductName());
                System.out.println("Database Product Version: "
                        + metadt.getDatabaseProductVersion());
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
