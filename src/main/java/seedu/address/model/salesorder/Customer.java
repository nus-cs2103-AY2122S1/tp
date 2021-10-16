package seedu.address.model.salesorder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Customer related to a SalesOrder.
 */
public class Customer {
    public static final String MESSAGE_CONSTRAINTS =
            "Customer name should contain only letters and spaces and be non-empty";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String name;

    /**
     * Constructs a {@code Customer}
     *
     * @param name A valid customer name.
     */
    public Customer(String name) {
        requireNonNull(name);
        checkArgument(isValidAmount(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    public static boolean isValidAmount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Customer
                && name.equals(((Customer) other).name));
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
