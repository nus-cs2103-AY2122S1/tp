package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Delete a progress of an exiting student in TutorAid.
 */
public class DeleteProgressCommand extends Command {

    public static final String COMMAND_WORD = "del -p";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from Delete Progress");
    }
}
