package org.hibernate.bugs.db;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * Pojo entity for application user information.
 * Stores information concerning the application user.
 *
 * @author tlang
 */
@Entity
@Table(name = "lineitem", schema = "public")
public class LineItem implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long lineItemId;

    @Column(nullable = false)
    private int lineItemNumber;

    @Column(nullable = false, scale = 2, precision = 10)
    private BigDecimal lineItemQuantity;

    @Column(nullable = false, length = 25)
    private String lineItemUnit;

    @Column(nullable = false, length = 100)
    private String lineItemText;

    @Column(nullable = false, scale = 2, precision = 10)
    private BigDecimal lineItemUnitPrice;

    @Transient
    private BigDecimal lineItemTotalPrice;

    @Transient
    private BigDecimal lineItemTotalPriceGross;

    @Column(nullable = false)
    private int bettermentTaxRate;

    @Transient
    private BigDecimal bettermentTaxValue;

    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;


    /**
     * Standard Constructor.
     */
    public LineItem() {
        this.lineItemNumber = 1;
        this.bettermentTaxRate = 0;
        this.lineItemQuantity = new BigDecimal(BigInteger.ONE);
        this.lineItemUnitPrice = new BigDecimal(BigInteger.ZERO);
        this.lineItemTotalPrice = new BigDecimal(BigInteger.ZERO);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(long lineItemId) {
        this.lineItemId = lineItemId;
    }

    public int getLineItemNumber() {
        return lineItemNumber;
    }

    public void setLineItemNumber(int lineItemNumber) {
        this.lineItemNumber = lineItemNumber;
    }

    public BigDecimal getLineItemQuantity() {
        return lineItemQuantity;
    }

    public void setLineItemQuantity(BigDecimal lineItemQuantity) {
        this.lineItemQuantity = lineItemQuantity;
    }

    public String getLineItemUnit() {
        return lineItemUnit;
    }

    public void setLineItemUnit(String lineItemUnit) {
        this.lineItemUnit = lineItemUnit;
    }

    public String getLineItemText() {
        return lineItemText;
    }

    public void setLineItemText(String lineItemText) {
        this.lineItemText = lineItemText;
    }

    public BigDecimal getLineItemUnitPrice() {
        return lineItemUnitPrice;
    }

    public void setLineItemUnitPrice(BigDecimal lineItemUnitPrice) {
        this.lineItemUnitPrice = lineItemUnitPrice;
    }

    public void setLineItemTotalPrice(BigDecimal lineItemTotalPrice) {
        this.lineItemTotalPrice = lineItemTotalPrice;
    }

    public void setLineItemTotalPriceGross(BigDecimal lineItemTotalPriceGross) {
        this.lineItemTotalPriceGross = lineItemTotalPriceGross;
    }

    public int getBettermentTaxRate() {
        return bettermentTaxRate;
    }

    public void setBettermentTaxRate(int bettermentTaxRate) {
        this.bettermentTaxRate = bettermentTaxRate;
    }

    public void setBettermentTaxValue(BigDecimal bettermentTaxValue) {
        this.bettermentTaxValue = bettermentTaxValue;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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
        LineItem lineItem = (LineItem) o;
        return lineItemId == lineItem.lineItemId &&
                lineItemNumber == lineItem.lineItemNumber;
    }

    /**
     * Computes a hash code for this instance.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(lineItemId, lineItemNumber);
    }

    /**
     * Getter for the line item total price.
     *
     * @return BigDecimal
     */
    public BigDecimal getLineItemTotalPrice() {
        if (this.lineItemTotalPrice == null) return BigDecimal.ZERO;
        return this.lineItemTotalPrice = this.lineItemUnitPrice.multiply(this.lineItemQuantity);
    }

    /**
     * Getter for the betterment tax value.
     *
     * @return BigDecimal
     */
    public BigDecimal getBettermentTaxValue() {
        if (this.lineItemTotalPrice == null) return BigDecimal.ZERO;
        return this.bettermentTaxValue = this.lineItemUnitPrice.multiply(this.lineItemQuantity).multiply(BigDecimal.valueOf(this.bettermentTaxRate).divide(BigDecimal.valueOf(100)));
    }

    /**
     * Getter for the line item total price including taxes.
     *
     * @return BigDecimal
     */
    public BigDecimal getLineItemTotalPriceGross() {
        if (this.lineItemTotalPrice == null) return BigDecimal.ZERO;
        return this.bettermentTaxValue = this.lineItemUnitPrice
                .multiply(this.lineItemQuantity)
                .multiply(BigDecimal.valueOf((100 + this.bettermentTaxRate))).divide(BigDecimal.valueOf(100));
    }
}