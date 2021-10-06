package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Adds a progress to an exiting student in TutorAid. Update the progress if one already exists.
 */
public class AddProgressCommand extends Command {

    public static final String COMMAND_WORD = "add -p";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from Add Progress");
    }
}
