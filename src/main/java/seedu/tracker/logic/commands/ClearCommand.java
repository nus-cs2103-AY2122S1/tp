package seedu.tracker.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.tracker.model.Model;
import seedu.tracker.model.ModuleTracker;

/**
 * Clears the module Tracker.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Module tracker has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setModuleTracker(new ModuleTracker());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
