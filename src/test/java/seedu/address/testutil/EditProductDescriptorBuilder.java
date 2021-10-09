package seedu.address.testutil;

import seedu.address.logic.commands.products.EditProductCommand.EditProductDescriptor;
import seedu.address.model.products.Description;
import seedu.address.model.products.Manufacturer;
import seedu.address.model.products.Product;
import seedu.address.model.products.ProductName;
import seedu.address.model.products.Type;

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
        descriptor.setProductName(product.getName());
        descriptor.setType(product.getType());
        descriptor.setManufacturer(product.getManufacturer());
        descriptor.setDescription(product.getDescription());
    }

    /**
     * Sets the {@code ProductName} of the {@code EditProductDescriptor} that we are building.
     */
    public EditProductDescriptorBuilder withProductName(String productName) {
        descriptor.setProductName(ProductName.getName(productName));
        return this;
    }

    /**
     * Sets the {@code ProductName} of the {@code EditProductDescriptor} that we are building.
     */
    public EditProductDescriptorBuilder withProductName(ProductName productName) {
        descriptor.setProductName(productName);
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code EditProductDescriptor} that we are building.
     */
    public EditProductDescriptorBuilder withType(String type) {
        descriptor.setType(Type.getType(type));
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code EditProductDescriptor} that we are building.
     */
    public EditProductDescriptorBuilder withType(Type type) {
        descriptor.setType(type);
        return this;
    }

    /**
     * Sets the {@code Manufacturer} of the {@code EditProductDescriptor} that we are building.
     */
    public EditProductDescriptorBuilder withManufacturer(String manufacturer) {
        descriptor.setManufacturer(Manufacturer.getManufacturer(manufacturer));
        return this;
    }

    /**
     * Sets the {@code Manufacturer} of the {@code EditProductDescriptor} that we are building.
     */
    public EditProductDescriptorBuilder withManufacturer(Manufacturer manufacturer) {
        descriptor.setManufacturer(manufacturer);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditProductDescriptor} that we are building.
     */
    public EditProductDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(Description.getDescription(description));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditProductDescriptor} that we are building.
     */
    public EditProductDescriptorBuilder withDescription(Description description) {
        descriptor.setDescription(description);
        return this;
    }

    public EditProductDescriptor build() {
        return this.descriptor;
    }
}
