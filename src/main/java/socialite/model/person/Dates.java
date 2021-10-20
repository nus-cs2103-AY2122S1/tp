package socialite.model.person;

import java.util.Date;
import java.util.HashMap;

public class Dates {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be formatted as NAME:YYYY-MM-DD";

    public static final String VALIDATION_REGEX = "([\\w\\s]+:\\d{4}-\\d{2}-\\d{2})(\\s+[\\w\\s]+:\\d{4}-\\d{2}-\\d{2})*";


    public final HashMap<String, Date> value;

    public Dates() {
        value = new HashMap<>();
    }

    // TODO(@bnjmnt4n)
    public Dates(String dates) {
        value = new HashMap<>();
    }

    public Dates(HashMap<String, Date> dates) {
        value = new HashMap<>(dates);
    }

    public Dates(Dates originalDates) {
        value = new HashMap<>(originalDates.value);
    }

    /**
     * Returns true if a given string is a valid sequence of dates.
     */
    public static boolean isValidDatesSequence(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Dates // instanceof handles nulls
                && value.equals(((Dates) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
