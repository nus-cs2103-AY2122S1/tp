package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.util.LessonUtil.formattedValue;

import java.util.Objects;
/**
 * Represents the Outstanding Fees for the lesson in the address book.
 */
public class OutstandingFees {

    public static final String MESSAGE_CONSTRAINTS =
            "Outstanding Fees should be formatted with a decimal point '.' "
                    + "as a separator between the dollars and cents, "
                    + "and adhere to the following constraints:\n"
                    + "1. Outstanding Fees should only contain numbers and at most one decimal point.\n"
                    + "2. Outstanding Fees should not start or end with a decimal point"
                    + " and should have at most two decimal places.";

    public static final String VALIDATION_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";
    public final String value;

    /**
     * Constructs a {@code OutstandingFees}.
     * This constructor is called when users are modifying lesson.
     *
     * @param outstandingFees Valid Outstanding Fees.
     */
    public OutstandingFees(String outstandingFees) {
        requireNonNull(outstandingFees);
        checkArgument(isValidOutstandingFee(outstandingFees));
        value = formattedValue(fillEmptyString(outstandingFees));
    }

    private String fillEmptyString(String outstandingFee) {
        return outstandingFee.isEmpty() ? "0.00" : outstandingFee;
    }

    /**
     * Returns true if outstandingFee value is empty.
     *
     * @return True if outstandingFee value is empty.
     */
    public boolean isEmpty() {
        return value.isEmpty();
    }

    /**
     * Returns true if a given string is a valid fee.
     */
    public static boolean isValidOutstandingFee(String test) {
        return test.isEmpty() || test.matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OutstandingFees // instanceof handles nulls
                && value.equals(((OutstandingFees) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


}
