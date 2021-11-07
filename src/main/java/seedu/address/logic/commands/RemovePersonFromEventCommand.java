package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EVENT_DETAILS_NOT_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_DETAILS_NOT_FOUND;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.List;

import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventNameEqualKeywordPredicate;
import seedu.address.model.person.NameEqualKeywordPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

public class RemovePersonFromEventCommand extends Command {

    public static final String COMMAND_WORD = "removePersonFromEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the Person specified from the Event specified. \n"
            + "Parameters: "
            + PREFIX_NAME + "PERSON_NAME "
            + PREFIX_EVENT_NAME + "EVENT_NAME"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_EVENT_NAME + "Training Week 10 Thursday";

    public static final String MESSAGE_SUCCESS = "Person %1$s removed from event %2$s";

    private final NameEqualKeywordPredicate personPredicate;
    private final EventNameEqualKeywordPredicate eventPredicate;

    /**
     * Creates a RemovePersonFromEventCommand to remove the specified {@code Person} matched by the name predicate
     * from the event matched by the event name predicate.
     */
    public RemovePersonFromEventCommand(NameEqualKeywordPredicate personPredicate,
                                   EventNameEqualKeywordPredicate eventPredicate) {
        requireAllNonNull(personPredicate, eventPredicate);
        this.personPredicate = personPredicate;
        this.eventPredicate = eventPredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Event> eventsMatched = model.getEvents(eventPredicate);
        assert eventsMatched.size() < 2;
        if (eventsMatched.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_EVENT_DETAILS_NOT_FOUND, eventPredicate));
        }
        Event target = eventsMatched.get(0);

        UniquePersonList personsMatched = target.getAttendees();
        assert personsMatched.size() < 2;
        if (personsMatched.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_PERSON_DETAILS_NOT_FOUND, personPredicate));
        }

        FilteredList<Person> toRemoveAsList = personsMatched.asUnmodifiableObservableList().filtered(personPredicate);
        if (toRemoveAsList.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_PERSON_DETAILS_NOT_FOUND, personPredicate));
        }

        Person toRemove = toRemoveAsList.get(0);
        target.removePerson(toRemove);

        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toRemove.getName(), target.getEventName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemovePersonFromEventCommand // instanceof handles nulls
                && personPredicate.equals(((RemovePersonFromEventCommand) other).personPredicate)
                && eventPredicate.equals(((RemovePersonFromEventCommand) other).eventPredicate));
    }

}
