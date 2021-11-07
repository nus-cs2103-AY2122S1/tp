package seedu.address.model.module.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's tele handle.
 * Guarantees: immutable; is valid as declared in {@link #isValidTeleHandle(String)}
 */
public class TeleHandle {


    public static final String MESSAGE_CONSTRAINTS = "Tele handle starts with @ and "
            + "is followed by 5-32 alphanumeric characters \n"
            + "or underscore.";
    public static final String VALIDATION_REGEX = "@[a-zA-Z0-9_]{5,32}+$";
    public final String value;

    /**
     * Constructs a {@code TeleHandle}.
     *
     * @param teleHandle A valid teleHandle number.
     */
    public TeleHandle(String teleHandle) {
        requireNonNull(teleHandle);
        checkArgument(isValidTeleHandle(teleHandle), MESSAGE_CONSTRAINTS);
        value = teleHandle;
    }

    /**
     * Returns true if a given string is a valid tele handle.
     *
     * @param test The string that may potentially be a tele handle.
     * @return A boolean stating whether the tele handle is valid.
     */
    public static boolean isValidTeleHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeleHandle // instanceof handles nulls
                && value.equals(((TeleHandle) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
