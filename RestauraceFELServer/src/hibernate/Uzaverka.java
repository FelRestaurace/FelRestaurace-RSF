/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Lukas Camra
 */
public class Uzaverka extends DBEntity{
    private static final long serialVersionUID = -3485621218713955338L;

    private static List<Uzaverka> findAll() {
        return findAll("Uzaverka");
    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private User user;
    //private Uzaverka previous;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

        public void create() {
        create(this);
    }

    public void delete() {
        delete(this);
    }

    public void update(){
        update(this);
    }

    
}
