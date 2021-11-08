package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears the screen.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String DESCRIPTION = "Clears the screen.";
    public static final String MESSAGE_SUCCESS = "Screen has been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": " + DESCRIPTION + "\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(unused -> false);
        model.displayFilteredTaskList(unused -> false);

        CommandResult commandResult = new CommandResult(MESSAGE_SUCCESS);
        commandResult.setWriteCommand();
        return commandResult;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof ClearCommand); // instanceof handles nulls
    }

    public String getCommand() {
        return COMMAND_WORD;
    }

    public String getDescription() {
        return DESCRIPTION;
    }
}
