package seedu.fast.model.person;

import static java.util.Objects.requireNonNull;

/**
 *
 * Represents a Person's remark in the address book.
 *  Guarantees: immutable; is always valid
 */
public class Remark {
    public static final int MAX_LENGTH_REMARK = 45;
    public static final String MESSAGE_CONSTRAINTS = "Remarks have a character limit of 45 characters.";

    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        this.value = remark;
    }


    public static boolean isValidRemark(String test) {
        return test.length() <= MAX_LENGTH_REMARK;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
