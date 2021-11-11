package nustracker.model.event;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's name.
 */
public class EventName {

    public static final String MESSAGE_CONSTRAINTS =
            "EventNames should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the event name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String eventName;

    /**
     * Constructs an {@code EventName}.
     *
     * @param name A valid name.
     */
    public EventName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        eventName = name;
    }

    public String getEventName() {
        return eventName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventName // instanceof handles nulls
                && eventName.toLowerCase().equals(((EventName) other).eventName.toLowerCase())); // state check
    }

    @Override
    public String toString() {
        return eventName;
    }

    @Override
    public int hashCode() {
        return eventName.hashCode();
    }
}
