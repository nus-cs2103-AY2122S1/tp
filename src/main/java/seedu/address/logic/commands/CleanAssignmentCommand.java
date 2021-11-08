package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Removes all completed assignments for all persons in the addressbook.
 */
public class CleanAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "clean";
    public static final String MESSAGE_SUCCESS = "Completed assignments removed for all persons!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.cleanAssignments();
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
