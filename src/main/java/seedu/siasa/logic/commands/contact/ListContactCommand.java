package seedu.siasa.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.commons.core.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
import static seedu.siasa.commons.core.Messages.MESSAGE_CONTACTS_LIST_EMPTY;
import static seedu.siasa.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.model.Model;

/**
 * Lists all contacts in the SIASA to the user.
 */
public class ListContactCommand extends Command {

    public static final String COMMAND_WORD = "allcontact";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);

        if (model.getFilteredContactList().size() > 0) {
            return new CommandResult(String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW,
                    model.getFilteredContactList().size()));
        } else {
            return new CommandResult(MESSAGE_CONTACTS_LIST_EMPTY);
        }
    }
}
