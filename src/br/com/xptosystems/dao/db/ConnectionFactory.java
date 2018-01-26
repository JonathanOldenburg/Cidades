package br.com.xptosystems.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/xptosystems";
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    
    private static Connection connection;
    
    public static Connection getConnection() throws Exception {
        connection = connection == null || connection.isClosed() ? newConnection() : connection;
        return connection;
    }
    
    private static Connection newConnection() throws Exception {
        Class.forName(DRIVER);
        return DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
    }
}
