package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSalary(String)}
 */
public class Salary implements Field {

    public static final String MESSAGE_CONSTRAINTS = "Salaries have to be a positive integer representing the pay in cents.";

    public final Integer value;

    /**
     * Constructs an {@code Salary}.
     *
     * @param salary A valid salary in cents.
     */
    public Salary(String salary) {
        requireNonNull(salary);
        checkArgument(isValidSalary(salary), MESSAGE_CONSTRAINTS);
        value = Integer.parseInt(salary);
    }

    /**
     * Returns if a given string is a valid salary.
     */
    public static boolean isValidSalary(String test) {
        return test.matches("\\d+");
    }

    @Override
    public String toString() {
        return value.toString();
    }

    /**
     * Returns a String representation of the value of this Salary object in dollars.
     */
    public String convertToDollars() {
        int dollars = value / 100;
        int cents = value % 100;
        String centsString;
        if (cents == 0) {
            centsString = "00";
        } else if (cents < 10) {
            centsString = "0" + cents;
        } else {
            centsString = String.valueOf(cents);
        }
        return ("$" + dollars + "." + centsString);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Salary // instanceof handles nulls
                && value.equals(((Salary) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
