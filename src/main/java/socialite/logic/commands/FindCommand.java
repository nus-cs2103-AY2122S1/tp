package socialite.logic.commands;

import static java.util.Objects.requireNonNull;

import socialite.commons.core.Messages;
import socialite.model.Model;
import socialite.model.person.ContainsKeywordsPredicate;

/**
 * Finds and lists all persons in the contact list whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_HELP_GUIDE = "Enter 'help find' for in-app guidance.";

    public static final String MESSAGE_INVALID_HANDLE = "Invalid platform specified. "
            + "Only the following inputs are supported:\n"
            + "p/facebook, p/instagram, p/telegram, p/tiktok, p/twitter.";

    public static final String MESSAGE_EMPTY_TAG = "Tags to search for must not be empty.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters contacts containing ALL keywords listed.\n"
            + "Keywords can be in the form of tags (prefix with 't/'), "
            + "handles (prefix with 'p/'), and names (no prefix needed).\n"
            + "Parameters: find [KEYWORDS]; "
            + "Example: " + COMMAND_WORD + " alice t/colleagues p/instagram";

    private final ContainsKeywordsPredicate predicate;

    public FindCommand(ContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredPersonList(predicate);

        if (predicate.isEmptyTag()) {
            return new CommandResult(MESSAGE_EMPTY_TAG);
        }

        if (!predicate.hasValidHandles()) {
            return new CommandResult(MESSAGE_INVALID_HANDLE);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
