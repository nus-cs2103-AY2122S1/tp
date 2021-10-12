package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.ModuleContainsKeywordsPredicate;

/**
 * Filters and lists all persons in address book who have a certain module (and optionally group status) as their tag.
 * Keyword matching is case insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Show user profiles filtered by module code "
            + "and optionally by group status.\n"
            + "OPTIONAL_GROUP_STATUS enumerates the following: "
            + "{ SG: seeking group, SM: seeking member, G: in a group, NG: no group }\n"
            + "MODULE_CODE is required for filtering by group status. "
            + "The filter will return the profiles with the specified group status of the specified module.\n"
            + "Parameters: filter mod/MODULE_CODE [group/GROUP_STATUS]\n"
            + "Example: " + COMMAND_WORD + " mod/CS2030 group/SM\n";

    private final ModuleContainsKeywordsPredicate predicate;

    public FilterCommand(ModuleContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }

}
