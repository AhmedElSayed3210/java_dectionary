/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.Register;
import com.sun.faces.context.FacesContextImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed ElSayed
 */
public class admin_register {

    ConnectDB con = new ConnectDB();
PreparedStatement ps=null;
    ResultSet set = null;
    public boolean usersearch(String email, String pass) {

        boolean flag = false;
        try {
             ps = con.conn().prepareStatement("select * from admin where email=? and password=?");
            ps.setString(1, email);
            ps.setString(2, pass);

             set = ps.executeQuery();
            if (set.next()) {
                flag = true;
                HttpSession ses = (HttpSession) FacesContextImpl.getCurrentInstance().getExternalContext().getSession(true);
                ses.setAttribute("email", email);
                ses.setAttribute("admin_id", set.getInt("id"));
                ses.setAttribute("admin_password", set.getString("password"));
                ses.setAttribute("login", true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    HttpSession ses = (HttpSession) FacesContextImpl.getCurrentInstance().getExternalContext().getSession(false);

    public int change_username(String name) {
        int x = 0;
        try {

            String sql = "update admin set name=?  where id =?";
             ps = con.conn().prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, (int) ses.getAttribute("admin_id"));
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
            x = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;

    }

    public int change_userpassword(String pass) {
        int x = 0;
        try {

            String sql = "update admin set password=?  where id =?";
             ps = con.conn().prepareStatement(sql);

            ps.setString(1, pass);
            HttpSession ses = (HttpSession) FacesContextImpl.getCurrentInstance().getExternalContext().getSession(false);
            ps.setInt(2, (int) ses.getAttribute("admin_id"));
            x = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;

    }

}
