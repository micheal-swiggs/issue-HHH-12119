package org.hibernate.bugs.db;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Pojo entity for invoice.
 * Stores information concerning the invoice.
 *
 * @author tlang
 */
@Entity
@Table(name = "invoiceprotocol", schema = "public",
        indexes = {
                @Index(name = "index_invoiceProtocolUser", columnList = "invoiceProtocolUser")
        })
public class InvoiceProtocol implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long invoiceProtocolId;

    @Column()
    private String invoiceProtocolMemo;

    @Column(nullable = false, length = 50)
    private String invoiceProtocolUser;

    @Column(nullable = false)
    private LocalDate invoiceProtocolDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;

    /**
     * Standard constructor.
     * Builds the needed dependencies.
     */
    public InvoiceProtocol() {
        this.invoiceProtocolDate = LocalDate.now();
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
        InvoiceProtocol that = (InvoiceProtocol) o;
        return invoiceProtocolId == that.invoiceProtocolId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getInvoiceProtocolId() {
        return invoiceProtocolId;
    }

    public void setInvoiceProtocolId(long invoiceProtocolId) {
        this.invoiceProtocolId = invoiceProtocolId;
    }

    public String getInvoiceProtocolMemo() {
        return invoiceProtocolMemo;
    }

    public void setInvoiceProtocolMemo(String invoiceProtocolMemo) {
        this.invoiceProtocolMemo = invoiceProtocolMemo;
    }

    public String getInvoiceProtocolUser() {
        return invoiceProtocolUser;
    }

    public void setInvoiceProtocolUser(String invoiceProtocolUser) {
        this.invoiceProtocolUser = invoiceProtocolUser;
    }

    public LocalDate getInvoiceProtocolDate() {
        return invoiceProtocolDate;
    }

    public void setInvoiceProtocolDate(LocalDate invoiceProtocolDate) {
        this.invoiceProtocolDate = invoiceProtocolDate;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    /**
     * Computes a hash code for this instance.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(invoiceProtocolId);
    }

}