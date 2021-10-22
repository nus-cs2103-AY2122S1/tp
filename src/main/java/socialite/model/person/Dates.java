package socialite.model.person;

import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Dates {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be formatted as NAME:YYYY-MM-DD";

    public static final String VALIDATION_REGEX = "([\\w\\s]+:\\d{4}-\\d{2}-\\d{2})(\\s+[\\w\\s]+:\\d{4}-\\d{2}-\\d{2})*";


    public final HashMap<String, Date> value;

    public Dates() {
        value = new HashMap<>();
    }

    public Dates(String dates) {
        Pattern pattern = Pattern.compile("([\\w\\s]+):(\\d{4})-(\\d{2})-(\\d{2})\\s*");
        Matcher matcher = pattern.matcher(dates);

        value = new HashMap<>();

        while (matcher.find()) {
            String name = matcher.group(1);
            int year = Integer.parseInt(matcher.group(2));
            int month = Integer.parseInt(matcher.group(3));
            int day = Integer.parseInt(matcher.group(4));
            // TODO(@bnjmnt4n): use non-deprecated date implementation
            value.put(name, new Date(year - 1900, month - 1, day - 1));
        }
    }

    public Dates(HashMap<String, Date> dates) {
        value = new HashMap<>(dates);
    }

    public Dates(Dates originalDates) {
        if (originalDates == null) {
            value = new HashMap<>();
        } else {
            value = new HashMap<>(originalDates.value);
        }
    }

    /**
     * Returns true if a given string is a valid sequence of dates.
     */
    public static boolean isValidDatesSequence(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value.entrySet()
                .stream()
                .map((item) -> {
                    return item.getKey() + ": " + item.getValue();
                })
                .collect(Collectors.joining());
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
