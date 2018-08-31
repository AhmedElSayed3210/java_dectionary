/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ahmed ElSayed
 */
public class ConnectDB {

    public static Connection conn() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer_dectionary", "root", "root");
            System.out.println("Don");
        } catch (ClassNotFoundException ex) {
            System.out.println("conn error");

        } catch (SQLException ex) {

            System.out.println("url error");
        }
        return con;
    }
    

}
