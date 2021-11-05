package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.client.Client;
import seedu.address.model.product.Product;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 * {@code AddressBook ab = new AddressBookBuilder().withClient(client).withProduct(product).build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    /**
     * Adds a new {@code Client} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withClient(Client client) {
        addressBook.addClient(client);
        return this;
    }

    /**
     * Adds a new {@code Product} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withProduct(Product product) {
        addressBook.addProduct(product);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
