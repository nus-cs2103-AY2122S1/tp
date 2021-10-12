package seedu.address.model.person.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Employee's salary in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSalary(String)}
 */
public class Salary {
    public static final String MESSAGE_CONSTRAINTS = "Salary should be numerical and should be 3 or more digits long"
            + ".";

    /*
     * Between 1-6 digits, optional 2 digits after the dot
     */
    public static final String VALIDATION_REGEX = "\\d{3,}";

    public final String currentSalary;

    /**
     * Constructs an {@code Salary}.
     *
     * @param salary A valid salary.
     */
    public Salary(String salary) {
        requireNonNull(salary);
        checkArgument(isValidSalary(salary), MESSAGE_CONSTRAINTS);
        currentSalary = salary;
    }

    /**
     * Returns true if a given string is a valid salary.
     */
    public static boolean isValidSalary(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return currentSalary;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Salary // instanceof handles nulls
                && currentSalary.equals(((Salary) other).currentSalary)); // state check
    }

    @Override
    public int hashCode() {
        return currentSalary.hashCode();
    }
}
