package seedu.siasa.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.commons.core.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
import static seedu.siasa.commons.core.Messages.MESSAGE_CONTACTS_LIST_EMPTY;

import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.model.Model;
import seedu.siasa.model.contact.NameContainsKeywordsPredicate;

/**
 * Finds and lists all contacts in SIASA whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindContactCommand extends Command {

    public static final String COMMAND_WORD = "findcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FindContactCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(predicate);

        if (model.getFilteredContactList().size() > 0) {
            return new CommandResult(
                    String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredContactList().size()));
        } else {
            return new CommandResult(MESSAGE_CONTACTS_LIST_EMPTY);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindContactCommand // instanceof handles nulls
                && predicate.equals(((FindContactCommand) other).predicate)); // state check
    }
}
