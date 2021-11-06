package tutoraid.model.lesson;

import static java.util.Objects.requireNonNull;
import static tutoraid.commons.util.AppUtil.checkArgument;

import java.text.DecimalFormat;

/**
 * Represents a Lesson's price in TutorAid.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {

    public static final String MESSAGE_CONSTRAINTS =
            "Price should be non-negative and at least one digit long. "
                    + "It may contain dollars only or both dollars and cents.";
    public static final String VALIDATION_REGEX = "\\d+(\\.\\d\\d)?";
    public final String price;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        if (!price.equals("")) {
            checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        }
        this.price = price;
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Takes in a double in the form of a String and returns the same string with comma grouping and $ sign.
     *
     * @return Formatted string with $ symbol and comma between groups of 3 digits
     */
    public String formatPrice() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
        return String.format("$%s", decimalFormat.format(Double.valueOf(price)));
    }

    @Override
    public String toString() {
        if (price.equals("")) {
            return "No price";
        }
        return price;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price // instanceof handles nulls
                && price.equals(((Price) other).price)); // state check
    }

    @Override
    public int hashCode() {
        return price.hashCode();
    }
}
