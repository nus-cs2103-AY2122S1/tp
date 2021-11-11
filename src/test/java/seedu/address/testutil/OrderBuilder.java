package seedu.address.testutil;

import seedu.address.model.Date;
import seedu.address.model.Label;
import seedu.address.model.order.Amount;
import seedu.address.model.order.Customer;
import seedu.address.model.order.Order;

public class OrderBuilder {

    public static final String DEFAULT_LABEL = "Blue Shirt";
    public static final String DEFAULT_CUSTOMER = "Johnson";
    public static final String DEFAULT_AMOUNT = "99.99";
    public static final String DEFAULT_DATE = "2021-10-20";
    public static final boolean DEFAULT_IS_COMPLETE = false;

    private Label label;
    private Customer customer;
    private Amount amount;
    private Date date;
    private boolean isComplete;
    private long id;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        label = new Label(DEFAULT_LABEL);
        customer = new Customer(DEFAULT_CUSTOMER);
        amount = new Amount(DEFAULT_AMOUNT);
        date = new Date(DEFAULT_DATE);
        isComplete = DEFAULT_IS_COMPLETE;
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        label = orderToCopy.getLabel();
        customer = orderToCopy.getCustomer();
        amount = orderToCopy.getAmount();
        date = orderToCopy.getDate();
        isComplete = orderToCopy.getIsComplete();
    }

    /**
     * Sets the {@code Customer} of the {@code Order} that we are building.
     */
    public OrderBuilder withCustomer(String customer) {
        this.customer = new Customer(customer);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code Order} that we are building.
     */
    public OrderBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Order} that we are building.
     */
    public OrderBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code isComplete} of the {@code Order} that we are building.
     */
    public OrderBuilder withIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
        return this;
    }

    /**
     * Sets the {@code Label} of the {@code Order} that we are building.
     */
    public OrderBuilder withLabel(String label) {
        this.label = new Label(label);
        return this;
    }

    /**
     * Sets the {@code id} of the {@code Order} that we are building.
     */
    public OrderBuilder withId(long id) {
        this.id = id;
        return this;
    }

    /**
     * Builds the {@code Order}.
     */
    public Order build() {
        Order order = new Order(label, customer, date, amount);
        if (this.id != 0) {
            order.setId(this.id);
        }
        if (this.isComplete) {
            order.markCompleted();
        }
        return order;
    }
}
