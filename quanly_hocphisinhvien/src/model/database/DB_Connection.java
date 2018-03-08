package model.database;

import java.sql.*;


public class DB_Connection {
    private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=QL_HOCPHI_SV;";
//            + "integratedSecurity=true";
    /**
     * Thay đổi giống tên user trong sql server
     */
    private static String USER_NAME = "sa";
    /**
     * Thay đổi giống tên password trong sql server
     */
    private static String PASSWORD = "hoangbui";
    private static Connection conn = null;
    private static Object lock = new Object();

    private DB_Connection () {
    }

    /**
     * Khởi tạo lấy connection từ database
     * @return Connection giữa database và java
     */
    public synchronized static Connection getConnection() {

        try {
            synchronized (lock) {
                if (conn == null) {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
                    System.out.println("connect successfully!");
                }

                return conn;
            }
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
            return null;
        }
    }
}
