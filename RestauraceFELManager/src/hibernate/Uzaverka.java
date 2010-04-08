/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Lukas Camra
 */
public class Uzaverka implements Serializable{

    private static final long serialVersionUID = -3485621218713955338L;

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


}
