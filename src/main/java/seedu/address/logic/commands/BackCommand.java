package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class BackCommand extends Command {

    public static final String COMMAND_WORD = "/back";

    public static final String MESSAGE_SUCCESS = "You exited the current directory! " +
            "Please enter either 'students' or 'tasks' to enter a directory.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
