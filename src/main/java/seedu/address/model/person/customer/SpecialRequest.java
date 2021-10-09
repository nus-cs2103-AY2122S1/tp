package seedu.address.model.person.customer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class SpecialRequest {

    public static final String MESSAGE_CONSTRAINTS =
            "Special requests can take any values, and can be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String requestDetails;

    /**
     * Constructs an {@code SpecialRequest}.
     *
     * @param requestDetails A valid requestDetail.
     */
    public SpecialRequest(String requestDetails) {
        requireNonNull(requestDetails);
        checkArgument(isValidSpecialRequest(requestDetails), MESSAGE_CONSTRAINTS);
        this.requestDetails = requestDetails;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidSpecialRequest(String requestDetails) {
        return requestDetails.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return requestDetails;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SpecialRequest // instanceof handles nulls
                && requestDetails.equals(((SpecialRequest) other).requestDetails)); // state check
    }

    @Override
    public int hashCode() {
        return requestDetails.hashCode();
    }

}
