package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.commons.util.DateTimeUtil;

/**
 * Represents frequency of visits for each person.
 */
public enum Frequency {
    EMPTY(""),
    DAILY("daily"),
    WEEKLY("weekly"),
    BIWEEKLY("biweekly"),
    MONTHLY("monthly"),
    QUARTERLY("quarterly");

    public static final String MESSAGE_CONSTRAINTS =
            "Frequency should be either daily, weekly, biweekly, monthly or quarterly.";

    public final String value;

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
     * Check if the frequency is empty.
     * @return true if the frequency is empty, otherwise false.
     */
    public boolean isEmpty() {
        return Objects.equals(this.value, "");
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

    /**
     * Finds the next visit with the frequency
     *
     * @param visit The current visit.
     * @return The next visit with the current visit and frequency.
     */
    public Visit nextVisit(Visit visit) {
        String currentStringVisit = visit.value;
        LocalDateTime currentDateTime = LocalDateTime.parse(currentStringVisit, DateTimeUtil.FORMATTER);

        LocalDateTime nextDateTime = null;
        switch (this) {
        case DAILY:
            nextDateTime = currentDateTime.plusDays(1);
            break;
        case WEEKLY:
            nextDateTime = currentDateTime.plusWeeks(1);
            break;
        case BIWEEKLY:
            nextDateTime = currentDateTime.plusWeeks(2);
            break;
        case MONTHLY:
            nextDateTime = currentDateTime.plusMonths(1);
            break;
        case QUARTERLY:
            nextDateTime = currentDateTime.plusMonths(3);
            break;
        default:
            break;
        }
        if (this == EMPTY) {
            return new Visit("");
        } else {
            String newStringVisit = nextDateTime.format(DateTimeUtil.FORMATTER);
            return new Visit(newStringVisit);
        }

    }

    @Override
    public String toString() {
        return value;
    }
}
