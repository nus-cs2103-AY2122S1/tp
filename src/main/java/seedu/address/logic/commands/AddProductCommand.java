package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIT_PRICE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commons.Name;
import seedu.address.model.product.Product;
import seedu.address.model.product.Quantity;
import seedu.address.model.product.UnitPrice;

public class AddProductCommand extends Command {
    public static final String COMMAND_WORD = "add -p";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Adds a product to the address book. "
                    + "Parameters: "
                    + "NAME "
                    + PREFIX_UNIT_PRICE + "UNIT_PRICE "
                    + "[" + PREFIX_QUANTITY + "QUANTITY] \n"
                    + "Example: " + COMMAND_WORD + " "
                    + "pen "
                    + PREFIX_UNIT_PRICE + "20 "
                    + PREFIX_QUANTITY + "150";

    public static final String MESSAGE_SUCCESS = "New product added: %1$s";
    public static final String MESSAGE_DUPLICATE_PRODUCT = "This product already exists in Sellah";

    private AddProductDescriptor addProductDescriptor;
    private Product productToAdd;

    /**
     * Constructor of the class `AddProductCommand`.
     *
     * @param addProductDescriptor A descriptor containing the information of the added product.
     */
    public AddProductCommand(AddProductDescriptor addProductDescriptor) {
        requireNonNull(addProductDescriptor);
        this.addProductDescriptor = addProductDescriptor;
        this.productToAdd = createAddedProduct(addProductDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProduct(productToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PRODUCT);
        }

        model.addProduct(productToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, productToAdd));
    }

    /**
     * Creates a Product to be added using an AddProductDescriptor.
     *
     * @param addProductDescriptor A descriptor containing the information of the product.
     * @return The product to be added.
     */
    private static Product createAddedProduct(AddProductDescriptor addProductDescriptor) {
        Name name = addProductDescriptor.getName();
        UnitPrice unitPrice = addProductDescriptor.getUnitPrice();
        Quantity quantity = addProductDescriptor.getQuantity();
        return new Product(name, unitPrice, quantity);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddProductCommand // instanceof handles nulls
                && productToAdd.equals(((AddProductCommand) other).productToAdd));
    }

    /**
     * Stores the details of the new product.
     */
    public static class AddProductDescriptor {
        private Name name;
        private UnitPrice unitPrice;
        private Quantity quantity;

        /**
         * Constructor of the class `AddProductDescriptor`.
         *
         * @param name Name of the product.
         */
        public AddProductDescriptor(Name name, UnitPrice unitPrice) {
            this.name = name;
            this.unitPrice = unitPrice;
        }

        /**
         * Gets the name of a product.
         *
         * @return The name of a product.
         */
        public Name getName() {
            return name;
        }

        /**
         * Gets the unit price of a product.
         *
         * @return The unit price of a product.
         */
        public UnitPrice getUnitPrice() {
            return unitPrice;
        }

        /**
         * Gets the quantity of a product.
         *
         * @return The quantity of a product.
         */
        public Quantity getQuantity() {
            return quantity;
        }

        /**
         * Updates a product's quantity.
         *
         * @param quantity The new quantity of the product.
         */
        public void setQuantity(Quantity quantity) {
            this.quantity = quantity;
        }
    }
}
