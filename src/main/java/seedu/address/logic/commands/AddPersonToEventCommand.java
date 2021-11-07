package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EVENT_DETAILS_NOT_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_DETAILS_NOT_FOUND;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.EventNameEqualKeywordPredicate;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.NameEqualKeywordPredicate;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Adds a person to the address book.
 */
public class AddPersonToEventCommand extends Command {

    public static final String COMMAND_WORD = "addPersonToEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a existing person in the address book "
            + "to the Event specified.\n"
            + "Parameters: "
            + PREFIX_NAME + "PERSON_NAME "
            + PREFIX_EVENT_NAME + "EVENT_NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_EVENT_NAME + "Great Party Event";

    public static final String MESSAGE_SUCCESS = "Person %1$s added to %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the Event";

    private final NameEqualKeywordPredicate personPredicate;
    private final EventNameEqualKeywordPredicate eventPredicate;

    /**
     * Creates an AddPersonToEventCommand to add the specified {@code Person} matched by the name predicate
     * to the event matched by the event name predicate.
     *
     * @param personPredicate The predicate matching the person name.
     * @param eventPredicate The predicate matching the event name.
     */
    public AddPersonToEventCommand(NameEqualKeywordPredicate personPredicate,
                                   EventNameEqualKeywordPredicate eventPredicate) {
        requireAllNonNull(personPredicate, eventPredicate);
        this.personPredicate = personPredicate;
        this.eventPredicate = eventPredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.addPersonToEvent(personPredicate, eventPredicate);
        } catch (PersonNotFoundException e) {
            throw new CommandException(String.format(MESSAGE_PERSON_DETAILS_NOT_FOUND, personPredicate));
        } catch (EventNotFoundException e) {
            throw new CommandException(String.format(MESSAGE_EVENT_DETAILS_NOT_FOUND, eventPredicate));
        } catch (DuplicatePersonException e) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, personPredicate, eventPredicate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPersonToEventCommand // instanceof handles nulls
                && personPredicate.equals(((AddPersonToEventCommand) other).personPredicate)
                && eventPredicate.equals(((AddPersonToEventCommand) other).eventPredicate));
    }
}
