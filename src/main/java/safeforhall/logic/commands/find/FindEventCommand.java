package safeforhall.logic.commands.find;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import safeforhall.commons.core.Messages;
import safeforhall.commons.util.CollectionUtil;
import safeforhall.logic.commands.Command;
import safeforhall.logic.commands.CommandResult;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.model.Model;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
import safeforhall.model.event.EventNameContainsKeywordsPredicate;
import safeforhall.model.event.EventTime;
import safeforhall.model.event.Venue;

/**
 * Finds and lists all events in address book whose parameters matched any of the provided argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindEventCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String PARAMETERS = "[n/NAME] [d/DATE] [v/VENUE] [c/CAPACITY]";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all events whose parameters match any of "
            + "the provided keywords for different options (case-insensitive)\nand displays them as a "
            + "list with index numbers.\n"
            + "Parameters: "
            + "[" + CliSyntax.PREFIX_NAME + "NAME] "
            + "[" + CliSyntax.PREFIX_DATE + "DATE] "
            + "[" + CliSyntax.PREFIX_VENUE + "VENUE] "
            + "[" + CliSyntax.PREFIX_CAPACITY + "CAPACITY] "
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_NAME + "Football Training "
            + CliSyntax.PREFIX_DATE + "03-01-2021 "
            + CliSyntax.PREFIX_VENUE + "Field";

    public static final String MESSAGE_SUCCESS = "Matching events listed.";
    public static final String MESSAGE_NOT_FILTERED = "At least one field to filter be must be provided.";

    private final FindCompositePredicate predicate;

    public FindEventCommand(FindCompositePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindEventCommand // instanceof handles nulls
                && predicate.equals(((FindEventCommand) other).predicate)); // state check
    }

    /**
     * Stores the predicates to search the address book with. Each non-empty field value will be used for filtering.
     */
    public static class FindCompositePredicate implements Predicate<Event> {
        private Predicate<Event> eventName;
        private Predicate<EventDate> eventDate;
        private Predicate<EventTime> eventTime;
        private Predicate<Venue> venue;
        private Predicate<Capacity> capacity;

        // For equality checks
        private EventName eEventName;
        private EventDate eEventDate;
        private EventTime eEventTime;
        private Venue eVenue;
        private Capacity eCapacity;

        public FindCompositePredicate() {}

        /**
         * Copy constructor.
         */
        public FindCompositePredicate(FindCompositePredicate toCopy) {
            this.eventName = toCopy.eventName;
            this.eventDate = toCopy.eventDate;
            this.eventTime = toCopy.eventTime;
            this.venue = toCopy.venue;
            this.capacity = toCopy.capacity;

            this.eEventName = toCopy.eEventName;
            this.eEventDate = toCopy.eEventDate;
            this.eEventTime = toCopy.eEventTime;
            this.eVenue = toCopy.eVenue;
            this.eCapacity = toCopy.eCapacity;
        }

        /**
         * Returns true if at least one field is to be filtered with.
         */
        public boolean isAnyFieldFiltered() {
            return CollectionUtil.isAnyNonNull(eventName, eventDate, eventTime, venue, capacity);
        }

        public void setEventName(EventName eventName) {
            this.eEventName = new EventName(String.join(" ", eventName.eventName.split("\\s+")));
            this.eventName = new EventNameContainsKeywordsPredicate(Arrays.asList(eventName.eventName.split("\\s+")));
        }

        public void setEventDate(EventDate eventDate) {
            this.eEventDate = eventDate;
            this.eventDate = eventDate::equals;
        }

        public void setEventTime(EventTime eventTime) {
            this.eEventTime = eventTime;
            this.eventTime = eventTime::equals;
        }

        public void setVenue(Venue venue) {
            this.eVenue = venue;
            this.venue = venue::equals;
        }

        public void setCapacity(Capacity capacity) {
            this.eCapacity = capacity;
            this.capacity = capacity::equals;
        }

        public Optional<Predicate<Event>> getEventName() {
            return Optional.ofNullable(eventName);
        }

        public Optional<Predicate<EventDate>> getEventDate() {
            return Optional.ofNullable(eventDate);
        }

        public Optional<Predicate<EventTime>> getEventTime() {
            return Optional.ofNullable(eventTime);
        }

        public Optional<Predicate<Venue>> getVenue() {
            return Optional.ofNullable(venue);
        }

        public Optional<Predicate<Capacity>> getCapacity() {
            return Optional.ofNullable(capacity);
        }

        /**
         * Tests if the provided Event matches all the available criteria.
         *
         * @param event The Event object to test
         * @return True if matches all the preset criteria
         */
        @Override
        public boolean test(Event event) {
            List<Predicate<Event>> allPredicates = Arrays.asList(
                p -> getEventName().orElse(x -> true).test(p),
                p -> getEventDate().orElse(x -> true).test(p.getEventDate()),
                p -> getEventTime().orElse(x -> true).test(p.getEventTime()),
                p -> getVenue().orElse(x -> true).test(p.getVenue()),
                p -> getCapacity().orElse(x -> true).test(p.getCapacity()));

            return allPredicates
                    .stream()
                    .reduce(p -> true, Predicate::and)
                    .test(event);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FindCompositePredicate)) {
                return false;
            }

            // state check
            FindCompositePredicate e = (FindCompositePredicate) other;

            return Objects.equals(eEventName, e.eEventName)
                    && Objects.equals(eEventDate, e.eEventDate)
                    && Objects.equals(eEventTime, e.eEventTime)
                    && Objects.equals(eVenue, e.eVenue)
                    && Objects.equals(eCapacity, e.eCapacity);
        }
    }
}
