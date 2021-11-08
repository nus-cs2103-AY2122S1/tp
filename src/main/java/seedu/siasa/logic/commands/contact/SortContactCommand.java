package seedu.siasa.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.commons.core.Messages.MESSAGE_CONTACTS_LIST_EMPTY;

import java.util.Comparator;

import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.model.Model;
import seedu.siasa.model.contact.Contact;

/**
 * Sorts the contact list.
 */
public class SortContactCommand extends Command {

    public static final String COMMAND_WORD = "sortcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the contact list alphabetically by the order specified.\n"
            + "Parameters: ORDER (asc, dsc)\n"
            + "Example: " + COMMAND_WORD + " dsc";

    public static final String MESSAGE_SUCCESS = "Sorted contacts";

    public static final String MESSAGE_NO_SUCH_COMPARATOR = "No such sorting order";

    private final Comparator<Contact> comparator;

    public SortContactCommand() {
        this.comparator = null;
    }

    public SortContactCommand(Comparator<Contact> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (comparator != null) {
            model.updateFilteredContactList(comparator);

            if (model.getFilteredContactList().size() > 0) {
                return new CommandResult(MESSAGE_SUCCESS);
            } else {
                return new CommandResult(MESSAGE_CONTACTS_LIST_EMPTY);
            }
        } else {
            return new CommandResult(MESSAGE_NO_SUCH_COMPARATOR);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SortContactCommand
                && comparator.equals(((SortContactCommand) other).comparator));
    }
}
