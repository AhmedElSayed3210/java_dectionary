/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.sun.faces.context.FacesContextImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed ElSayed
 */
public class ContactDB {

    ConnectDB con = new ConnectDB();
    PreparedStatement ps=null;
    ResultSet set = null;
    HttpSession ses = (HttpSession) FacesContextImpl.getCurrentInstance().getExternalContext().getSession(false);

    public boolean addcontact(String name, String email, String text) {

        boolean flag = false;
        try {
            String sql = "insert into contact (name , email, message,register_id) values (? ,?,?,?)";
           ps = con.conn().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, text);

            ps.setInt(4, (int) ses.getAttribute("user_id"));
            System.out.println("xxxxxxxxxxxxxxx " + ses.getAttribute("user_id"));
            int x = ps.executeUpdate();

            if (x != 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return flag;
    }

    public List<allContact> getcontant() {

        List<allContact> list = new ArrayList<allContact>();
        try {
            String sql = "select * from contact";
            PreparedStatement ps = con.conn().prepareStatement(sql);

            set = ps.executeQuery();
            while (set.next()) {
                allContact c = new allContact();
                c.setId(set.getInt(1));
                c.setName(set.getString(2));
                c.setEmail(set.getString(3));
                c.setMessage(set.getString(4));
                c.setRegister_id(set.getInt(5));

                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;

    }

    public int deletecontact(int id) {
        int x = 0;
        try {
            String sql = "delete  from contact where id =?";
            ps = con.conn().prepareStatement(sql);
            ps.setInt(1, id);
            x = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
       
        }
        return x;

    }

}
