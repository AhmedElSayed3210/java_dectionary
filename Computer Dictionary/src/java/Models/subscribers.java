/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ahmed ElSayed
 */
public class subscribers {

    ConnectDB con = new ConnectDB();
    PreparedStatement ps = null;
    ResultSet set = null;

    public List<allsubscribers> selectallRegister() {
        List<allsubscribers> list = new ArrayList<allsubscribers>();
        try {
            String sql = "select * from register";
            ps = con.conn().prepareStatement(sql);

            set = ps.executeQuery();
            while (set.next()) {
                System.out.println(set.getString(1) + "  mmmmmmmmmmmmmmmmmmmmmmm");
                allsubscribers c = new allsubscribers();
                c.setId(set.getInt(1));
                System.out.println("cccccccccccccccccccccc   +   " + set.getInt(1));
                c.setName(set.getString(2));
                c.setEmail(set.getString(3));

                System.out.println("ok");
                list.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        return list;

    }

}
