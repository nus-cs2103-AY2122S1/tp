package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PRODUCTS;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.product.Product;
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
    private Index index;

    /**
     * Constructor for the view product command.
     */
    public ViewProductCommand(ProductContainsIdPredicate predicate) {
        this.predicate = predicate;
        try {
            this.index = Index.fromOneBased(predicate.getId());
        } catch (IndexOutOfBoundsException ioobe) {
            this.index = Index.fromOneBased(Integer.MAX_VALUE);
        }

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredProductList(PREDICATE_SHOW_ALL_PRODUCTS);
        List<Product> lastShownList = model.getFilteredProductList();
        if (index.getZeroBased() < 0 || index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
        }

        Product productToEdit = lastShownList.get(index.getZeroBased());
        Predicate<Product> productPredicate = (product -> product.equals(productToEdit));
        model.updateFilteredProductList(productPredicate);
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
