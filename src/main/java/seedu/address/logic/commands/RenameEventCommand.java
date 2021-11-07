package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.person.UniquePersonList;

/**
 * Changes the name of an event
 */
public class RenameEventCommand extends Command {

    public static final String COMMAND_WORD = "renameEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Renames an existing event "
            + "by the index number used in the event list. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_EVENT_NAME + "NEW_NAME \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EVENT_NAME + "Training Week 10 Thursday";

    public static final String MESSAGE_RENAME_EVENT_SUCCESS = "Renamed Event %1$s into %2$s";
    public static final String MESSAGE_NOT_RENAMED = "The new name must be provided.";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the address book.";


    private final Index index;
    private final RenameEventDescriptor renameEventDescriptor;

    /**
     * @param index of the event in the event list to be renamed
     * @param renameEventDescriptor details to rename the event with
     */
    public RenameEventCommand(Index index, RenameEventDescriptor renameEventDescriptor) {
        requireNonNull(index);
        requireNonNull(renameEventDescriptor);

        this.index = index;
        this.renameEventDescriptor = new RenameEventDescriptor(renameEventDescriptor);
    }

    private static Event createRenamedEvent(Event eventToRename, RenameEventDescriptor renameEventDescriptor) {
        assert eventToRename != null;

        EventName updatedEventName = renameEventDescriptor.getEventName().orElse(eventToRename.getEventName());
        UniquePersonList attendees = renameEventDescriptor.getAttendees().orElse(eventToRename.getAttendees());

        return new Event(updatedEventName, attendees);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownEventList = model.getFilteredEventList();

        if (index.getZeroBased() >= lastShownEventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToRename = lastShownEventList.get(index.getZeroBased());
        Event renamedEvent = createRenamedEvent(eventToRename, renameEventDescriptor);

        if (!eventToRename.isSameEvent(renamedEvent) && model.hasEvent(renamedEvent)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.setEvent(eventToRename, renamedEvent);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(String.format(MESSAGE_RENAME_EVENT_SUCCESS, eventToRename.getEventName(),
                renamedEvent.getEventName()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RenameEventCommand)) {
            return false;
        }

        // state check
        RenameEventCommand e = (RenameEventCommand) other;
        return index.equals(e.index)
                && renameEventDescriptor.equals(e.renameEventDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class RenameEventDescriptor {
        private EventName eventName;
        private UniquePersonList attendees;

        public RenameEventDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public RenameEventDescriptor(RenameEventCommand.RenameEventDescriptor toCopy) {
            setEventName(toCopy.eventName);
            setAttendees(toCopy.attendees);
        }

        /**
         * Returns true if the event name has been edited.
         */
        public boolean isEventNameRenamed() {
            return CollectionUtil.isAnyNonNull(eventName);
        }

        public Optional<EventName> getEventName() {
            return Optional.ofNullable(eventName);
        }

        public void setEventName(EventName eventName) {
            this.eventName = eventName;
        }

        public Optional<UniquePersonList> getAttendees() {
            return Optional.ofNullable(attendees);
        }

        public void setAttendees(UniquePersonList attendees) {
            this.attendees = attendees;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof RenameEventCommand.RenameEventDescriptor)) {
                return false;
            }

            // state check
            RenameEventCommand.RenameEventDescriptor e = (RenameEventCommand.RenameEventDescriptor) other;

            return getEventName().equals(e.getEventName())
                    && getAttendees().equals(e.getAttendees());
        }
    }
}
