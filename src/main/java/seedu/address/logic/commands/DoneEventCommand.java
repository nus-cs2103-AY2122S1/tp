package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Marks an Event identified using its displayed index as done.
 */
public class DoneEventCommand extends Command {

    public static final String COMMAND_WORD = "doneEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the event identified by the "
            + "index number used in the displayed event list as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DONE_EVENT_SUCCESS = "Marked event as done:\n%1$s";

    private final Index targetIndex;

    /**
     * This is a constructor for DoneEventCommand.
     *
     * @param targetIndex The index of the event to be marked as done, as seen in the displayed event list.
     */
    public DoneEventCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToMarkDone = lastShownList.get(targetIndex.getZeroBased());
        model.markEventAsDone(eventToMarkDone);
        return new CommandResult(String.format(MESSAGE_DONE_EVENT_SUCCESS, eventToMarkDone.markAsDone()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DoneEventCommand
                && targetIndex.equals(((DoneEventCommand) other).targetIndex));
    }
}
