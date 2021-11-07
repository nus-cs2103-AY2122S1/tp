package seedu.address.model.order;

import seedu.address.model.Date;
import seedu.address.model.Label;

public class Order implements Comparable<Order> {

    public static final String ID_PREFIX = "SO";
    private static long count = 1;

    private final Customer customer;
    private long id;
    private Amount amount;
    private Date date;
    private Label label;
    private boolean isComplete;

    /**
     * Constructor creates an Order related to the customer, due on the given date, with the given amount.
     * isComplete flag is set to False initially, and the id is automatically assigned.
     */
    public Order(Label label, Customer customer, Date date, Amount amount) {
        this.customer = customer;
        this.date = date;
        this.amount = amount;
        this.label = label;
        this.isComplete = false;
    }

    /**
     * Confirms the id when the application is sure the order is valid.
     */
    public void confirmId() {
        this.id = count;
        count++;
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }

    /**
     * Marks an order as completed by setting isComplete to true.
     * @return boolean indicating whether isComplete has been changed or not.
     */
    public boolean markCompleted() {
        if (this.isComplete == true) {
            return false;
        } else {
            this.isComplete = true;
            return true;
        }
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

    public Label getLabel() {
        return this.label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static void setCount(long newCount) {
        count = newCount;
    }

    /**
     * Returns the prefixed order id as a String.
     *
     * @return Prefixed order id
     */
    public String getDisplayId() {
        return ID_PREFIX + this.id;
    }

    /**
     * Returns the amount as a double.
     *
     * @return the double representation of the amount.
     */
    public double getAmountAsDouble() {
        return Double.parseDouble(amount.toString());
    }

    /**
     * Returns true if both orders have the same customer, label and amount.
     * This defines a weaker notion of equality between two orders.
     */
    public boolean isSameOrder(Order otherOrder) {
        if (otherOrder == this) {
            return true;
        }

        return otherOrder != null
                && otherOrder.getCustomer().equals(getCustomer())
                && otherOrder.getAmount().equals(getAmount())
                && otherOrder.getLabel().equals(getLabel());
    }

    // Order string representation is temporary, change as necessary for UI.
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (isComplete) {
            builder.append("[X] ");
        } else {
            builder.append("[ ] ");
        }
        builder.append("ID: " + ID_PREFIX)
                .append(getId())
                .append("; Label: ")
                .append(getLabel())
                .append("; Customer: ")
                .append(getCustomer())
                .append("; Amount: ")
                .append(getAmount())
                .append("; Date: ")
                .append(getDate());

        return builder.toString();
    }

    // Required for UniqueOrderList to check if an Order exists, before marking it.
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
                && otherOrder.getAmount().equals(getAmount())
                && otherOrder.getId() == getId()
                && otherOrder.getLabel().equals(getLabel());

    }

    @Override
    public int compareTo(Order o) {
        //Orders are compared using their id.
        return Long.compare(this.id, o.id);
    }
}
