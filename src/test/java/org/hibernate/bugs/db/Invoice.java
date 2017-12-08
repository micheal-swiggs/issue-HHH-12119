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
public class Invoice  {

    @Id
    @GeneratedValue
    private long invoiceId;

    @Column(nullable = false, length = 50)
    private String bookingTag;


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
}