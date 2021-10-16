package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Removes an Event identified using its displayed index.
 */
public class RemoveEventCommand extends Command {

    public static final String COMMAND_WORD = "removeEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the event identified by the "
            + "index number used in the displayed event list as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMOVE_EVENT_SUCCESS = "Removed Event:\n%1$s";

    private final Index targetIndex;

    /**
     * This is a constructor for RemoveEventCommand.
     *
     * @param targetIndex The index of the event to be removed, as seen in the displayed event list.
     */
    public RemoveEventCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToRemove = lastShownList.get(targetIndex.getZeroBased());
        model.removeEvent(eventToRemove);
        return new CommandResult(String.format(MESSAGE_REMOVE_EVENT_SUCCESS, eventToRemove));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RemoveEventCommand
                && targetIndex.equals(((RemoveEventCommand) other).targetIndex));
    }
}
