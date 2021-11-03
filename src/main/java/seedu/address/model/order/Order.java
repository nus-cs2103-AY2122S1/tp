package seedu.address.model.order;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.product.Quantity.QUANTITY_ZERO;

import java.time.LocalDate;

import seedu.address.model.Model;
import seedu.address.model.commons.ID;
import seedu.address.model.commons.Name;
import seedu.address.model.product.Product;
import seedu.address.model.product.Quantity;

/**
 * Represents an Order in Sellah.
 */
public class Order {
    public static final String REGEX = "\\d+ \\d+ (\\d{4}/)?\\d{0,2}/\\d{0,2}";

    public static final String MESSAGE_CONSTRAINTS =
            "Please follow the format for orders: -o PRODUCT_ID QUANTITY TIME\n"
                    + "Valid formats of time: MM/DD, YYYY/MM/DD\n"
                    + "Example: -o 10312 20 2021/10/20";

    public static final String MESSAGE_CONSTRAINTS_ID = "The product with given ID doesn't exist.";
    public static final String MESSAGE_CONSTRAINTS_QUANTITY = "There is not enough stock for the requested product.";

    private final Name productName;
    private final Quantity quantity;
    private final LocalDate time;

    /**
     * Constructor of {@code Order}
     */
    public Order(ID id, Quantity quantity, LocalDate time, Model model) {
        checkArgument(isValidProductID(id, model), MESSAGE_CONSTRAINTS_ID);

        Product product = model.getProductById(id);

        checkArgument(isValidQuantity(quantity, product), MESSAGE_CONSTRAINTS_QUANTITY);

        productName = product.getName();
        this.quantity = quantity;
        this.time = time;
    }

    /**
     * Constructor of {@code Order}, assume attributes to be valid.
     */
    public Order(Name productName, Quantity quantity, LocalDate time) {
        this.productName = productName;
        this.quantity = quantity;
        this.time = time;
    }

    public Name getProductName() {
        return productName;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public LocalDate getTime() {
        return time;
    }

    private static boolean isValidProductID(ID id, Model model) {
        return model.hasProduct(id);
    }

    private static boolean isValidQuantity(Quantity quantity, Product product) {
        return product.hasEnoughStock(quantity);
    }

    public boolean isPositiveQuantity() {
        return quantity.moreThan(QUANTITY_ZERO);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return productName.equals(otherOrder.productName);
    }

    @Override
    public int hashCode() {
        return productName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return "[ Product Name: " + productName + ", Quantity: " + quantity + ", Time " + time + "]";
    }
}
