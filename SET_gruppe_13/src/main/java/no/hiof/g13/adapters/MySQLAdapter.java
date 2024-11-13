package no.hiof.g13.adapters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLAdapter {

    public static Connection getConnection() throws SQLException,ClassNotFoundException {

        String driverClassName = "com.mysql.cj.jdbc.Driver";
        // load a driver
        Class.forName(driverClassName );
        String url ="jdbc:mysql://gruppe-13.mysql.database.azure.com:3306/gruppe13?useSSL=false";
        String user = "superuser";
        String password ="Gruppe_13";
        //establish the connection
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

}