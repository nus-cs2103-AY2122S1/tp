package seedu.address.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class LastMet {
    public final LocalDate value;
    public final String dateInString;

    public static final String MESSAGE_CONSTRAINTS = "LastMet should be in the form of Day-Month-Year, "
            + "where Day, month and year should be numerical values.";
    public static final String VALIDATION_REGEX = "(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)";

    /**
     * Constructs an {@code Email}.
     *
     * @param lastMetDate date agent last meets a client
     */
    public LastMet(String lastMetDate) {
        requireNonNull(lastMetDate);
        checkArgument(isValidLastMet(lastMetDate), MESSAGE_CONSTRAINTS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.dateInString = lastMetDate;
        this.value = LocalDate.parse(lastMetDate, formatter);
    }


    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidLastMet(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return dateInString;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LastMet // instanceof handles nulls
                && dateInString.equals(((LastMet) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
