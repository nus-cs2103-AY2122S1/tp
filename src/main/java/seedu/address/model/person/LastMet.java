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
    public static final String VALIDATION_REGEX = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    public static final String ALTERNATIVE_VALIDATION_REGEX = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    /**
     * Constructs an {@code Email}.
     *
     * @param lastMetDate date agent last meets a client
     */
    public LastMet(String lastMetDate) {
        this.dateInString = lastMetDate;
        checkArgument(isValidLastMet(lastMetDate), MESSAGE_CONSTRAINTS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.value = LocalDate.parse(lastMetDate, formatter);
    }


    /**
     * Returns if a given string is a valid LastMet.
     */
    public static boolean isValidLastMet(String test) {
        return test.matches(ALTERNATIVE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.dateInString;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LastMet // instanceof handles nulls
                && value.equals(((LastMet) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
