package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.CategoryCode;
import seedu.address.model.person.IsFilterablePredicate;
import seedu.address.model.person.Rating;

/**
 * Filters contacts in the address book by category.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all contacts by category or rating "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: [" + PREFIX_CATEGORY_CODE + "CATEGORY_CODE] "
            + "[" + PREFIX_RATING + "RATING] \n"
            + "Example: " + COMMAND_WORD + " c/att" + " ra/5";

    private final Set<CategoryCode> categoryCodes;
    private final IsFilterablePredicate predicate;

    private final Rating rating;

    /**
     * constructor for FilterCommand
     * @param categoryCodes types of contacts to be filtered
     */
    public FilterCommand(Set<CategoryCode> categoryCodes, Rating rating) {
        requireNonNull(categoryCodes);
        this.categoryCodes = categoryCodes;
        this.predicate = new IsFilterablePredicate(categoryCodes, rating);
        this.rating = rating;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
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
        return categoryCodes.equals(e.categoryCodes) && rating.equals(e.rating);
    }
}
