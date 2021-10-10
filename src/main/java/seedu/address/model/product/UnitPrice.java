package seedu.address.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Product's unit price in Sellah.
 */
public class UnitPrice {
    public static final String MESSAGE_CONSTRAINTS =
            "Unit prices should only contain numbers and optionally '.' for cents,"
                    + "and it should not be blank";

    /**
     * The unit price should contain digits from 0 to 9 only, and optionally '.' for cents.
     */
    public static final String VALIDATION_REGEX = "[0-9]+(\\.[0-9]{2})?";

    public final String unitPrice;

    /**
     * Constructs a {@code UnitPrice}.
     *
     * @param unitPrice A valid unitPrice.
     */
    public UnitPrice(String unitPrice) {
        requireNonNull(unitPrice);
        checkArgument(isValidUnitPrice(unitPrice), MESSAGE_CONSTRAINTS);
        this.unitPrice = unitPrice;
    }

    /**
     * Returns true if a given string is a valid unit price.
     *
     * @param unitPrice Unit price to be tested.
     */
    public static boolean isValidUnitPrice(String unitPrice) {
        return unitPrice.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return unitPrice;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnitPrice // instanceof handles nulls
                && unitPrice.equals(((UnitPrice) other).unitPrice)); // state check
    }

    @Override
    public int hashCode() {
        return unitPrice.hashCode();
    }
}
