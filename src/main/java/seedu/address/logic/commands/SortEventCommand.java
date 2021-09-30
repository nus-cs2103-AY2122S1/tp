package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * This is a SortEventCommand class that handles sorting of events in Chronological order.
 */
public class SortEventCommand extends Command {

    public static final String COMMAND_WORD = "sortEvents";

    public static final String MESSAGE_SUCCESS = "Events sorted chronologically";

    @Override
    public CommandResult execute(Model model) {
        model.sortEvents();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof SortEventCommand;
    }
}
