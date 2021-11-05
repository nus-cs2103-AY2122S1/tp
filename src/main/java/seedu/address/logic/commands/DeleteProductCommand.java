package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.commons.ID;
import seedu.address.model.commons.Name;
import seedu.address.model.product.Product;

/**
 * Deletes a product identified using it's displayed index from the address book.
 */
public class DeleteProductCommand extends Command {
    public static final String COMMAND_WORD = "delete -p";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Deletes the product identified by the index number used in the displayed product list.\n"
                    + "Parameters: INDEX (must be a positive integer)\n"
                    + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PRODUCT_SUCCESS = "Deleted Product: %1$s";
    private static final Logger logger = LogsCenter.getLogger("DeleteProductLogger");

    private final Index targetIndex;

    public DeleteProductCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Product> lastShownList = model.getFilteredProductList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
        }

        Product productToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteProduct(productToDelete);

        ID productID = productToDelete.getId();
        logger.log(Level.INFO, String.format("Deleted product (ID %1$s)", productID));

        Name productName = productToDelete.getName();
        ObservableList<Client> clientList = model.getAddressBook().getClientList();
        clientList.forEach(client -> {
            if (client.hasOrder(productName)) {
                client.removeOrder(productName);
            }
        });

        logger.log(Level.INFO, String.format("Deleted orders containing product (ID %1$s)", productID));

        return new CommandResult(String.format(MESSAGE_DELETE_PRODUCT_SUCCESS, productToDelete),
                CommandType.DELETE, null, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteProductCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteProductCommand) other).targetIndex)); // state check
    }
}
