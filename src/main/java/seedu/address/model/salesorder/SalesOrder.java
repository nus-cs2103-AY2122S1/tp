package seedu.address.model.salesorder;

import seedu.address.model.Date;

public class SalesOrder {
    private static int count = 1;

    private final Customer customer;
    private final long id;
    private Amount amount;
    private Date date;
    private boolean isComplete;

    /**
     * Constructor creates a SalesOrder related to the customer, due on the given date, with the given amount.
     * isComplete flag is set to False initially, and the id is automatically assigned.
     */
    public SalesOrder(Customer customer, Date date, Amount amount) {
        this.customer = customer;
        this.date = date;
        this.amount = amount;
        this.id = SalesOrder.count;
        this.isComplete = false;

        SalesOrder.count++;
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public Amount getAmount() {
        return this.amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    //SalesOrder string representation is temporary, change as necessary for UI.
    @Override
    public String toString() {
        if (isComplete) {
            return "[X] " + "SO" + id;
        } else {
            return "[ ]" + "SO" + id + ", due: " + date;
        }
    }
}
