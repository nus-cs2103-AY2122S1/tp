package seedu.address.model.person.supplier;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class DeliveryDetails {

    public static final String MESSAGE_CONSTRAINTS =
            "Delivery details should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String deliveryDetails;

    /**
     * Constructs a {@code Name}.
     *
     * @param deliveryDetails A valid delivery detail.
     */
    public DeliveryDetails(String deliveryDetails) {
        requireNonNull(deliveryDetails);
        checkArgument(isValidDeliveryDetail(deliveryDetails), MESSAGE_CONSTRAINTS);
        this.deliveryDetails = deliveryDetails;
    }

    /**
     * Returns true if a given string is a valid delivery detail.
     */
    public static boolean isValidDeliveryDetail(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return this.deliveryDetails;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeliveryDetails // instanceof handles nulls
                && this.deliveryDetails.equals(((DeliveryDetails) other).deliveryDetails)); // state check
    }

    @Override
    public int hashCode() {
        return this.deliveryDetails.hashCode();
    }
}
