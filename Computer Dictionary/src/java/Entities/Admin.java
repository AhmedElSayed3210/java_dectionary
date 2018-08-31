/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Models.admin_register;
import com.sun.faces.context.FacesContextImpl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed ElSayed
 */
@ManagedBean
@SessionScoped
public class Admin {
    
    private int id;
    private String name;
    private String email;
    private String password;
    private String Npassword;
    private String cpassword;
    

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

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public String getNpassword() {
        return Npassword;
    }

    public void setNpassword(String Npassword) {
        this.Npassword = Npassword;
    }
    
    HttpSession ses=(HttpSession) FacesContextImpl.getCurrentInstance().getExternalContext().getSession(false);
           
    admin_register ad=new admin_register();
    
    public void changUsername(){
       String pass=(String) ses.getAttribute("admin_password");
password=encur_pass(password);
       if (pass.equals(password)) {
          ad.change_username(name);  
        }
    }
    
    public void changePassword(){
        password=encur_pass(password);
        cpassword=encur_pass(cpassword);
       String pass=(String) ses.getAttribute("admin_password");
        System.out.println(pass+" nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
        if (pass.equals(password) && cpassword.equals(Npassword)) {
          ad.change_userpassword(Npassword);  
        }
    }
    
      public String encur_pass(String pass){
    int x;
    String newpass="";
        for (int i = 0; i < pass.length(); i++) {
            x=(int)pass.charAt(i);
            newpass+=x;
        }
    
    return newpass;
    }
}
