package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's role in the address book.
 */
public class Role {

    public static final String MESSAGE_CONSTRAINTS = "Roles can take a values of student/staff/admin";
    public static final String STUDENT = "student";
    public static final String STAFF = "staff";
    public static final String ADMIN = "admin";

    public final String value;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role.
     */
    public Role(String role) {
        requireNonNull(role);
        checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS);
        value = role;
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String test) {
        // Change to VALIDATION_REGEX in the future.
        return test.equals(STUDENT) || test.equals(STAFF) || test.equals(ADMIN);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Role // instanceof handles nulls
                && value.equals(((Role) other).value)); // state check
    }
}
