package seedu.address.model.person.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Employee's salary in RHRH.
 * Guarantees: immutable; is valid as declared in {@link #isValidSalary(String)}
 */
public class Salary {
    public static final String MESSAGE_CONSTRAINTS = "Salary should be numerical and more than or equal to 100 and"
            + " less than or equals to 10 million";

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
        try {
            return Integer.parseInt(test) >= 100 && Integer.parseInt(test) <= 10000000;
        } catch (NumberFormatException e) {
            return false;
        }
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
