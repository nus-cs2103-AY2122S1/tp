package seedu.modulink.model.person;

import static seedu.modulink.commons.util.AppUtil.checkArgument;

public class TelegramHandle {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram handle can only have alphanumeric and hyphen characters, and should not contain any spaces. "
                    + "The handle can either start with '@', or just the handle itself.";
    public static final String VALIDATION_REGEX = "^[A-Za-z0-9_@][A-Za-z0-9_]+$";
    public final String value;

    /**
     * Constructs an {@code TelegramHandle}.
     *
     * @param handle A valid Telegram handle.
     */
    public TelegramHandle(String handle) {
        checkArgument(isValidHandle(handle), MESSAGE_CONSTRAINTS);

        // for JSON file input
        if (handle != null && handle.startsWith("@")) {
            handle = handle.substring(1);
        }

        value = handle;
    }

    /**
     * Returns true if a given string is a valid Telegram handle.
     */
    public static boolean isValidHandle(String test) {
        if (test == null) {
            return true;
        } else {
            return test.matches(VALIDATION_REGEX);
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TelegramHandle // instanceof handles nulls
                && value.equals(((TelegramHandle) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isNull() {
        return value == null;
    }
}
