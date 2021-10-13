package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import seedu.address.model.commons.Name;
import seedu.address.model.product.Quantity;
import seedu.address.model.product.UnitPrice;

public class AddProductDescriptorTest {
    private Name name = new Name("pen");
    private UnitPrice unitPrice = new UnitPrice("15");
    private AddProductCommand.AddProductDescriptor descriptor =
            new AddProductCommand.AddProductDescriptor(name, unitPrice);

    @Test
    public void getName_hasName_nameReturned() {
        assertEquals(name, descriptor.getName());
    }

    @Test
    public void getUnitPrice_hasUnitPrice_unitPriceReturned() {
        assertEquals(unitPrice, descriptor.getUnitPrice());
    }

    @Test
    public void getQuantity_nullQuantity_nullReturned() {
        assertNull(descriptor.getQuantity());
    }

    @Test
    public void getQuantity_hasQuantity_quantityReturned() {
        Quantity quantity = new Quantity("100");
        descriptor.setQuantity(quantity);
        assertEquals(quantity, descriptor.getQuantity());
    }
}
