package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents frequency of visits for each person.
 */
public enum Frequency {
    DAILY("daily"),
    WEEKLY("weekly"),
    BIWEEKLY("biweekly"),
    MONTHLY("monthly"),
    QUARTERLY("quarterly");

    public static final String MESSAGE_CONSTRAINTS =
            "Frequency can take be daily, weekly, biweekly, monthly or quarterly. Enter the exact frequency.";

    private final String value;

    Frequency(String value) {
        this.value = value;
    }

    /**
     * Returns if a given string is a valid frequency.
     */
    public static boolean isValidFrequency(String value) {
        requireNonNull(value);
        for (Frequency f : Frequency.values()) {
            if (value.toLowerCase().equals(f.value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the matching Frequency of a string.
     *
     * @param value The string of frequency.
     * @return The corresponding Frequency to the string.
     */
    public static Frequency find(String value) {
        checkArgument(isValidFrequency(value), MESSAGE_CONSTRAINTS);

        for (Frequency f : Frequency.values()) {
            if (value.toLowerCase().equals(f.value)) {
                return f;
            }
        }
        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return value;
    }
}
