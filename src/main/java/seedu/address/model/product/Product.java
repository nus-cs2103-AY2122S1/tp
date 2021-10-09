package seedu.address.model.product;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.Category;
import seedu.address.model.commons.ID;
import seedu.address.model.commons.Name;

/**
 * Represents a Product in the app.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Product implements Category {
    // Identity fields
    private final ID id;

    // Data fields
    private final Name name;

    public Product(Name name) {
        this(new ID(), name);
    }

    /**
     * Every field must be present and not null.
     */
    public Product(ID id, Name name) {
        requireAllNonNull(id, name);

        this.id = id;
        this.name = name;
    }

    public ID getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public static Product updateProduct(Product product, Name name) {
        return new Product(product.getId(), name);
    }

    /**
     * Returns true if both products have the same id.
     * This defines a weaker notion of equality between two products.
     *
     * @param otherProduct Another Product being compared to.
     */
    public boolean isSameProduct(Product otherProduct) {
        if (otherProduct == this) {
            return true;
        }

        return otherProduct != null && otherProduct.getId().equals(this.getId());
    }

    /**
     * Returns true if both products have the same identity and data fields.
     * This defines a stronger notion of equality between two products.
     *
     * @param other Any Object being compared to. May or may not be a instance of Product.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Product)) {
            return false;
        }

        Product otherProduct = (Product) other;
        return this.getId() == otherProduct.getId();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ID: ")
                .append(getId())
                .append("; Name: ")
                .append(getName());

        return builder.toString();
    }
}
