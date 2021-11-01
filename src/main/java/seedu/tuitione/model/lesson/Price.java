package seedu.tuitione.model.lesson;

import static java.util.Objects.hash;
import static seedu.tuitione.commons.util.AppUtil.checkArgument;

/**
 * Represents a Price for a Lesson in tuitiONE book.
 */
public class Price {

    public static final double MAX_PRICE = 200.0;
    public static final String CURRENCY = "SGD $";

    public static final String PRICE_MESSAGE_CONSTRAINT = String.format("Price cannot be negative or more than "
            + CURRENCY + " %1$.2f", MAX_PRICE);

    public final double value;

    /**
     * Constructs a {@code Price}.
     *
     * @param price price of lesson.
     */
    public Price(double price) {
        checkArgument(isValidPrice(price), PRICE_MESSAGE_CONSTRAINT);
        this.value = (double) Math.round(price * 100) / 100; // keep only 2 d.p.
    }

    /**
     * Returns true if a given price is between 0 and {@code MAX_PRICE} inclusive.
     */
    public static boolean isValidPrice(double testPrice) {
        return (testPrice >= 0.0) && (testPrice <= MAX_PRICE);
    }

    @Override
    public int hashCode() {
        return hash(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Price)) {
            return false;
        }
        Price otherPrice = (Price) other;
        return value == otherPrice.value;
    }

    @Override
    public String toString() {
        return String.format(CURRENCY + " %1$.2f", value);
    }
}
