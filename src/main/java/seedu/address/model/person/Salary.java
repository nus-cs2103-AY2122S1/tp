package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSalary(String)}
 */
public class Salary implements Field {

    public static final String MESSAGE_CONSTRAINTS =
            "Salaries have to be a non-negative integer representing the pay in dollars. Cents can be added by "
            + "adding one or two number digits after a \".\" The salary cannot exceed $9999999.99 per hour.";

    public final Integer value; //

    /**
     * Constructs an {@code Salary}.
     *
     * @param salary A valid salary in cents.
     */
    public Salary(String salary) {
        requireNonNull(salary);
        checkArgument(isValidSalary(salary), MESSAGE_CONSTRAINTS);
        if (salary.contains(".")) {
            String[] salaryStringSplit = salary.split("\\.");
            String centsString = salaryStringSplit[1];
            if (centsString.length() == 1) {
                centsString += "0";
            } else if (centsString.length() > 2) {
                centsString = centsString.substring(0, 2);
            }
            value = Integer.parseInt(salaryStringSplit[0]) * 100 + Integer.parseInt(centsString);
        } else {
            value = Integer.parseInt(salary) * 100;
        }
    }

    /**
     * Returns if a given string is a valid salary.
     */
    public static boolean isValidSalary(String test) {
        test = test.trim();
        if (test.contains(".")) {
            String[] testStringSplit = test.split("\\.");
            if (testStringSplit.length != 2) { // multiple "." or empty field for dollars or cents
                return false;
            }
            return isValidDollars(testStringSplit[0]) && isValidCents(testStringSplit[1]);
        } else {
            return isValidDollars(test);
        }
    }

    /**
     * Returns if a given string represents a valid dollar value.
     */
    public static boolean isValidDollars(String test) {
        test = test.trim();
        if (!test.matches("\\d+") || test.equals("")) {
            return false;
        }

        int dollarInt;
        try {
            dollarInt = Integer.parseInt(test);
        // This exception will be caught if the integer exceeds max integer
        } catch (NumberFormatException e) {
            return false;
        }

        if (dollarInt > 9999999) {
            return false;
        }
        return dollarInt >= 0;
    }

    /**
     * Returns if a given string represents a valid cents value.
     */
    public static boolean isValidCents(String test) {
        test = test.trim();
        if (!test.matches("\\d+") || test.equals("") || test.length() > 2) {
            return false;
        }
        int dollarInt;
        try {
            dollarInt = Integer.parseInt(test);
            // This exception will be caught if the integer exceeds max integer
        } catch (NumberFormatException e) {
            return false;
        }
        return dollarInt >= 0;
    }

    @Override
    public String toString() {
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
        return (dollars + "." + centsString);
    }

    /**
     * Returns a String representation of the value of this Salary object in dollars.
     */
    public String convertToDollars() {
        return ("$" + toString());
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
