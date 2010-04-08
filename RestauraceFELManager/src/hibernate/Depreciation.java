package hibernate;
// Generated 16.3.2009 21:36:54 by Hibernate Tools 3.2.1.GA

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * Depreciation generated by hbm2java
 */
public class Depreciation implements Serializable {

    private static final long serialVersionUID = -345056626509032692L;

    private Integer depreciationId;
    private Material material;
    private double quantity;
    private Date date;
    private ReasonType reasonType;
    private User userOffender;
    private User userReporter;
    private String note;
    private int isDeleted;

    public Depreciation() {
    }

    public Depreciation(User userReporter, User userOffender, Material material, double quantity, Date date, int isDeleted) {
        this.userReporter = userReporter;
        this.userOffender = userOffender;
        this.material = material;
        this.quantity = quantity;
        this.date = date;
        this.isDeleted = isDeleted;
    }

    public Depreciation(User userReporter, User userOffender, Material material, ReasonType reasoneType, double quantity, Date date, String note, int isDeleted) {
        this.userReporter = userReporter;
        this.userOffender = userOffender;
        this.material = material;
        this.reasonType = reasoneType;
        this.quantity = quantity;
        this.date = date;
        this.note = note;
        this.isDeleted = isDeleted;
    }

    //tato metoda zde musi byt, a to z duvodu, aby mohla probehnout spravne serializace
    //zapis objektu do proudu bajtu
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeInt(depreciationId);
        stream.writeObject(date);

        Material m = new Material();
        UnitType ut = new UnitType(material.getUnitType().getName(), material.getUnitType().getAbbreviation(), material.getUnitType().getTypeId(), material.getUnitType().getIsDeleted());
        ut.setUnitTypeId(material.getUnitType().getUnitTypeId());
        MaterialType mt = new MaterialType(material.getMaterialType().getName(), material.getMaterialType().getNote(), material.getMaterialType().getIsDeleted());
        mt.setMaterialTypeId(material.getMaterialType().getMaterialTypeId());
        m.setMaterialId(material.getMaterialId());
        m.setUnitType(ut);
        m.setMaterialType(mt);
        m.setName(material.getName());
        m.setCurrentQuantity(material.getCurrentQuantity());
        m.setBarcode(material.getBarcode());
        m.setMinimal(material.getMinimal());
        m.setIsDeleted(material.getIsDeleted());
        stream.writeObject(m);

        stream.writeDouble(quantity);

        User uOffered = new User(userOffender.getFirstName(), userOffender.getLastName(), userOffender.getPersonalIdentificationNumber(), userOffender.getUsername(), userOffender.getPassword(), userOffender.getIsDeleted());
        uOffered.setUserId(userOffender.getUserId());
        stream.writeObject(uOffered);

        User uReporter = new User(userReporter.getFirstName(), userReporter.getLastName(), userReporter.getPersonalIdentificationNumber(), userReporter.getUsername(), userReporter.getPassword(), userReporter.getIsDeleted());
        uOffered.setUserId(userReporter.getUserId());
        stream.writeObject(uReporter);

        ReasonType rt = null;
        if (reasonType != null) {
            rt = new ReasonType(reasonType.getName(), reasonType.getNote(), reasonType.getIsDeleted());
        }
        stream.writeObject(rt);

        stream.writeObject(note);
        stream.writeInt(isDeleted);
    }

    //tato metoda zde musi byt, a to z duvodu, aby mohla probehnout spravne serializace
    //cteni a rekonstrukce objektu z proudu bajtu
    private void readObject(ObjectInputStream stream) throws IOException {
        try {
            depreciationId = stream.readInt();
            date = (Date) (stream.readObject());
            material = (Material) (stream.readObject());
            quantity = stream.readDouble();
            userOffender = (User) (stream.readObject());
            userReporter = (User) (stream.readObject());
            reasonType = (ReasonType) (stream.readObject());
            note = (String) (stream.readObject());
            isDeleted = stream.readInt();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public Integer getDepreciationId() {
        return this.depreciationId;
    }

    public void setDepreciationId(Integer depreciationId) {
        this.depreciationId = depreciationId;
    }

    public User getUserReporter() {
        return this.userReporter;
    }

    public void setUserReporter(User userReporter) {
        this.userReporter = userReporter;
    }

    public User getUserOffender() {
        return this.userOffender;
    }

    public void setUserOffender(User userOffender) {
        this.userOffender = userOffender;
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public ReasonType getReasonType() {
        return this.reasonType;
    }

    public void setReasonType(ReasonType reasoneType) {
        this.reasonType = reasoneType;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
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


