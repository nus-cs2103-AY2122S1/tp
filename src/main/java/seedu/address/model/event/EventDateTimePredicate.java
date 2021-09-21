package seedu.address.model.event;

import java.util.List;
import java.util.function.Predicate;

public class EventDateTimePredicate implements Predicate<Event> {
    private final List<String> dateTime;

    public EventDateTimePredicate(List<String> dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean test(Event event) {
        EventDate eventDate = new EventDate(dateTime.get(0));
        if (dateTime.size() == 2) { // has date and time
            EventTime eventTime = new EventTime(dateTime.get(1));
            return event.getDate().equals(eventDate) && event.getTime().equals(eventTime);
        } else { // date only
            return event.getDate().equals(eventDate);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EventDateTimePredicate
                && dateTime.equals(((EventDateTimePredicate) other).dateTime));
    }
}
