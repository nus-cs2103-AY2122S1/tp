package seedu.address.model.order;

import seedu.address.model.Date;

public class Order {
    private static int count = 1;

    private final Customer customer;
    private final long id;
    private Amount amount;
    private Date date;
    private boolean isComplete;

    /**
     * Constructor creates a Order related to the customer, due on the given date, with the given amount.
     * isComplete flag is set to False initially, and the id is automatically assigned.
     */
    public Order(Customer customer, Date date, Amount amount) {
        this.customer = customer;
        this.date = date;
        this.amount = amount;
        this.id = Order.count;
        this.isComplete = false;

        Order.count++;
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

    //Order string representation is temporary, change as necessary for UI.
    @Override
    public String toString() {
        if (isComplete) {
            return "[X] " + "SO" + id;
        } else {
            return "[ ]" + "SO" + id + ", due: " + date;
        }
    }

    //required for OrderBook to check if a Order exists, before marking it.
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return otherOrder.getCustomer().equals(getCustomer())
                && otherOrder.getDate().equals(getDate())
                && otherOrder.getAmount().equals(getAmount());

    }
}
