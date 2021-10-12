package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.awe.commons.core.Messages;
import seedu.awe.model.Model;
import seedu.awe.model.group.GroupContainsKeywordsPredicate;

/**
 * Finds and lists all persons in awe book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindGroupsCommand extends Command {

    public static final String COMMAND_WORD = "findgroups";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all groups whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " london";

    private final GroupContainsKeywordsPredicate predicate;

    public FindGroupsCommand(GroupContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGroupList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_GROUPS_LISTED_OVERVIEW, model.getFilteredGroupList().size()),
                false, false, true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindGroupsCommand)) { // instanceof handles nulls
            return false;
        }

        return predicate.equals(((FindGroupsCommand) other).predicate); // state check
    }
}
