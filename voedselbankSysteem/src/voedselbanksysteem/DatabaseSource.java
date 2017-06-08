/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voedselbanksysteem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Calvin
 */
public class DatabaseSource {

    private static String dbserver;
    private static String database;
    private static String username;
    private static String password;

    private static Connection activeConn;

    private static void init() {
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        dbserver = "meru.hhs.nl";
        database = "16094190";
        username = "16094190";
        password = "Aabas7uwu7";

    }

    public static Connection getConnection() throws SQLException {
        if (activeConn == null) {
            init();
            activeConn = createConnection();
        } else {
            if (!activeConn.isValid(0)) {
                activeConn = createConnection();
            }
        }

        return activeConn;

    }

    private static Connection createConnection() throws SQLException {

        String connectionString = "jdbc:mysql://" + dbserver + "/" + database + "?"
                + "user=" + username + "&password=" + password;

        return DriverManager.getConnection(connectionString);
    }

    public static void closeConnection() {
        if (activeConn != null) {
            try {
                activeConn.close();
            } catch (SQLException e) {
                //to catch and do nothing is the best option
                //don't know how to recover from this exception

            } finally {
                activeConn = null;
            }

        }

    }
}
