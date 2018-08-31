/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ahmed ElSayed
 */
public class ReadMore {

    ConnectDB con = new ConnectDB();
PreparedStatement ps=null;
    ResultSet set = null;
    public ResultSet readMore(int id) {

        ResultSet set = null;
        try {
             ps = con.conn().prepareStatement("select * from term where id=?");
            ps.setInt(1, id);

            set = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("problem  zaaaaaaaaaaaaaaaaaa");
            ex.printStackTrace();
        
        }
        return set;
    }

}
