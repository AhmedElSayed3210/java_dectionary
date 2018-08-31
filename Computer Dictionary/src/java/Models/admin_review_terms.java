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
public class admin_review_terms {

    ConnectDB con = new ConnectDB();
    PreparedStatement ps = null;
    ResultSet set = null;

    public List<allTerms> selectallTerms() {
        List<allTerms> list = new ArrayList<allTerms>();
        try {
            String sql = "select * from term where approved=0";
            ps = con.conn().prepareStatement(sql);

            set = ps.executeQuery();
            while (set.next()) {
                allTerms c = new allTerms();
                c.setId(set.getInt(1));
                c.setTermName(set.getString(2));
                c.setInfo(set.getString(3));
                c.setWritenBy(set.getString(4));
                c.setApproved(set.getString(5));
                c.setApprovedBy(set.getString(6));
                c.setApprovedDate(set.getString(7));

                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("111111111111111111111111111111111111111");
            System.out.println(ex.getMessage());
        } 
        return list;

    }
    HttpSession ses = (HttpSession) FacesContextImpl.getCurrentInstance().getExternalContext().getSession(true);

    public int approvedTerm(int id) {
        int x = 0;
        try {

            String sql = "update term set approved=? , approved_by=? ,approved_date=? ,admin_id=? where id =?";
            ps = con.conn().prepareStatement(sql);

            ps.setInt(1, 1);
            ps.setString(2, (String) ses.getAttribute("email"));
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            ps.setDate(3, date);
            ps.setInt(4, (int) ses.getAttribute("admin_id"));
            ps.setInt(5, id);
            x = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;

    }
}
