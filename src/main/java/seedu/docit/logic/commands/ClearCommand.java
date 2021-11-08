package seedu.docit.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.docit.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends BasicCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearAllRecords();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
