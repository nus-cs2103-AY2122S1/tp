package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearTasksCommand extends Command {

    public static final String COMMAND_WORD = "clearTasks";
    public static final String MESSAGE_SUCCESS = "Task list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearTasks();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
