package seedu.insurancepal.model.person;

import static java.util.Objects.requireNonNull;

import seedu.insurancepal.commons.core.Money;

/**
 * Represents a Person's revenue in the address book.
 * Guarantees: immutable; is always valid
 */
public class Revenue {

    public static final String CENTS_VALIDATION_REGEX = "\\d{2}";

    public static final String MESSAGE_CONSTRAINTS = "Revenue can be any positive number with up to 2 decimal places "
            + "that is not more than 19,999,998";

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
        if (this.value.getDollars() > 20000000) {
            return true;
        } else if (this.value.getDollars() == 20000000) {
            return this.value.getCents() > 0;
        } else {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid revenue.
     */
    public static boolean isValidRevenue(String test) {
        try {
            String[] dollarsAndCents = test.split(".", 2);
            int dollars = Integer.valueOf(dollarsAndCents[0]);
            int cents = Integer.valueOf(dollarsAndCents[1]);
            return dollarsAndCents[1].matches(CENTS_VALIDATION_REGEX);
        } catch (NumberFormatException e) {
            return false;
        }
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
        if (revenueToBeAdded.value.isNegative()) {
            return subtractMoney(revenueToBeAdded.value.getDollars(), revenueToBeAdded.value.getCents());
        } else {
            return addMoney(revenueToBeAdded.value.getDollars(), revenueToBeAdded.value.getCents());
        }
    }

    private Revenue subtractMoney(int dollars, int cents) {
        int updatedCents = this.value.getCents() - cents;
        int updatedDollars = this.value.getDollars() - dollars;
        if (updatedDollars < 0) {
            return new Revenue(new Money(0, 0, true));
        } else if (updatedDollars == 0) {
            if (updatedCents < 0) {
                return new Revenue(new Money(0, 0, true));
            } else {
                return new Revenue(new Money(updatedDollars, updatedCents, false));
            }
        } else {
            if (updatedCents < 0) {
                int displayCents = 100 + updatedCents;
                int displayDollars = updatedDollars - 1;
                return new Revenue(new Money(displayDollars, displayCents, false));
            } else {
                return new Revenue(new Money(updatedDollars, updatedCents, false));
            }
        }
    }

    private Revenue addMoney(int dollars, int cents) {
        int updatedCents = this.value.getCents() + cents;
        int updatedDollars = this.value.getDollars() + dollars;
        if (updatedCents >= 100) {
            int displayCents = updatedCents - 100;
            int displayDollars = updatedDollars + 1;
            return new Revenue(new Money(displayDollars, displayCents, false));
        } else {
            return new Revenue(new Money(updatedDollars, updatedCents, false));
        }
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
