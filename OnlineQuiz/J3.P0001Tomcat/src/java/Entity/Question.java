/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author tranb
 */
public class Question {

    int q_id;
    String q_content;
    Date date_Create;

    public Question(int q_id, String q_content, Date date_Create) {
        this.q_id = q_id;
        this.q_content = q_content;
        this.date_Create = date_Create;
    }

    public Date getDate_Create() {
        return date_Create;
    }

    public void setDate_Create(Date date_Create) {
        this.date_Create = date_Create;
    }

    public Question() {
    }

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    public String getQ_content() {
        return q_content;
    }

    public void setQ_content(String q_content) {
        this.q_content = q_content;
    }

}
