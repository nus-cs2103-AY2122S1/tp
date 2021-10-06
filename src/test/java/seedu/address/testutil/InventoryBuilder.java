package seedu.address.testutil;

import seedu.address.model.Inventory;
import seedu.address.model.item.Item;

/**
 * A utility class to help with building Inventory objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new InventoryBuilder().withItem("Pie", "Pudding").build();}
 */
public class InventoryBuilder {

    private Inventory inventory;

    public InventoryBuilder() {
        inventory = new Inventory();
    }

    public InventoryBuilder(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Adds a new {@code Item} to the {@code Inventory} that we are building.
     */
    public InventoryBuilder withItem(Item item) {
        inventory.addItem(item);
        return this;
    }

    public Inventory build() {
        return inventory;
    }
}
