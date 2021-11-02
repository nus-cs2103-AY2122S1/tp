package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BENSON;
import static seedu.address.testutil.TypicalClients.CARL;
import static seedu.address.testutil.TypicalClients.DANIEL;
import static seedu.address.testutil.TypicalClients.ELLE;
import static seedu.address.testutil.TypicalClients.GEORGE;
import static seedu.address.testutil.TypicalProducts.IPAD;
import static seedu.address.testutil.TypicalProducts.IPHONE;
import static seedu.address.testutil.TypicalProducts.MACBOOK;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Client;
import seedu.address.model.product.Product;
import seedu.address.model.util.SampleDataUtil;

public class SampleDataUtilTest {
    @Test
    public void getSampleClients_differentClients_throwsAssertionFailedError() {
        Client[] clients = SampleDataUtil.getSampleClients();
        Client[] exampleClients = new Client[]{ALICE, BENSON, CARL, DANIEL, ELLE, GEORGE};
        assertThrows(AssertionFailedError.class, () -> assertArrayEquals(clients, exampleClients));
    }

    @Test
    public void getSampleClients_null_throwsAssertionFailedError() {
        Client[] clients = SampleDataUtil.getSampleClients();
        assertThrows(AssertionFailedError.class, () -> assertArrayEquals(clients, null));
    }

    @Test
    public void getSampleProducts_differentProducts_throwsAssertionFailedError() {
        Product[] products = SampleDataUtil.getSampleProducts();
        Product[] exampleProducts = new Product[]{IPHONE, IPAD, MACBOOK};
        assertThrows(AssertionFailedError.class, () -> assertArrayEquals(products, exampleProducts));
    }

    @Test
    public void getSampleProducts_null_throwsAssertionFailedError() {
        Product[] products = SampleDataUtil.getSampleProducts();
        assertThrows(AssertionFailedError.class, () -> assertArrayEquals(products, null));
    }

    @Test
    public void getSampleAddressBook_differentAddressBook_throwsAssertionFailedError() {
        ReadOnlyAddressBook addressBook = SampleDataUtil.getSampleAddressBook();
        AddressBook sampleAb = new AddressBook();
        assertThrows(AssertionFailedError.class, () -> assertEquals(addressBook, sampleAb));
    }

    @Test
    public void getSampleAddressBook_null_throwsAssertionFailedError() {
        ReadOnlyAddressBook addressBook = SampleDataUtil.getSampleAddressBook();
        assertThrows(AssertionFailedError.class, () -> assertEquals(addressBook, null));
    }
}
