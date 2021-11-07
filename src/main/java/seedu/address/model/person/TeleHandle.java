package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Person's Telegram handle in contHACKS.
 * Guarantees: immutable; is valid as declared in {@link #isValidTeleHandle(String)}
 */
public class TeleHandle {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram handles should begin with a '@', contain alphanumeric characters and underscores,"
                    + " and should be at least 5 to 32 characters long with no spaces";

    public static final String VALIDATION_REGEX = "@[\\p{Alnum}_]{5,32}";
    public final String value;

    /**
     * Constructs a {@code TeleHandle}.
     *
     * @param teleHandle A valid Telegram handle.
     */
    public TeleHandle(String teleHandle) {
        requireNonNull(teleHandle);
        checkArgument(isValidTeleHandle(teleHandle), MESSAGE_CONSTRAINTS);
        value = teleHandle;
    }

    /**
     * Returns true if a given string is a valid Telegram handle.
     *
     * @param test The given string.
     * @return True if the given string is a valid Telegram handle, false otherwise.
     */
    public static boolean isValidTeleHandle(String test) {
        return test.equals("") || test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the other Telegram handle is similar to this Telegram handle.
     */
    public boolean isSimilarTo(TeleHandle other) {
        if (other == null || other.value.equals("")) {
            return false;
        }

        return value.equalsIgnoreCase(other.value);
    }
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TeleHandle
                && value.equalsIgnoreCase(((TeleHandle) other).value));
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
