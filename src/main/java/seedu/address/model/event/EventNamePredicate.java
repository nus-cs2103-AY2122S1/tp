package seedu.address.model.event;

import java.util.function.Predicate;

/**
 * Tests that a {@code Event}'s {@code Name} matches a given name.
 */
public class EventNamePredicate implements Predicate<Event> {
    private final String eventName;

    public EventNamePredicate(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public boolean test(Event event) {
        return eventName.equals(event.getNameString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.event.EventNamePredicate // instanceof handles nulls
                && eventName.equals(((seedu.address.model.event.EventNamePredicate) other).eventName)); // state check
    }

}
