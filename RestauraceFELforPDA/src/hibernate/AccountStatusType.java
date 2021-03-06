package hibernate;
// Generated 16.3.2009 21:36:54 by Hibernate Tools 3.2.1.GA

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * Accountstatustype generated by hbm2java
 */
public class AccountStatusType implements Serializable {

    private static final long serialVersionUID = -345056626509032692L;

     private Integer accountStatusTypeId;
     private String name;
     private String note;
     private int isDeleted;

    public AccountStatusType() {
    }


    public AccountStatusType(String name, int isDeleted) {
        this.name = name;
        this.isDeleted = isDeleted;
    }
    public AccountStatusType(String name, String note, int isDeleted) {
       this.name = name;
       this.note = note;
       this.isDeleted = isDeleted;
    }

        //tato metoda zde musi byt, a to z duvodu, aby mohla probehnout spravne serializace
    //zapis objektu do proudu bajtu
    private void writeObject(ObjectOutputStream stream) throws IOException {

    }

    //tato metoda zde musi byt, a to z duvodu, aby mohla probehnout spravne serializace
    //cteni a rekonstrukce objektu z proudu bajtu
    private void readObject(ObjectInputStream stream) throws IOException {

    }

    public Integer getAccountStatusTypeId() {
        return this.accountStatusTypeId;
    }

    public void setAccountStatusTypeId(Integer accountStatusTypeId) {
        this.accountStatusTypeId = accountStatusTypeId;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

}


