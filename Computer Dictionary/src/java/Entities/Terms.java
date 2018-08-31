/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Models.ReadMore;
import Models.TermsDB;
import Models.admin_defination;
import Models.admin_review_terms;
import Models.allTerms;
import com.sun.faces.context.FacesContextImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed ElSayed
 */
@ManagedBean
@SessionScoped
public class Terms {

    private int id;
    private String termName = "";
    private String info = "";
    private String WritenBy;
    private String approved;
    private String approvedBy;
    private String approvedDate;
    private String msg = "";
    private String searchkey;
    private int depart = 0;
    List<allTerms> terms;
    List<allTerms> admin_review_terms;
    List<allTerms> search_terms;
    private boolean flag = true;
    private boolean info_length = true;
    private String department;
    private String choose_department = "";

    public String getChoose_department() {
        return choose_department;
    }

    public void setChoose_department(String choose_department) {
        this.choose_department = choose_department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public int getDepart() {
        return depart;
    }

    public void setDepart(int depart) {
        this.depart = depart;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getWritenBy() {
        return WritenBy;
    }

    public void setWritenBy(String WritenBy) {
        this.WritenBy = WritenBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    HttpSession ses = (HttpSession) FacesContextImpl.getCurrentInstance().getExternalContext().getSession(false);

    public List<allTerms> getTerms() {
        terms = db.userTerms((int) ses.getAttribute("user_id"));
        return terms;
    }
    admin_review_terms admin = new admin_review_terms();

    public List<allTerms> getAdmin_review_terms() {
        admin_review_terms = admin.selectallTerms();
        return admin_review_terms;
    }

    public List<allTerms> getSearch_terms() {
        search_terms = db.searchItem(searchkey, depart);
        return search_terms;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void changeFlag() {

        flag = false;
    }

    public void cancelchange() {

        flag = true;
    }

    public boolean isInfo_length() {
        return info_length;
    }

    public void setInfo_length(boolean info_length) {
        this.info_length = info_length;
    }

    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
    }

    TermsDB db = new TermsDB();

    public void add_term() {
        msg = "";
        if (choose_department != null && choose_department != "") {
            boolean flag = db.add_term(termName, info, choose_department);
            if (flag) {
                msg = "term is added done! thanks .";
               
            } else {
                msg = "term didint added , please try again!";
            }
        } else {
            msg = "***Please choose the department***";
        }
        choose_department = "";
    }

    admin_defination adm = new admin_defination();

    public void admin_add_term() {

        boolean flag = adm.add_term(termName, info);
        if (flag) {
            msg = "term is added done! thanks .";
            System.out.println("okkkkkkk");
        } else {
            msg = "term didint added , please try again!";
        }

    }

    public void cancel() {
        termName = "";
        info = "";
        msg = "";
        choose_department = "";
    }

    public void cleare() {
        termName = "";
        info = "";
    }

    ReadMore r = new ReadMore();

    public String readmore(int id1) {
        System.out.println("++++++++++++++++++++++++  " + id1);
        ResultSet rs = r.readMore(id1);
        ses.setAttribute("current_term_id", id1);
        try {
            if (rs.next()) {
                termName = rs.getString("term_name");
                info = rs.getString("term_info");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Terms.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ses.getAttribute("user_id") != null) {

            return "read_more?faces-redirect=true";
        }

        return "read_more_without_log?faces-redirect=true";
    }

    public void updateterm() {

        db.updateTerm(termName, info, (int) ses.getAttribute("current_term_id"));
        flag = true;

    }

    public void checklength() {
        if (info.length() >= 25) {
            info_length = true;
        } else {
            info_length = false;
        }
    }

    public String deleteterm(int id1) {
        int x = db.deleteterm(id1);
        if (x > 0) {
            System.out.println("Deleted");
        }
return "admin_defination?faces-redirect=true";
    }

    public String approvedterm(int id1) {
        int x = admin.approvedTerm(id1);
        if (x > 0) {
            System.out.println("approved!");
        }
        return "admin_review_defination?faces-redirect=true";
    }

    public void search() {
        searchkey = searchkey.trim();
        if (searchkey != "" || searchkey != null) {
            search_terms = db.searchItem(searchkey, depart);
        }
    }

    public String chosedepart0() {
        depart = 0;
        search_terms = db.searchItem(searchkey, depart);
        return "home_without_log?faces-redirect=true";
    }

    public String chosedepart1() {
        depart = 1;
        search_terms = db.searchItem(searchkey, depart);
        return "home_without_log?faces-redirect=true";
    }

    public String chosedepart2() {
        depart = 2;
        search_terms = db.searchItem(searchkey, depart);
        return "home_without_log?faces-redirect=true";
    }

    public String chosedepart3() {
        depart = 3;
        search_terms = db.searchItem(searchkey, depart);
        return "home_without_log?faces-redirect=true";
    }

    public String chosedepart4() {
        depart = 4;
        search_terms = db.searchItem(searchkey, depart);
        return "home_without_log?faces-redirect=true";
    }

    public String chosedepart5() {
        depart = 4;
        search_terms = db.searchItem(searchkey, depart);
        return "home_without_log?faces-redirect=true";
    }

    public String chosedeparth0() {
        depart = 0;
        search_terms = db.searchItem(searchkey, depart);
        return "home?faces-redirect=true";
    }

    public String chosedeparth1() {
        depart = 1;
        search_terms = db.searchItem(searchkey, depart);
        return "home?faces-redirect=true";
    }

    public String chosedeparth2() {
        depart = 2;
        search_terms = db.searchItem(searchkey, depart);
        return "home?faces-redirect=true";
    }

    public String chosedeparth3() {
        depart = 3;
        search_terms = db.searchItem(searchkey, depart);
        return "home?faces-redirect=true";
    }

    public String chosedeparth4() {
        depart = 4;
        search_terms = db.searchItem(searchkey, depart);
        return "home?faces-redirect=true";
    }

    public String chosedeparth5() {
        depart = 4;
        search_terms = db.searchItem(searchkey, depart);
        return "home?faces-redirect=true";
    }
}
