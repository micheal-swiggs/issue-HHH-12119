package org.hibernate.bugs.db;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Pojo entity for invoice.
 * Stores information concerning the invoice.
 *
 * @author tlang
 */
@Entity
@Table(name = "invoice", schema = "public",
        indexes = {
                @Index(name = "index_bookingTag", columnList = "bookingTag"),
                @Index(name = "index_invoiceApiGroups", columnList = "invoiceApiGroups"),
                @Index(name = "index_invoiceNumber", columnList = "invoiceNumber", unique = true),
                @Index(name = "index_invoiceDate", columnList = "invoiceDate")
        })
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Invoice.full",
                attributeNodes = {
                        @NamedAttributeNode("lineItemSet"),
                        @NamedAttributeNode("invoiceProtocolSet"),
                        @NamedAttributeNode("recipient"),
                        @NamedAttributeNode("biller")
                })
})
public class Invoice implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long invoiceId;

    @Column(nullable = false, length = 50)
    private String bookingTag;

    @Column(unique = true, nullable = false, length = 25)
    private String invoiceNumber;

    @Column()
    private String invoiceMemo;

    @Column(nullable = false)
    private LocalDate invoiceDate;

    @Column(nullable = false)
    private LocalDate serviceDateFrom;

    @Column(nullable = false)
    private LocalDate serviceDateUntil;

    @Column(nullable = false)
    private String accountingConnection;

    @Column(nullable = false, length = 1024)
    private String paymentTerms;

    @Column(nullable = false, length = 50)
    private String taxId;

    @Column(nullable = false, length = 50)
    private String salesTaxId;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "invoice", fetch = FetchType.LAZY)
    @OrderBy("lineItemNumber")
    private Set<LineItem> lineItemSet;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "invoice", fetch = FetchType.LAZY)
    @OrderBy("invoiceProtocolDate")
    private Set<InvoiceProtocol> invoiceProtocolSet;

    @Column(nullable = false)
    @Embedded
    private Information information;

    @Column(nullable = false)
    private String invoiceApiGroups;

    @Column(nullable = false)
    @Enumerated
    private InvoiceState invoiceState;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Recipient recipient;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Biller biller;

    /**
     * Standard Constructor.
     */
    public Invoice() {
        this.lineItemSet = new HashSet<>();
        this.invoiceProtocolSet = new HashSet<>();
        this.information = new Information();
        this.invoiceDate = LocalDate.now();
        this.serviceDateFrom = LocalDate.now();
        this.serviceDateUntil = LocalDate.now();
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
        Invoice invoice = (Invoice) o;
        return invoiceId == invoice.invoiceId;
    }

    /**
     * Computes a hash code for this instance.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(invoiceId);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getBookingTag() {
        return bookingTag;
    }

    public void setBookingTag(String bookingTag) {
        this.bookingTag = bookingTag;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceMemo() {
        return invoiceMemo;
    }

    public void setInvoiceMemo(String invoiceMemo) {
        this.invoiceMemo = invoiceMemo;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public LocalDate getServiceDateFrom() {
        return serviceDateFrom;
    }

    public void setServiceDateFrom(LocalDate serviceDateFrom) {
        this.serviceDateFrom = serviceDateFrom;
    }

    public LocalDate getServiceDateUntil() {
        return serviceDateUntil;
    }

    public void setServiceDateUntil(LocalDate serviceDateUntil) {
        this.serviceDateUntil = serviceDateUntil;
    }

    public String getAccountingConnection() {
        return accountingConnection;
    }

    public void setAccountingConnection(String accountingConnection) {
        this.accountingConnection = accountingConnection;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getSalesTaxId() {
        return salesTaxId;
    }

    public void setSalesTaxId(String salesTaxId) {
        this.salesTaxId = salesTaxId;
    }

    public Set<LineItem> getLineItemSet() {
        return lineItemSet;
    }

    public void setLineItemSet(Set<LineItem> lineItemSet) {
        this.lineItemSet = lineItemSet;
    }

    public Set<InvoiceProtocol> getInvoiceProtocolSet() {
        return invoiceProtocolSet;
    }

    public void setInvoiceProtocolSet(Set<InvoiceProtocol> invoiceProtocolSet) {
        this.invoiceProtocolSet = invoiceProtocolSet;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public String getInvoiceApiGroups() {
        return invoiceApiGroups;
    }

    public void setInvoiceApiGroups(String invoiceApiGroups) {
        this.invoiceApiGroups = invoiceApiGroups;
    }

    public InvoiceState getInvoiceState() {
        return invoiceState;
    }

    public void setInvoiceState(InvoiceState invoiceState) {
        this.invoiceState = invoiceState;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public Biller getBiller() {
        return biller;
    }

    public void setBiller(Biller biller) {
        this.biller = biller;
    }
}