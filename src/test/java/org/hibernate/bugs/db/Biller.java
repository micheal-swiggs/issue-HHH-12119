package org.hibernate.bugs.db;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Pojo entity for biller information.
 * Stores information concerning the invoice´s biller.
 *
 * @author tlang
 */
@Entity
@Table(name = "biller", schema = "public",
        indexes = {
                @Index(name = "index_biller", columnList = "biller"),
                @Index(name = "index_billerCampus", columnList = "billerCampus"),
                @Index(name = "index_billerContactPerson", columnList = "billerContactPerson"),
                @Index(name = "index_billerPostalcode", columnList = "billerPostalcode"),
                @Index(name = "index_billerCity", columnList = "billerCity")
        })
public class Biller implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long billerId;

    @Column(nullable = false)
    @Enumerated
    private Campus billerCampus;

    @Column(length = 50)
    private String billerSalutation;

    @Column(nullable = false, length = 150)
    private String biller;

    @Column(length = 50)
    private String billerContactPerson;

    @Column(length = 50)
    private String billerContactDetails;

    @Column(nullable = false, length = 100)
    private String billerStreet;

    @Column(length = 50)
    private String billerBox;

    @Column(nullable = false, length = 10)
    private String billerPostalcode;

    @Column(nullable = false, length = 50)
    private String billerCity;

    @Column(nullable = false, length = 50)
    private String billerCountry;

    @Column(nullable = false, length = 50)
    private String billerWeb;

    @Column(nullable = false)
    @Embedded
    private Information information;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "biller", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Invoice> invoiceSet;

    /**
     * Standard Constructor.
     */
    public Biller() {
        this.billerCampus = Campus.THD;
        this.invoiceSet = new HashSet<>();
        this.information = new Information();
    }

    /**
     * Compares this instance with another instance of this.
     *
     * @param o another instance
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biller biller = (Biller) o;
        return billerId == biller.billerId;
    }

    /**
     * Computes a hash code for this instance.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(billerId);
    }

    /**
     * Readable version of the current instance.
     *
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s %s, %s, %s", biller, billerStreet, billerPostalcode, billerCity);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getBillerId() {
        return billerId;
    }

    public void setBillerId(long billerId) {
        this.billerId = billerId;
    }

    public Campus getBillerCampus() {
        return billerCampus;
    }

    public void setBillerCampus(Campus billerCampus) {
        this.billerCampus = billerCampus;
    }

    public String getBillerSalutation() {
        return billerSalutation;
    }

    public void setBillerSalutation(String billerSalutation) {
        this.billerSalutation = billerSalutation;
    }

    public String getBiller() {
        return biller;
    }

    public void setBiller(String biller) {
        this.biller = biller;
    }

    public String getBillerContactPerson() {
        return billerContactPerson;
    }

    public void setBillerContactPerson(String billerContactPerson) {
        this.billerContactPerson = billerContactPerson;
    }

    public String getBillerContactDetails() {
        return billerContactDetails;
    }

    public void setBillerContactDetails(String billerContactDetails) {
        this.billerContactDetails = billerContactDetails;
    }

    public String getBillerStreet() {
        return billerStreet;
    }

    public void setBillerStreet(String billerStreet) {
        this.billerStreet = billerStreet;
    }

    public String getBillerBox() {
        return billerBox;
    }

    public void setBillerBox(String billerBox) {
        this.billerBox = billerBox;
    }

    public String getBillerPostalcode() {
        return billerPostalcode;
    }

    public void setBillerPostalcode(String billerPostalcode) {
        this.billerPostalcode = billerPostalcode;
    }

    public String getBillerCity() {
        return billerCity;
    }

    public void setBillerCity(String billerCity) {
        this.billerCity = billerCity;
    }

    public String getBillerCountry() {
        return billerCountry;
    }

    public void setBillerCountry(String billerCountry) {
        this.billerCountry = billerCountry;
    }

    public String getBillerWeb() {
        return billerWeb;
    }

    public void setBillerWeb(String billerWeb) {
        this.billerWeb = billerWeb;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public Set<Invoice> getInvoiceSet() {
        return invoiceSet;
    }

    public void setInvoiceSet(Set<Invoice> invoiceSet) {
        this.invoiceSet = invoiceSet;
    }
}