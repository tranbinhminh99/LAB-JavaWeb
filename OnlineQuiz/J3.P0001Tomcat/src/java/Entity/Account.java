/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author tranb
 */
public class Account {

    String userName;
    String password;
    UserType a_Type;
    String Email;

    public Account(String userName, String password, UserType t_id, String Email) {
        this.userName = userName;
        this.password = password;
        this.a_Type = t_id;
        this.Email = Email;
    }

    public Account() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getA_Type() {
        return a_Type;
    }

    public void setA_Type(UserType a_Type) {
        this.a_Type = a_Type;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

}
