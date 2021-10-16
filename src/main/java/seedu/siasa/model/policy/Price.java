package seedu.siasa.model.policy;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.commons.util.AppUtil.checkArgument;

public class Price {
    public static final String MESSAGE_CONSTRAINTS =
        "Price in cents should be a non-negative integer.";


    public final int priceInCents;

    /**
     * Constructs an {@code Price}.
     *
     * @param priceInCents A valid price in cents.
     */
    public Price(int priceInCents) {
        requireNonNull(priceInCents);
        checkArgument(isValidPrice(priceInCents), MESSAGE_CONSTRAINTS);
        this.priceInCents = priceInCents;
    }

    public static boolean isValidPrice(int price) {
        return price >= 0;
    }

    @Override
    public String toString() {
        int cents = priceInCents % 100;
        int dollars = (priceInCents - cents);

        String centsStr;
        if (cents <= 9) {
            centsStr = 0 + "" + cents;
        } else {
            centsStr = Integer.toString(cents);
        }

        return "$" + dollars + "." + centsStr;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof seedu.siasa.model.policy.Price // instanceof handles nulls
            && priceInCents == ((seedu.siasa.model.policy.Price) other).priceInCents); // state check
    }

    @Override
    public int hashCode() {
        return priceInCents;
    }
}
