package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Info {

    public static final String MESSAGE_CONSTRAINTS =
            "Important information is limited to 100 characters "
                    + "and should only contain alphanumeric "
                    + "characters with spaces";

    /*
     * Checks if information keyed in only contains alphanumeric
     * characters with spaces
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9 ]{0,100}$";

    public final String value;

    /**
     * Constructs a {@code Info}.
     *
     * @param info valid information.
     */
    public Info(String info) {
        requireNonNull(info);
        checkArgument(isValidInfo(info), MESSAGE_CONSTRAINTS);
        value = info;
    }

    /**
     * Returns true if a given string has valid information.
     */
    public static boolean isValidInfo(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Info // instanceof handles nulls
                && value.equals(((Info) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}


