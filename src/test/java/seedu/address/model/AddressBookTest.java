package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalProducts.IPHONE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.client.exceptions.DuplicateClientException;
import seedu.address.model.product.Product;
import seedu.address.model.product.exceptions.DuplicateProductException;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.ProductBuilder;
import seedu.address.testutil.TypicalClients;
import seedu.address.testutil.TypicalProducts;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void clientConstructor() {
        assertEquals(Collections.emptyList(), addressBook.getClientList());
    }

    @Test
    public void productConstructor() {
        assertEquals(Collections.emptyList(), addressBook.getProductList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBookClient_replacesData() {
        AddressBook newData = TypicalClients.getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withValidReadOnlyAddressBookProduct_replacesData() {
        AddressBook newData = TypicalProducts.getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateClients_throwsDuplicateClientException() {
        // Two clients with the same identity fields
        Client editedAlice = new ClientBuilder(ALICE).build();
        List<Client> newClients = Arrays.asList(ALICE, editedAlice);
        List<Product> newProducts = Arrays.asList(IPHONE);
        AddressBookStub newData = new AddressBookStub(newClients, newProducts);

        assertThrows(DuplicateClientException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateProducts_throwsDuplicateProductException() {
        // Two products with the same identity fields
        List<Client> newClients = Arrays.asList(ALICE);
        Product editedIphone = new ProductBuilder(IPHONE).build();
        List<Product> newProducts = Arrays.asList(IPHONE, editedIphone);
        AddressBookStub newData = new AddressBookStub(newClients, newProducts);

        assertThrows(DuplicateProductException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasClient(null));
    }

    @Test
    public void hasProduct_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasProduct(null));
    }

    @Test
    public void hasClient_clientNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasClient(ALICE));
    }

    @Test
    public void hasProduct_productNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasProduct(IPHONE));
    }

    @Test
    public void hasClient_clientInAddressBook_returnsTrue() {
        addressBook.addClient(ALICE);
        assertTrue(addressBook.hasClient(ALICE));
    }

    @Test
    public void hasProduct_productInAddressBook_returnsTrue() {
        addressBook.addProduct(IPHONE);
        assertTrue(addressBook.hasProduct(IPHONE));
    }

    @Test
    public void hasClient_clientWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addClient(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).build();
        assertTrue(addressBook.hasClient(editedAlice));
    }

    @Test
    public void hasProduct_productWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addProduct(IPHONE);
        Product editedAlice = new ProductBuilder(IPHONE).build();
        assertTrue(addressBook.hasProduct(editedAlice));
    }

    @Test
    public void getClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getClientList().remove(0));
    }

    @Test
    public void getProductList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getProductList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose clients/products list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Client> clients = FXCollections.observableArrayList();
        private final ObservableList<Product> products = FXCollections.observableArrayList();

        AddressBookStub(Collection<Client> clients, Collection<Product> products) {
            this.clients.setAll(clients);
            this.products.setAll(products);
        }

        @Override
        public ObservableList<Client> getClientList() {
            return this.clients;
        }

        @Override
        public ObservableList<Product> getProductList() {
            return this.products;
        }
    }

}
