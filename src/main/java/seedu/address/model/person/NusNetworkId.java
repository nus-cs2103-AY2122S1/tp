package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's NusNetworkId in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNusNetworkId(String)}
 */
public class NusNetworkId {

    public static final String MESSAGE_CONSTRAINTS = "NUS Network ID must be valid, and it should not be blank";

    /*
     * Regex for the NUS Network ID.
     */
    public static final String VALIDATION_REGEX = "[eE][0-9]{7}";

    public final String value;

    /**
     * Constructs an {@code NusNetworkId}.
     *
     * @param nusNetworkId A valid NUS Network ID.
     */
    public NusNetworkId(String nusNetworkId) {
        requireNonNull(nusNetworkId);
        checkArgument(isValidNusNetworkId(nusNetworkId), MESSAGE_CONSTRAINTS);
        value = nusNetworkId.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid NUS Network ID.
     */
    public static boolean isValidNusNetworkId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof NusNetworkId)) {
            return false;
        }

        NusNetworkId otherNusNetworkId = (NusNetworkId) other;

        return value.equals(otherNusNetworkId.value); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
