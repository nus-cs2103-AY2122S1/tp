package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PRODUCTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commons.Name;
import seedu.address.model.product.Product;
import seedu.address.model.product.UnitPrice;

/**
 * Edits the details of an existing product in the address book.
 */
public class EditProductCommand extends Command {
    public static final String COMMAND_WORD = "edit -p";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Edits the details of the product identified by the index number used in the displayed "
                    + "product list.\n"
                    + "Existing values will be overwritten by the input values.\n"
                    + "Parameters: INDEX (must be a positive integer) "
                    + "[" + PREFIX_NAME + "NAME]\n"
                    + "Example: " + COMMAND_WORD + " 1 "
                    + PREFIX_NAME + " Ben";

    public static final String MESSAGE_EDIT_PRODUCT_SUCCESS = "Edited Product: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PRODUCT = "This product already exists in the address book.";

    private final Index index;
    private final EditProductDescriptor editProductDescriptor;

    /**
     * @param index of the product in the filtered product list to edit
     * @param editProductDescriptor details to edit the product with
     */
    public EditProductCommand(Index index, EditProductDescriptor editProductDescriptor) {
        requireNonNull(index);
        requireNonNull(editProductDescriptor);

        this.index = index;
        this.editProductDescriptor = new EditProductDescriptor(editProductDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Product> lastShownList = model.getFilteredProductList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
        }

        Product productToEdit = lastShownList.get(index.getZeroBased());
        Product editedProduct = createEditedProduct(productToEdit, editProductDescriptor);

        if (!productToEdit.isSameProduct(editedProduct) && model.hasProduct(editedProduct)) {
            throw new CommandException(MESSAGE_DUPLICATE_PRODUCT);
        }

        model.setProduct(productToEdit, editedProduct);
        model.updateFilteredProductList(PREDICATE_SHOW_ALL_PRODUCTS);
        return new CommandResult(String.format(MESSAGE_EDIT_PRODUCT_SUCCESS, editedProduct));
    }

    /**
     * Creates and returns a {@code Product} with the details of {@code productToEdit}
     * edited with {@code editProductDescriptor}.
     */
    private static Product createEditedProduct(Product productToEdit, EditProductDescriptor editProductDescriptor) {
        assert productToEdit != null;

        Name updatedName = editProductDescriptor.getName().orElse(productToEdit.getName());
        UnitPrice updatedUnitPrice = editProductDescriptor.getUnitPrice().orElse(productToEdit.getUnitPrice());

        return Product.updateProduct(productToEdit, updatedName, updatedUnitPrice);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditProductCommand)) {
            return false;
        }

        // state check
        EditProductCommand e = (EditProductCommand) other;
        return index.equals(e.index)
                       && editProductDescriptor.equals(e.editProductDescriptor);
    }

    /**
     * Stores the details to edit the product with. Each non-empty field value will replace the
     * corresponding field value of the product.
     */
    public static class EditProductDescriptor {
        private Name name;
        private UnitPrice unitPrice;

        public EditProductDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditProductDescriptor(EditProductDescriptor toCopy) {
            setName(toCopy.name);
            setUnitPrice(toCopy.unitPrice);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, unitPrice);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setUnitPrice(UnitPrice unitPrice) {
            this.unitPrice = unitPrice;
        }

        public Optional<UnitPrice> getUnitPrice() {
            return Optional.ofNullable(unitPrice);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditProductDescriptor)) {
                return false;
            }

            // state check
            EditProductDescriptor e = (EditProductDescriptor) other;

            return getName().equals(e.getName())
                           && getUnitPrice().equals(e.getUnitPrice());
        }
    }
}
