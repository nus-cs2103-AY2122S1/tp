package safeforhall.model.person;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class LastDate implements Comparable<LastDate> {

    public static final String MESSAGE_CONSTRAINTS = "Date inputted has to be in dd-mm-yyyy, dd.mm.yyyy"
            + "or dd/mm/yyyy format";
    public static final String DEFAULT_DATE = "None";
    public static final String FET_DESC = "Last FET: ";
    public static final String COLLECTION_DESC = "Last Collection: ";
    public static final String FET_FIELD = "fd";
    public static final String COLLECTION_FIELD = "cd";


    private static final DateTimeFormatter dateFormatter = DateTimeFormatter
            .ofPattern("[dd-MM-yyyy][dd.MM.yyyy][dd/MM/yyyy]");

    private static final int LASTDATE_DEADLINE = 1;

    public final String date;

    /**
     * Constructs a {@code LastDate}.
     *
     * @param date A valid date.
     */
    public LastDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        if (date.equals(DEFAULT_DATE)) {
            this.date = date;
        } else {
            String[] dayMonthYear = date.split("[-./]");
            assert dayMonthYear.length == 3;
            this.date = dayMonthYear[0] + "-" + dayMonthYear[1] + "-" + dayMonthYear[2];
        }

    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String date) {
        if (date.equals(DEFAULT_DATE)) {
            return true;
        }
        try {
            LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Converts the given {@code LastDate} to a {@code LocalDate}.
     */
    public LocalDate toLocalDate() {
        return date.equals(DEFAULT_DATE)
                ? LocalDate.now()
                : LocalDate.parse(date, dateFormatter);
    }

    /**
     * Adds the period of validity to the given {@code LastDate} to get the next deadline.
     */
    public LocalDate getDeadline() {
        return date.equals(DEFAULT_DATE)
                ? LocalDate.now()
                : LocalDate.parse(date, dateFormatter).plusWeeks(LASTDATE_DEADLINE);
    }

    @Override
    public String toString() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LastDate // instanceof handles nulls
                && date.equals(((LastDate) other).date)); // state check
    }

    @Override
    public int compareTo(LastDate ld) {
        return this.toLocalDate().compareTo(ld.toLocalDate());
    }
}
