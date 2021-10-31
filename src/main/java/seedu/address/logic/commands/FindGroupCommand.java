package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.group.GroupContainsKeywordsPredicate;

/**
 * Finds and lists all groups in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindGroupCommand extends Command {

    public static final String COMMAND_WORD = "findGroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all groups whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Note: * All special characters except dashes (-) will be ignored, and partial names will be matched.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " W14-4 W15";

    private final GroupContainsKeywordsPredicate predicate;

    public FindGroupCommand(GroupContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGroupList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_GROUPS_LISTED_OVERVIEW, model.getFilteredGroupList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindGroupCommand // instanceof handles nulls
                && predicate.equals(((FindGroupCommand) other).predicate)); // state check
    }
}
