package hibernate;
// Generated 16.3.2009 21:36:54 by Hibernate Tools 3.2.1.GA

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * User generated by hbm2java
 */
public class User implements Serializable {

    private static final long serialVersionUID = 4579859916170942975L;

    private Integer userId;
    private String firstName;
    private String lastName;
    private String personalIdentificationNumber;
    private String username;
    private String password;
    private int isDeleted;

    public User() {
    }

    public User(String firstName, String lastName, String personalIdentificationNumber, String username, String password, int isDeleted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalIdentificationNumber = personalIdentificationNumber;
        this.username = username;
        this.password = password;
        this.isDeleted = isDeleted;
    }

    //tato metoda zde musi byt, a to z duvodu, aby mohla probehnout spravne serializace
    //zapis objektu do proudu bajtu
    private void writeObject(ObjectOutputStream stream) throws IOException {
        //stream.defaultWriteObject();
        stream.writeInt(userId);
        stream.writeObject(firstName);
        stream.writeObject(lastName);
        stream.writeObject(personalIdentificationNumber);
        stream.writeObject(username);
        stream.writeObject(password);
        stream.writeInt(isDeleted);
    }

    //tato metoda zde musi byt, a to z duvodu, aby mohla probehnout spravne serializace
    //cteni a rekonstrukce objektu z proudu bajtu
    private void readObject(ObjectInputStream stream) throws IOException {
        try {
            //stream.defaultReadObject();
            userId = stream.readInt();
            firstName = (String)stream.readObject();
            lastName = (String)stream.readObject();
            personalIdentificationNumber = (String)stream.readObject();
            username = (String)stream.readObject();
            password = (String)stream.readObject();
            isDeleted = stream.readInt();
        } catch (Exception e){}
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalIdentificationNumber() {
        return this.personalIdentificationNumber;
    }

    public void setPersonalIdentificationNumber(String personalIdentificationNumber) {
        this.personalIdentificationNumber = personalIdentificationNumber;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

}


