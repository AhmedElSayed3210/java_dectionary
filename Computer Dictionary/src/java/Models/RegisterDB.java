/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.sun.faces.context.FacesContextImpl;
import interfacepackag.user;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed ElSayed
 */
public class RegisterDB implements user {

    ConnectDB con = new ConnectDB();
PreparedStatement ps=null;
    ResultSet set = null;
    public boolean add_user(String name, String email, String password) {

        boolean flag = false;
        try {
            String sql = "insert into register (name , email, password) values (? ,?,?)";
            ps = con.conn().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            int x = ps.executeUpdate();

            if (x == 0) {
                flag = false;
            } else {
                flag = true;
                HttpSession ses = (HttpSession) FacesContextImpl.getCurrentInstance().getExternalContext().getSession(true);
                ses.setAttribute("login", "true");
                ses.setAttribute("email", email);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return flag;
    }

    public boolean usersearch(String email, String pass) {

        boolean flag = false;
        try {
             ps = con.conn().prepareStatement("select * from register where email=? and password=?");

            ps.setString(1, email);
            ps.setString(2, pass);

             set = ps.executeQuery();
            if (set.next()) {
                flag = true;
                HttpSession ses = (HttpSession) FacesContextImpl.getCurrentInstance().getExternalContext().getSession(true);
                ses.setMaxInactiveInterval(9000000);
                ses.setAttribute("email", email);
                ses.setAttribute("pass", pass);
                ses.setAttribute("user_id", set.getInt("id"));
                ses.setAttribute("login", "true");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }
       public int change_userpassword(String pass) {
        int x = 0;
        try {

            String sql = "update register set password=?  where id =?";
             ps = con.conn().prepareStatement(sql);

            ps.setString(1, pass);
            HttpSession ses = (HttpSession) FacesContextImpl.getCurrentInstance().getExternalContext().getSession(false);
            ps.setInt(2, (int) ses.getAttribute("user_id"));
            x = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;

    }
  public  int termcountuser(int id) {
      int count=0;
        try {
           Statement st=ConnectDB.conn().createStatement();
ResultSet rs3 = st.executeQuery("SELECT * FROM term where register_id='"+id+"'");
    while(rs3.next()){
    count ++;
    }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;

    }
}
