package seedu.address.testutil;

import seedu.address.model.commons.Name;
import seedu.address.model.product.Product;
import seedu.address.model.product.Quantity;
import seedu.address.model.product.UnitPrice;

/**
 * A utility class to help with building Product objects.
 */
public class ProductBuilder {

    public static final String DEFAULT_NAME = "Monitor";
    public static final String DEFAULT_PRICE = "1";
    public static final String DEFAULT_QUANTITY = "1";

    private Name name;
    private UnitPrice unitPrice;
    private Quantity quantity;

    /**
     * Creates a {@code ProductBuilder} with the default details.
     */
    public ProductBuilder() {
        name = new Name(DEFAULT_NAME);
        unitPrice = new UnitPrice(DEFAULT_PRICE);
        quantity = new Quantity(DEFAULT_QUANTITY);
    }

    /**
     * Initializes the ProductBuilder with the data of {@code productToCopy}.
     */
    public ProductBuilder(Product productToCopy) {
        name = productToCopy.getName();
        unitPrice = productToCopy.getUnitPrice();
        quantity = productToCopy.getQuantity();
    }

    /**
     * Sets the {@code Name} of the {@code Product} that we are building.
     */
    public ProductBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code UnitPrice} of the {@code Product} that we are building.
     */
    public ProductBuilder withUnitPrice(String unitPrice) {
        this.unitPrice = new UnitPrice(unitPrice);
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Product} that we are building.
     */
    public ProductBuilder withQuantity(String quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    public Product build() {
        return new Product(name, unitPrice, quantity);
    }
}
