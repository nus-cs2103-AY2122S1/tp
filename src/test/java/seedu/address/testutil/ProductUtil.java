package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIT_PRICE;

import seedu.address.logic.commands.AddProductCommand;
import seedu.address.logic.commands.EditProductCommand;
import seedu.address.model.product.Product;

/**
 * A utility class for Product.
 */
public class ProductUtil {

    /**
     * Returns an add product command string for adding the {@code product}.
     */
    public static String getAddProductCommand(Product product) {
        return AddProductCommand.COMMAND_WORD + " " + getProductDetails(product);
    }

    /**
     * Returns the part of command string for the given {@code product}'s details.
     */
    public static String getProductDetails(Product product) {
        StringBuilder sb = new StringBuilder();
        sb.append(product.getName().fullName + " ");
        sb.append(PREFIX_UNIT_PRICE + product.getUnitPrice().value + " ");
        sb.append(PREFIX_QUANTITY + product.getQuantity().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditProductDescriptor}'s details.
     */
    public static String getEditProductDescriptorDetails(EditProductCommand.EditProductDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getUnitPrice().ifPresent(price -> sb.append(PREFIX_UNIT_PRICE).append(price.value).append(" "));
        descriptor.getQuantity().ifPresent(quantity -> sb.append(PREFIX_QUANTITY).append(quantity.value).append(" "));
        return sb.toString();
    }
}
