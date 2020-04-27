/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import context.DBContext;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author tranb
 */
public class Article {
    private int id;
    private String title;
    private String imageUrl;
    private String content;
    private Timestamp date;
    private String author;
    private String shortDes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }
    public String getDateFormat() {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMM dd yyyy - hh:mm");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("a");
        
        return dateFormat1.format(this.date)+dateFormat2.format(this.date).toLowerCase() ;
    }
    
    public String getImgUrl(){
        DBContext db = new DBContext();
        String image_url = db.getImagePath();
        return image_url+this.imageUrl;
    }
    
}
