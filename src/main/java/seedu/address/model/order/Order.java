package seedu.address.model.order;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

import seedu.address.model.Model;
import seedu.address.model.product.Product;
import seedu.address.model.product.Quantity;

/**
 * Represents an Order in Sellah.
 */
public class Order {
    public static final String REGEX =
            "^[0-9]+ [0-9]+ (((19|2[0-9])[0-9]{2})/)?(0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])$";

    public static final String MESSAGE_CONSTRAINTS =
            "Please follow the format for orders: -o PRODUCT_ID QUANTITY TIME\n"
                    + "Valid formats of time: MM/DD, YYYY/MM/DD\nExample: -o 10312 20 2021/10/20";
    public static final String MESSAGE_CONSTRAINTS_ID = "The product with given ID doesn't exist.";
    public static final String MESSAGE_CONSTRAINTS_POS_QUANTITY = "Quantity must be a positive integer.";
    public static final String MESSAGE_CONSTRAINTS_QUANTITY = "There is not enough stock for the requested product.";

    public final LocalDate time;
    public final int productId;
    public final Quantity quantity;

    /**
     * Constructor of {@code Order}
     */
    public Order(LocalDate time, int productId, Quantity quantity, Model model) {
        checkArgument(isValidProductID(productId, model), MESSAGE_CONSTRAINTS_ID);
        Product product = model.getProductById(productId);
        checkArgument(isPositiveQuantity(quantity), MESSAGE_CONSTRAINTS_POS_QUANTITY);
        checkArgument(isValidQuantity(quantity, product), MESSAGE_CONSTRAINTS_QUANTITY);

        this.time = time;
        this.productId = productId;
        this.quantity = quantity;
    }
    /**
     * Constructor of {@code Order}, assume attributes to be valid.
     */
    public Order(LocalDate time, int productId, Quantity quantity) {
        this.time = time;
        this.productId = productId;
        this.quantity = quantity;
    }

    private static boolean isValidProductID(int productId, Model model) {
        return model.hasProduct(productId);
    }

    private static boolean isValidQuantity(Quantity quantity, Product product) {
        return quantity.compareTo(product.getQuantity()) <= 0;
    }

    private static boolean isPositiveQuantity(Quantity quantity) {
        return quantity.compareTo(new Quantity("0")) > 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Order // instanceof handles nulls
                && time.equals(((Order) other).time)
                && productId == (((Order) other).productId)
                && quantity.equals(((Order) other).quantity)); // state check
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[ Time: " + time + ", Product ID: " + productId + ", Quantity: " + quantity + " ]";
    }
}
