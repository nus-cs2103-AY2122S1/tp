package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.product.ProductContainsIdPredicate;

/**
 * Views a product based on the index specified.
 */
public class ViewProductCommand extends Command {
    public static final String COMMAND_WORD = "view -p";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Views a current product identified by the index number used in the displayed "
                    + "product list.\n"
                    + "If no such product exists, nothing will be shown\n"
                    + "Parameters: INDEX (must be a positive integer)"
                    + "Example usage : "
                    + COMMAND_WORD
                    + " 20 ";

    private final ProductContainsIdPredicate predicate;

    public ViewProductCommand(ProductContainsIdPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredProductList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PRODUCTS_LISTED_OVERVIEW, model.getFilteredProductList().size())
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewProductCommand // instanceof handles nulls
                && predicate.equals(((ViewProductCommand) other).predicate)); // state check
    }
}
