package seedu.address.model.order;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

import javafx.collections.transformation.FilteredList;
import seedu.address.model.AddressBook;
import seedu.address.model.commons.ID;
import seedu.address.model.product.Product;
import seedu.address.model.product.Quantity;

/**
 * Represents an Order in Sellah.
 */
public class Order {
    public static final String MESSAGE_CONSTRAINTS_ID = "The product with given ID doesn't exist.";
    public static final String MESSAGE_CONSTRAINTS_QUANTITY = "There is not enough stock for the requested product.";

    private final LocalDateTime time;
    private final ID productId;
    private final Quantity quantity;

    public Order(LocalDateTime time, ID productId, Quantity quantity, AddressBook addressBook) {
        FilteredList<Product> products = getProductInList(productId, addressBook);
        checkArgument(isValidProductID(products), MESSAGE_CONSTRAINTS_ID);
        Product product = products.get(0);
        checkArgument(isValidQuantity(quantity, product), MESSAGE_CONSTRAINTS_QUANTITY);

        this.time = time;
        this.productId = productId;
        this.quantity = quantity;
    }

    private static FilteredList<Product> getProductInList(ID productId, AddressBook addressBook) {
        FilteredList<Product> products = new FilteredList<>(addressBook.getProductList());
        products.setPredicate(product -> product.getId().equals(productId));
        return products;
    }

    private static boolean isValidProductID(FilteredList<Product> products) {
        return products.size() == 1;
    }

    private static boolean isValidQuantity(Quantity quantity, Product product) {
        return quantity.compareTo(product.getQuantity()) <= 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Order // instanceof handles nulls
                && time.equals(((Order) other).time)
                && productId.equals(((Order) other).productId)
                && quantity.equals(((Order) other).quantity)); // state check
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[ Time: " + time + ", Product ID: " + productId + ", Quantity: " + quantity + " ]";
    }
}