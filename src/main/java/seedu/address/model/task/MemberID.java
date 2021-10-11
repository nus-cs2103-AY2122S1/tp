package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Member's index number in the address book.
 */
public class MemberID {
    public static final String MESSAGE_CONSTRAINTS =
            "MemberID numbers should only contain numbers, and it should be at least 1 digit long";
    public static final String VALIDATION_REGEX = "\\d{1,}";
    public final String value;

    /**
     * Constructs a {@code MemberID}.
     *
     * @param memberID A valid memberID number.
     */
    public MemberID(String memberID) {
        requireNonNull(memberID);
        checkArgument(isValidMemberID(memberID), MESSAGE_CONSTRAINTS);
        value = memberID;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidMemberID(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MemberID // instanceof handles nulls
                && value.equals(((MemberID) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
