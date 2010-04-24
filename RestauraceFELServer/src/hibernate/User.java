package hibernate;
// Generated 16.3.2009 21:36:54 by Hibernate Tools 3.2.1.GA

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * User generated by hbm2java
 */
public class User extends DBEntity {

    private static final long serialVersionUID = 4579859916170942975L;

    private Integer userId;
    private String firstName;
    private String lastName;
    private String personalIdentificationNumber;
    private String username;
    private String password = "";
    private double credit = 0;
    private int isDeleted;

    public User() {
    }

    public User(String firstName, String lastName, String personalIdentificationNumber, String username, String password, int isDeleted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalIdentificationNumber = personalIdentificationNumber;
        this.username = username;
        this.password = password;
        this.credit = 0;
        this.isDeleted = isDeleted;
    }

    public User(String firstName, String lastName, String personalIdentificationNumber, String username, double credit, int isDeleted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalIdentificationNumber = personalIdentificationNumber;
        this.username = username;
        this.password = "";
        this.credit = credit;
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

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
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

    public static User findById(Integer id) {
        //return (User) findById("User", "userId", id);
        return (User) findByIdNotDeleted("User", "userId", id, "isDeleted", 0);
    }

    public static User findBySurname(String name) {
        //return (User) findByStringName("User", "lastName", name);
        return (User) findByStringNameNotDeleted("User", "lastName", name, "isDeleted", 0);
    }

    public static User findByUsername(String userName) {
        //return (User) findByStringName("User", "username", userName);
        return (User) findByStringNameNotDeleted("User", "username", userName, "isDeleted", 0);
    }

    public static User findByPID(String pid) {
        //return (User) findByStringName("User", "personalIdentificationNumber", pid);
        return (User) findByStringNameNotDeleted("User", "personalIdentificationNumber", pid, "isDeleted", 0);
    }

    //vraci vsechny uzivatelske zaznamy, ktere nejsou oznaceny jako smazane
    public static List findAll(){
        return findAllNotDeleted("User", "isDeleted", 0);
    }
}

