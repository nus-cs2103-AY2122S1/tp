package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Locale;

/**
 * Represents whether a Person is called in the address book.
 */
public class IsCalled {

    public static final String MESSAGE_CONSTRAINTS = "Called has to either be TRUE or FALSE";
    public final boolean value;

    /**
     * Constructs an {@code IsCalled}.
     *
     * @param isCalled Indication whether person is called.
     */
    public IsCalled(String isCalled) {
        requireNonNull(isCalled);
        checkArgument(isValidIsCalled(isCalled), MESSAGE_CONSTRAINTS);
        value = isCalled.toUpperCase(Locale.ROOT).equals("TRUE");
    }

    /**
     * Returns true if a given string is a valid IsCalled.
     */
    public static boolean isValidIsCalled(String test) {
        String testValid = test.toUpperCase(Locale.ROOT);
        return testValid.equals("TRUE") || testValid.equals("FALSE") || testValid.equals("");
    }

    @Override
    public String toString() {
        if (value) {
            return "True";
        }
        return "False";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsCalled // instanceof handles nulls
                && (value == ((IsCalled) other).value)); // state check
    }

    @Override
    public int hashCode() {
        if (value) {
            return 1;
        }
        return 0;
    }
}
