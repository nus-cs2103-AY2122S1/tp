package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    /**
     * Stands for exit command.
     */
    public static final String COMMAND_WORD = "exit";

    /**
     * Stands for the message of exit success command.
     */
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting ezFoodie as requested ...";

    /**
     * Overrides and executes the model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult related to edit command.
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false, false);
    }

}
