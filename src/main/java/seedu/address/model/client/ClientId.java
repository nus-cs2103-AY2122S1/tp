package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.StringUtil.removeLeadingZeros;

public class ClientId extends NumberComparable<ClientId> {

    public static final String VALIDATION_REGEX = "\\d+";
    public static final String MESSAGE_CONSTRAINTS = "ClientId should only contain non-negative integers";
    public final String value;

    /**
     * Constructs an {@code ClientId}.
     *
     * @param clientId ClientId
     */
    public ClientId(String clientId) {
        requireNonNull(clientId);
        checkArgument(isValidClientId(clientId), MESSAGE_CONSTRAINTS);
        this.value = removeLeadingZeros(clientId);
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidClientId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ClientId // instanceof handles nulls
            && value.equals(((ClientId) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
