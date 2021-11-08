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
                false, false, true, false,
                false, false, false);
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
