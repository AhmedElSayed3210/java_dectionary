/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.sun.faces.context.FacesContextImpl;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed ElSayed
 */
public class admin_defination {

    ConnectDB con = new ConnectDB();
    PreparedStatement ps=null;
    HttpSession ses = (HttpSession) FacesContextImpl.getCurrentInstance().getExternalContext().getSession(false);

    public boolean add_term(String name, String info) {

        boolean flag = false;
        try {
            String sql = "insert into term (term_name ,term_info, approved, Writen_by,approved_by,approved_date,register_id,admin_id) values (?,? ,?,?,?,?,?,?)";
             ps = con.conn().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, info);
            ps.setBoolean(3, true);

            ps.setString(4, (String) ses.getAttribute("email"));
            ps.setString(5, (String) ses.getAttribute("email"));

            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            ps.setDate(6, date);

            ps.setInt(7, (int) ses.getAttribute("admin_id"));
            ps.setInt(8, (int) ses.getAttribute("admin_id"));

            System.out.println("hhhhhhhhhhhhhhhhhhh   " + ses.getAttribute("admin_id"));
            int x = ps.executeUpdate();

            if (x == 0) {
                flag = false;
            } else {
                flag = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("noooooo");
        }
        return flag;
    }
}
