package seedu.docit.logic.commands;

import seedu.docit.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends BasicCommand {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_USAGE = "doc " + COMMAND_WORD + ": Exits the program.\n"
            + "Example: doc " + COMMAND_WORD;

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Doc'it as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false);
    }

}
