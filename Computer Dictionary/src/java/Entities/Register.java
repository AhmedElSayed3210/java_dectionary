/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Models.RegisterDB;
import Models.admin_register;
import Models.allsubscribers;
import Models.subscribers;
import com.sun.faces.context.FacesContextImpl;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed ElSayed
 */
@ManagedBean
@SessionScoped

public class Register {

    private int id;
    private String name;
    private String email;
    private String password;
    private String confirm_password;
    private String msg = "";
    private List<allsubscribers> subscrib;
    private String npassword;

    public String getNpassword() {
        return npassword;
    }

    public void setNpassword(String npassword) {
        this.npassword = npassword;
    }
    private int countpost = 0;

    public int getCountpost() {
        return countpost;
    }

    public void setCountpost(int countpost) {
        this.countpost = countpost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setConfirm_password(String confirm_password) {

        this.confirm_password = confirm_password;

    }

    subscribers al = new subscribers();

    public List<allsubscribers> getSubscrib() {
        subscrib = al.selectallRegister();
        return subscrib;
    }

    public void setSubscrib(List<allsubscribers> subscrib) {
        this.subscrib = subscrib;
    }

    RegisterDB re = new RegisterDB();
    admin_register ad = new admin_register();

    public String singnUp() {
  if (email.contains("@")&& email.contains(".com")) {
      password=encur_pass(password);
      confirm_password=encur_pass(confirm_password);
        if (password.equals(confirm_password)) {
            boolean r = re.add_user(name, email, password);
            if (r == true) {
                msg = "";
                Logout.setChecklog(true);
                return "home?faces-redirect=true";
            } else {
                password = null;
                confirm_password = null;
                msg = "invalid try again! ";

            }
        } else {

            msg = "password and confirm not equal!";
        }
  }else{
  msg="****please Enter avlid email!****";
  }

        return null;
    }
    HttpSession ses = (HttpSession) FacesContextImpl.getCurrentInstance().getExternalContext().getSession(false);

    public String login() {
        if (email.contains("@")&& email.contains(".com")) {
            
        
        msg = "";
        password=encur_pass(password);
        boolean r = re.usersearch(email, password);
        boolean admin = ad.usersearch(email, password);

        if (r == true) {
            msg = "";
            Logout.setChecklog(true);
            countpost = re.termcountuser((int) ses.getAttribute("user_id"));
            return "home?faces-redirect=true";
        } else if (admin) {
            System.out.println("***************************************");
            Logout.setAdminchecklog(true);
            return "admin_home?faces-redirect=true";
        } else {
            clear();
            msg = "User Not Found!.. try again! ";

        }
    }else{
        msg="****Please Enter a vlid email!****";
        }
        return null;

    }
public void changePassword(){
       String pass=(String) ses.getAttribute("pass");
password=encur_pass(password);    
npassword=encur_pass(npassword);    

        if (pass.equals(password) && npassword!="") {
          re.change_userpassword(npassword);  
          msg="password updated!";
        }
        else{
        msg="password not updated .. try again";
        }
}
    public void clear() {
        name = "";
        password = null;
        confirm_password = null;
        email = "";
    }
    public static String encur_pass(String pass){
    int x;
    String newpass="";
        for (int i = 0; i < pass.length(); i++) {
            x=(int)pass.charAt(i);
            newpass+=x;
        }
    
    return newpass;
    }
    public static void main(String[] args) {
        System.out.println(encur_pass("admin"));
    }
}
