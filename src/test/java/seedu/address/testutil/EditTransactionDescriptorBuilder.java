package seedu.address.testutil;

import seedu.address.logic.commands.EditTransactionCommand.EditTransactionDescriptor;
import seedu.address.model.Timestamp;
import seedu.address.model.transaction.Billing;
import seedu.address.model.transaction.Transaction;

/**
 * A utility class to help with building EditTransactionDescriptor objects.
 */
public class EditTransactionDescriptorBuilder {

    private EditTransactionDescriptor descriptor;

    public EditTransactionDescriptorBuilder() {
        descriptor = new EditTransactionDescriptor();
    }

    public EditTransactionDescriptorBuilder(EditTransactionDescriptor descriptor) {
        this.descriptor = new EditTransactionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTransactionDescriptor} with fields containing {@code transaction}'s details
     */
    public EditTransactionDescriptorBuilder(Transaction transaction) {
        descriptor = new EditTransactionDescriptor();
        descriptor.setTimestamp(transaction.getTimestamp());
        descriptor.setBilling(transaction.getBilling());
    }

    /**
     * Sets the {@code Timestamp} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withTimestamp(String timestamp) {
        descriptor.setTimestamp(new Timestamp(timestamp));
        return this;
    }

    /**
     * Sets the {@code Billing} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withBilling(String billing) {
        descriptor.setBilling(new Billing(billing));
        return this;
    }

    public EditTransactionDescriptor build() {
        return descriptor;
    }
}
