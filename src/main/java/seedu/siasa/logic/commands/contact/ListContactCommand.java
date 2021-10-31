package seedu.siasa.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.model.Model;

/**
 * Lists all contacts in the address book to the user.
 */
public class ListContactCommand extends Command {

    public static final String COMMAND_WORD = "listcontact";

    public static final String MESSAGE_SUCCESS = "Listed all contacts";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
