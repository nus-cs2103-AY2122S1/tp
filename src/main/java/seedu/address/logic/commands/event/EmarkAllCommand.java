package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.event.Event;

/**
 * Marks all member in event identified using it's displayed index from the Ailurus.
 */
public class EmarkAllCommand extends Command {

    public static final String COMMAND_WORD = "emarkall";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark all members as present for the event.\n"
            + "Parameters: "
            + PREFIX_EVENT_INDEX + "EVENT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_INDEX + "1 ";

    public static final String MESSAGE_MARK_MEMBER_SUCCESS = "Marked all members in event: %1$s";

    private final Index eventIndex;

    /**
     * Constructor for EmarkAllCommand
     *
     * @param index eventIndex to mark all as present
     */
    public EmarkAllCommand(Index index) {
        requireNonNull(index);
        this.eventIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownEventList = model.getFilteredEventList();
        if (eventIndex.getZeroBased() >= lastShownEventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event selectedEvent = lastShownEventList.get(eventIndex.getZeroBased());

        selectedEvent.markAttendanceForAll();
        return new CommandResult(String.format(MESSAGE_MARK_MEMBER_SUCCESS, selectedEvent));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmarkAllCommand // instanceof handles nulls
                && eventIndex.equals(((EmarkAllCommand) other).eventIndex)); // state check
    }
}
