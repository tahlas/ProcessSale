package model;

/**
 * This is the payment the customer provides to pay for the sale.
 */
public class CashPayment {
    private final Amount paidAmount;
    private Amount totalCostForSale;

    /**
     * Creates a new instance.
     * @param paidAmount The amount the customer paid for the sale.
     */
    public CashPayment(Amount paidAmount) {
        this.paidAmount = paidAmount;
    }

    /**
     *
     * @param total The total cost of the sale.
     */
    public void setTotalCostForSale(Amount total) {
        this.totalCostForSale = total;
    }

    /**
     * Gets the amount the customer paid.
     * @return The amount the customer paid.
     */
    public Amount getPaidAmount(){
        return this.paidAmount;
    }

    /**
     * Gets the total cost of the sale.
     * @return The total cost amount.
     */
    public Amount getTotalCostForSale(){
        return this.totalCostForSale;
    }

    /**
     * Calculates the change when paying with cash.
     * @return The change.
     */
    public Amount getChange(){
        return paidAmount.minus(totalCostForSale);
    }
}
