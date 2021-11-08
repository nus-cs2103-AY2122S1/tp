package seedu.address.model.event;

import java.util.function.Predicate;

/**
 * Tests that an {@code Event} matches a given {@code Event}
 */
public class IsEventPredicate implements Predicate<Event> {
    private final Event event;

    /**
     * Constructs a {@code Event}.
     *
     * @param event A valid event.
     */
    public IsEventPredicate(Event event) {
        this.event = event;
    }

    @Override
    public boolean test(Event event) {
        return event.equals(this.event);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsEventPredicate // instanceof handles nulls
                && event.equals(((IsEventPredicate) other).event)); // state check
    }

}
