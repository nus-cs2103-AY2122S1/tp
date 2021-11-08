package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.product.ProductContainsKeywordsPredicate;

/**
 * Finds and lists all products in application whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindProductCommand extends Command {
    public static final String COMMAND_WORD = "find -p";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Finds all products whose names contain any of "
                    + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
                    + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
                    + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final ProductContainsKeywordsPredicate predicate;

    public FindProductCommand(ProductContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredProductList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PRODUCTS_LISTED_OVERVIEW, model.getFilteredProductList().size()),
                CommandType.FIND, null, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindProductCommand // instanceof handles nulls
                && predicate.equals(((FindProductCommand) other).predicate)); // state check
    }
}
