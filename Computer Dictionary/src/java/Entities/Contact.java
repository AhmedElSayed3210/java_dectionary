/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Models.ContactDB;
import Models.allContact;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Ahmed ElSayed
 */
@ManagedBean
public class Contact {
     private int id;
    private String name;
    private String email;
    private String message;
private  List<allContact> content;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    ContactDB cont=new ContactDB();
    
    public void addcontact(){
   cont.addcontact(name, email, message);
        System.out.println("aaaaaaaaaaaaaaaaaaa");
    
    }
ContactDB contdb=new ContactDB();
    public List<allContact> getContent() {
        content=contdb.getcontant();
        return content;
    }
    
    public  void deletcont(int id1){
    
    int x=contdb.deletecontact(id1);
        if (x>0) {
            System.out.println("deleted");
        }
    
    }
}
