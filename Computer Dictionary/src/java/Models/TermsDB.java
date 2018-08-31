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
public class TermsDB {

    ConnectDB con = new ConnectDB();
PreparedStatement ps=null;
    ResultSet set = null;
    public boolean add_term(String name, String info,String depart) {

        boolean flag = false;
        try {
            String sql = "insert into term (term_name ,term_info, approved, Writen_by,register_id,department_id) values (?,? ,?,?,?,?)";
             ps = con.conn().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, info);
            ps.setBoolean(3, false);
            HttpSession ses = (HttpSession) FacesContextImpl.getCurrentInstance().getExternalContext().getSession(false);

            ps.setString(4, (String) ses.getAttribute("email"));
            ps.setInt(5, (int) ses.getAttribute("user_id"));
            ps.setInt(6, Integer.parseInt(depart));

            int x = ps.executeUpdate();

            if (x == 0) {
                flag = false;
            } else {
                flag = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return flag;
    }

    public List<allTerms> selectallTerms() {
        List<allTerms> list = new ArrayList<allTerms>();
        try {
            String sql = "select * from term where approved=1";
            ps = con.conn().prepareStatement(sql);

            set = ps.executeQuery();
            while (set.next()) {
                System.out.println(set.getString(1) + "  mmmmmmmmmmmmmmmmmmmmmmm");
                allTerms c = new allTerms();
                c.setId(set.getInt(1));
                c.setTermName(set.getString(2));
                c.setInfo(set.getString(3));
                c.setWritenBy(set.getString(4));
                c.setApproved(set.getString(5));
                c.setApprovedBy(set.getString(6));
                c.setApprovedDate(set.getString(7));
                System.out.println("ok");
                list.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;

    }
    public List<allTerms> userTerms(int id) {
        List<allTerms> list = new ArrayList<allTerms>();
        try {
            String sql = "select * from term where approved=1 and register_id='"+id+"'";
            ps = con.conn().prepareStatement(sql);

            set = ps.executeQuery();
            while (set.next()) {
                System.out.println(set.getString(1) + "  mmmmmmmmmmmmmmmmmmmmmmm");
                allTerms c = new allTerms();
                c.setId(set.getInt(1));
                c.setTermName(set.getString(2));
                c.setInfo(set.getString(3));
                c.setWritenBy(set.getString(4));
                c.setApproved(set.getString(5));
                c.setApprovedBy(set.getString(6));
                c.setApprovedDate(set.getString(7));
                System.out.println("ok");
                list.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;

    }

    public int updateTerm(String name, String info, int id) {
        int x = 0;
        try {

            String sql = "update term set term_name=? , term_info=? where id =?";
            ps = con.conn().prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, info);
            ps.setInt(3, id);
            x = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;

    }

    public int deleteterm(int id) {
        int x = 0;
        try {
            String sql = "delete  from term where id =?";
             ps = con.conn().prepareStatement(sql);
            ps.setInt(1, id);
            x = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;

    }

    public List<allTerms> searchItem(String key,int dept) {
        
        List<allTerms> list = new ArrayList<allTerms>();
        try {
            String sql="";
            if (key=="" || key==null) {
                if (dept==0) {
                  sql = "select * from term where approved=1 "; 
              
                }else if(dept>0){
                sql = "select * from term where approved=1 and department_id='"+dept+"' "; 
            }
            }else{
                if (dept==0) {
              sql = "select * from term where approved=1 and term_name like '" + key + "%'";
           
                }
                else {sql = "select * from term where approved=1 and department_id='"+dept+"' and term_name like '" + key + "%'";
            }}
              ps = con.conn().prepareStatement(sql);

            set = ps.executeQuery();
            while (set.next()) {
                System.out.println(set.getString(1) + "  mmmmmmmmmmmmmmmmmmmmmmm");
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
            System.out.println(ex.getMessage());
        }
        return list;

    }
    public int searchdept(String dept) {
        int department=0;
        try {
            String sql = "select * from department where depart_name= '"+dept+"'"; 
              ps = con.conn().prepareStatement(sql);

            set = ps.executeQuery();
         
department=set.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return department;

    }

}
