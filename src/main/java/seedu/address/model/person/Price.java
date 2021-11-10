package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Price {

    public static final String MESSAGE_CONSTRAINTS =
            "Prices should be given to 2 decimal places"
                    + " and be numerical inputs (lesser than 1000000000000000.00) only";

    public static final String VALIDATION_REGEX = "\\d{1,}" + "." + "\\d{2}";

    public final String value;

    /**
     * Constructs an {@code Price}.
     *
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        value = price;
    }

    /**
     * Returns true if a given string has a valid price.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX) && (Double.parseDouble(test)) <= 1000000000000000.00;
    }

    /**
     * Returns true if current price is greater than parameter price
     *
     * @param price Other price to be compared
     * @return Whether current price is greater than other price
     */
    public boolean greaterThan(Price price) {
        double currentPrice = Double.parseDouble(value);
        double otherPrice = Double.parseDouble(price.value);
        return currentPrice > otherPrice;
    }

    public double toDouble() {
        return Double.parseDouble(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price // instanceof handles nulls
                && value.equals(((Price) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


}
