package seedu.track2gather.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.track2gather.model.Model;

/**
 * Starts a new call session with all persons set to non-called.
 */
public class SessionCommand extends Command {
    public static final String COMMAND_WORD = "session";
    public static final String MESSAGE_SUCCESS = "Started a new call session with all persons set to non-called.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetAllCallStatuses();
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_NON_CALLED);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
