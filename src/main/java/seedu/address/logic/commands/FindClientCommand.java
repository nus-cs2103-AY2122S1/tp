package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.client.ClientContainsKeywordsPredicate;

/**
 * Finds and lists all clients in application whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindClientCommand extends Command {
    public static final String COMMAND_WORD = "find -c";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Finds all clients whose names contain any of "
                    + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
                    + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
                    + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final ClientContainsKeywordsPredicate predicate;

    public FindClientCommand(ClientContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW, model.getFilteredClientList().size()),
                CommandType.FIND, null, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindClientCommand // instanceof handles nulls
                && predicate.equals(((FindClientCommand) other).predicate)); // state check
    }
}
