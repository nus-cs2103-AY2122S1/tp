package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class EditClassCommand extends Command {
    public static final String COMMAND_WORD = "editclass";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the class identified "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return null;
    }
}
