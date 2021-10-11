package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Money;

/**
 * Represents a Person's revenue in the address book.
 * Guarantees: immutable; is always valid
 */
public class Revenue {

    public static final String MESSAGE_CONSTRAINTS = "Revenue can take any values, and it should not be negative";

    public final Money value;

    /**
     * Constructs an {@code Address}.
     *
     * @param revenue A valid address.
     */
    public Revenue(Money revenue) {
        requireNonNull(revenue);
        value = revenue;
    }

    /**
     * Returns true if the resulting Revenue value is a valid.
     */
    public boolean isValidResultingRevenue() {
        return this.value.getInDollars() >= 0;
    }

    /**
     * Returns true if a given string is a valid revenue.
     */
    public static boolean isValidRevenue(String test) {
        try {
            float number = Float.valueOf(test);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Revenue // instanceof handles nulls
                && value.equals(((Revenue) other).value)); // state check
    }

    /**
     * Adds a revenue value to the current revenue.
     *
     * @param revenueToBeAdded Revenue value to be added.
     * @return A new revenue object with its value the result of the 2 added revenues.
     */
    public Revenue addRevenue(Revenue revenueToBeAdded) {
        int updatedValue = this.value.getCents() + revenueToBeAdded.value.getCents();
        float updatedValueInDollars = updatedValue / 100f;
        Revenue revenue = new Revenue(new Money(updatedValueInDollars));
        return revenue;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
