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
public class Option {
    int o_id;
    String o_Content;
    Question q_id;
    boolean status;

    public Option() {
    }

    public Option(int o_id, String o_Content, Question q_id, boolean status) {
        this.o_id = o_id;
        this.o_Content = o_Content;
        this.q_id = q_id;
        this.status = status;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public String getO_Content() {
        return o_Content;
    }

    public void setO_Content(String o_Content) {
        this.o_Content = o_Content;
    }

    public Question getQ_id() {
        return q_id;
    }

    public void setQ_id(Question q_id) {
        this.q_id = q_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
    
}
