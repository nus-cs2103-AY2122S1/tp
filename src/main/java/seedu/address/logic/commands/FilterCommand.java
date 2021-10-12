package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.CategoryCode;
import seedu.address.model.person.IsInCategoryPredicate;



/**
 * Filters contacts in the address book by category.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all contacts by category "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: CATEGORY_CODE\n"
            + "Example: " + COMMAND_WORD + " c/att";

    private final Set<CategoryCode> categoryCodes;
    private final IsInCategoryPredicate predicate;

    /**
     * constructor for FilterCommand
     * @param categoryCodes types of contacts to be filtered
     */
    public FilterCommand(Set<CategoryCode> categoryCodes) {
        requireNonNull(categoryCodes);
        this.categoryCodes = categoryCodes;
        this.predicate = new IsInCategoryPredicate(categoryCodes);

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
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        // state check
        FilterCommand e = (FilterCommand) other;
        return categoryCodes.equals(e.categoryCodes);
    }
}
