package seedu.insurancepal.model.person;

import static java.util.Objects.requireNonNull;

import seedu.insurancepal.commons.core.Money;

/**
 * Represents a Person's revenue in the address book.
 * Guarantees: immutable; is always valid
 */
public class Revenue {

    public static final String MESSAGE_CONSTRAINTS = "Revenue can be any positive number with up to 2 decimal places "
            + "that is not more than 20,000,000";

    public static final String MESSAGE_INVALID_REVENUE_PLUS_SIGN = "There is no need to add a plus sign in front"
            + " of revenue, it is positive by default until a minus sign is placed in front of the number.";

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
     * Returns true if the resulting Revenue value is valid.
     */
    public boolean isValidResultingRevenue() {
        return !this.value.isNegative();
    }

    /**
     * Returns true if the resulting Revenue value is more than 20,000,000.
     */
    public boolean isMoreThanMaxRevenue() {
        return this.value.isMoreThan(20000000);
    }

    /**
     * Returns true if a given string is a valid revenue.
     */
    public static boolean isValidRevenue(String test) {
        return Money.isValidMoney(test);
    }

    public static boolean isPlusSignPresent(String test) {
        return Money.isPlusSignPresent(test);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    public String stringInputByUser() {
        return this.value.stringInputByUser();
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
        return new Revenue(this.value.addValue(revenueToBeAdded.value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
