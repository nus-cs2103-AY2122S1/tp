package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_emptyString_success() {
        String emptyAddress = "";
        boolean success = false;

        try {
            Address address = new Address(emptyAddress);
            success = true;
        } catch (Exception e) {
            // Test Case Failed
            success = false;
        }
        assertTrue(success);
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Address.isValidAddress(null));

        // invalid addresses
        assertFalse(Address.isValidAddress("")); // empty string
        assertFalse(Address.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Address.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Address.isValidAddress("-")); // one character
        assertTrue(Address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }

    @Test
    public void equals_twoSameObjects_success() {
        Address address = new Address("Prince George's Park Residences, NUS, 118430");
        assertTrue(address.equals(address));
    }

    @Test
    public void equals_twoDifferentObjects_falseOutput() {
        Address address = new Address("Prince George's Park Residences, NUS, 118430");
        Email email = new Email("jay@gmail.com");
        assertFalse(address.equals(email));
    }

    @Test
    public void equals_twoDifferentObjectsWithSameAddress_success() {
        Address address1 = new Address("Prince George's Park Residences, NUS, 118430");
        Address address2 = new Address("Prince George's Park Residences, NUS, 118430");
        assertTrue(address1.equals(address2));
    }

    @Test
    public void equals_twoDifferentObjectsWithDifferentAddresses_falseOutput() {
        Address address1 = new Address("Prince George's Park Residences, NUS, 118430");
        Address address2 = new Address("UTown, NUS, Singapore");
        assertFalse(address1.equals(address2));
    }

    @Test
    public void toString_aValidInput_success() {
        Address address = new Address("Prince George's Park Residences, NUS, 118430");
        assertTrue(address.toString().equals("Prince George's Park Residences, NUS, 118430"));
    }

    @Test
    public void hashCode_validInput_correctOutput() {
        Address address = new Address("Prince George's Park Residences, NUS, 118430");
        assertTrue(address.hashCode() == -778107328);
    }
}
