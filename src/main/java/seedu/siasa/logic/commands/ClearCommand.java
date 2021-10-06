package seedu.siasa.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.siasa.model.Siasa;
import seedu.siasa.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new Siasa());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
