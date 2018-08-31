/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.sun.faces.context.FacesContextImpl;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

@ManagedBean
public class Logout {
    public boolean login=false;

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
    
   private static boolean checklog=false;
    private static boolean adminchecklog=false;

    public static boolean isChecklog() {
        return checklog;
    }

    public static void setChecklog(boolean checklog) {
        Logout.checklog = checklog;
    }

   

   
    public static boolean isAdminchecklog() {
        return adminchecklog;
    }

    public static void setAdminchecklog(boolean adminchecklog) {
        Logout.adminchecklog = adminchecklog;
    }
   
 
    public String logout(){
        try {
            login=true;
           
         HttpSession ses=(HttpSession) FacesContextImpl.getCurrentInstance().getExternalContext().getSession(false);
   ses.invalidate();
   checklog=false;
adminchecklog=false;
   ses.setAttribute("login", false); 
        } catch (Exception e) {
            System.out.println(e);
        }
        
    return "home_without_log?faces-redirect=true";
   
}
    
}