package seedu.address.testutil;

import seedu.address.logic.commands.EditProductCommand.EditProductDescriptor;
import seedu.address.model.commons.Name;
import seedu.address.model.product.Product;
import seedu.address.model.product.Quantity;
import seedu.address.model.product.UnitPrice;

/**
 * A utility class to help with building EditProductDescriptor objects.
 */
public class EditProductDescriptorBuilder {
    private EditProductDescriptor descriptor;

    public EditProductDescriptorBuilder() {
        descriptor = new EditProductDescriptor();
    }

    public EditProductDescriptorBuilder(EditProductDescriptor descriptor) {
        this.descriptor = new EditProductDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditProductDescriptor} with fields containing {@code product}'s details
     */
    public EditProductDescriptorBuilder(Product product) {
        descriptor = new EditProductDescriptor();
        descriptor.setName(product.getName());
        descriptor.setUnitPrice(product.getUnitPrice());
        descriptor.setQuantity(product.getQuantity());
    }

    /**
     * Sets the {@code Name} of the {@code EditProductDescriptor} that we are building.
     */
    public EditProductDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code UnitPrice} of the {@code EditProductDescriptor} that we are building.
     */
    public EditProductDescriptorBuilder withUnitPrice(String phone) {
        descriptor.setUnitPrice(new UnitPrice(phone));
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code EditProductDescriptor} that we are building.
     */
    public EditProductDescriptorBuilder withQuantity(String quantity) {
        descriptor.setQuantity(new Quantity(quantity));
        return this;
    }

    public EditProductDescriptor build() {
        return descriptor;
    }
}
