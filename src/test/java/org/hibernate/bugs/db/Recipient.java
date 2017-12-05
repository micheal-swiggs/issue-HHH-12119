package org.hibernate.bugs.db;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Pojo entity for application user information.
 * Stores information concerning the application user.
 *
 * @author tlang
 */
@Entity
@Table(name = "recipient", schema = "public",
        indexes = {
                @Index(name = "index_recipient", columnList = "recipient"),
                @Index(name = "index_recipientContactPerson", columnList = "recipientContactPerson"),
                @Index(name = "index_recipientPostalcode", columnList = "recipientPostalcode"),
                @Index(name = "index_recipientCity", columnList = "recipientCity")
        })

public class Recipient implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long recipientId;

    @Column(length = 50)
    private String recipientSalutation;

    @Column(nullable = false, length = 150)
    private String recipient;

    @Column(length = 150)
    private String recipientContactPerson;

    @Column(nullable = false, length = 100)
    private String recipientStreet;

    @Column(length = 50)
    private String recipientBox;

    @Column(nullable = false, length = 10)
    private String recipientPostalcode;

    @Column(nullable = false, length = 50)
    private String recipientCity;

    @Column(nullable = false, length = 50)
    private String recipientCountry;

    @Column(length = 50)
    private String recipientWeb;

    @Column(nullable = false)
    @Embedded
    private Information information;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "recipient", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Invoice> invoiceSet;

    /**
     * Standard Constructor.
     */
    public Recipient() {
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
        Recipient recipient = (Recipient) o;
        return recipientId == recipient.recipientId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(long recipientId) {
        this.recipientId = recipientId;
    }

    public String getRecipientSalutation() {
        return recipientSalutation;
    }

    public void setRecipientSalutation(String recipientSalutation) {
        this.recipientSalutation = recipientSalutation;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipientContactPerson() {
        return recipientContactPerson;
    }

    public void setRecipientContactPerson(String recipientContactPerson) {
        this.recipientContactPerson = recipientContactPerson;
    }

    public String getRecipientStreet() {
        return recipientStreet;
    }

    public void setRecipientStreet(String recipientStreet) {
        this.recipientStreet = recipientStreet;
    }

    public String getRecipientBox() {
        return recipientBox;
    }

    public void setRecipientBox(String recipientBox) {
        this.recipientBox = recipientBox;
    }

    public String getRecipientPostalcode() {
        return recipientPostalcode;
    }

    public void setRecipientPostalcode(String recipientPostalcode) {
        this.recipientPostalcode = recipientPostalcode;
    }

    public String getRecipientCity() {
        return recipientCity;
    }

    public void setRecipientCity(String recipientCity) {
        this.recipientCity = recipientCity;
    }

    public String getRecipientCountry() {
        return recipientCountry;
    }

    public void setRecipientCountry(String recipientCountry) {
        this.recipientCountry = recipientCountry;
    }

    public String getRecipientWeb() {
        return recipientWeb;
    }

    public void setRecipientWeb(String recipientWeb) {
        this.recipientWeb = recipientWeb;
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

    /**
     * Computes a hash code for this instance.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(recipientId);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        if (recipientSalutation != null && !recipientSalutation.isEmpty()) {
            sb.append(recipientSalutation);
            sb.append(System.lineSeparator());
        }
        if (recipient != null && !recipient.isEmpty()) {
            sb.append(recipient);
            sb.append(System.lineSeparator());
        }
        if (recipientContactPerson != null && !recipientContactPerson.isEmpty()) {
            sb.append(recipientContactPerson);
            sb.append(System.lineSeparator());
        }
        if (recipientBox != null && !recipientBox.isEmpty()) {
            sb.append(recipientBox);
            sb.append(System.lineSeparator());
        }
        if (recipientBox != null && !recipientBox.isEmpty()) {
            sb.append(recipientBox);
            sb.append(System.lineSeparator());
        }
        if (recipientStreet != null && !recipientStreet.isEmpty()) {
            sb.append(recipientStreet);
            sb.append(System.lineSeparator());
        }
        if (recipientPostalcode != null && !recipientPostalcode.isEmpty()) {
            sb.append(recipientPostalcode);
            sb.append(" ");
        }
        if (recipientCity != null && !recipientCity.isEmpty()) {
            sb.append(recipientCity);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}