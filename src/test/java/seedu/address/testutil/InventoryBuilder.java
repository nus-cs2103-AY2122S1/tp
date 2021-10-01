package seedu.address.testutil;

import seedu.address.newmodel.Inventory;
import seedu.address.newmodel.item.Item;

/**
 * A utility class to help with building Inventory objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
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
