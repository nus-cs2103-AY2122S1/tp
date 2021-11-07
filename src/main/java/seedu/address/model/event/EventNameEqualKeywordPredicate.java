package seedu.address.model.event;

import java.util.function.Predicate;

/**
 * Tests that an {@code Event}'s {@code EventName} matches the keyword given exactly.
 */
public class EventNameEqualKeywordPredicate implements Predicate<Event> {
    private final EventName keyword;

    /**
     * Creates a EventNameEqualKeywordPredicate object with the keyword.
     *
     * @param keyword The keyword to be used in the predicate.
     */
    public EventNameEqualKeywordPredicate(EventName keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Event event) {
        return keyword.equals(event.getEventName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventNameEqualKeywordPredicate // instanceof handles nulls
                && keyword.equals(((EventNameEqualKeywordPredicate) other).keyword)); // state check
    }

    @Override
    public String toString() {
        return this.keyword.toString();
    }

}
