package seedu.address.testutil;

import seedu.address.model.Timestamp;
import seedu.address.model.transaction.Billing;
import seedu.address.model.transaction.Id;
import seedu.address.model.transaction.Transaction;

/**
 * A utility class to help with building Transaction objects.
 */
public class TransactionBuilder {

    public static final String DEFAULT_ID = "000101";
    public static final String DEFAULT_TIMESTAMP = "1611136800000";
    public static final String DEFAULT_BILLING = "345.67";

    private Id id;
    private Timestamp timestamp;
    private Billing billing;

    /**
     * Creates a {@code TransactionBuilder} with the default details.
     */
    public TransactionBuilder() {
        id = new Id(DEFAULT_ID);
        timestamp = new Timestamp(DEFAULT_TIMESTAMP);
        billing = new Billing(DEFAULT_BILLING);
    }

    /**
     * Initializes the TransactionBuilder with the data of {@code transactionToCopy}.
     */
    public TransactionBuilder(Transaction transactionToCopy) {
        id = transactionToCopy.getId();
        timestamp = transactionToCopy.getTimestamp();
        billing = transactionToCopy.getBilling();
    }

    /**
     * Sets the {@code Id} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Sets the {@code Timestamp} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withTimestamp(String timestamp) {
        this.timestamp = new Timestamp(timestamp);
        return this;
    }

    /**
     * Sets the {@code Billing} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withBilling(String billing) {
        this.billing = new Billing(billing);
        return this;
    }

    public Transaction build() {
        return new Transaction(id, timestamp, billing);
    }

}
