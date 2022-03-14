package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEventName(String)}
 */
public class EventName implements Comparable<EventName> {
    public static final String MESSAGE_CONSTRAINTS =
            "EventNames should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String eventName;

    /**
     * Constructs an {@code EventName}.
     *
     * @param name A valid name.
     */
    public EventName(String name) {
        requireNonNull(name);
        checkArgument(isValidEventName(name), MESSAGE_CONSTRAINTS);
        eventName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     *
     * @param test A String that represents a name to be tested.
     * @return A boolean indicating if the String is a valid name.
     */
    public static boolean isValidEventName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return eventName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EventName
                && eventName.equals(((EventName) other).eventName));
    }

    @Override
    public int hashCode() {
        return eventName.hashCode();
    }

    @Override
    public int compareTo(EventName o) {
        return eventName.compareTo(o.eventName);
    }
}
