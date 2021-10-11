package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.Address;
import seedu.address.model.client.Email;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.Name;

public class AddClientDescriptorTest {
    Name name = new Name("John Doe");
    PhoneNumber phoneNumber = new PhoneNumber("12345678");
    AddClientCommand.AddClientDescriptor descriptor = new AddClientCommand.AddClientDescriptor(name, phoneNumber);

    @Test
    public void getName_hasName_nameReturned() {
        assertEquals(descriptor.getName(), name);
    }

    @Test
    public void getPhoneNumber_hasPhoneNumber_phoneNumberReturned() {
        assertEquals(descriptor.getPhoneNumber(), phoneNumber);
    }

    @Test
    public void getEmail_nullEmail_nullReturned() {
        assertNull(descriptor.getEmail());
    }

    @Test
    public void getEmail_hasEmail_emailReturned() {
        Email email = new Email("john.doe@email.example.com");
        descriptor.setEmail(email);
        assertEquals(descriptor.getEmail(), email);
    }

    @Test
    public void getAddress_nullAddress_nullReturned() {
        assertNull(descriptor.getAddress());
    }

    @Test
    public void getAddress_hasAddress_addressReturned() {
        Address address = new Address("Earth");
        descriptor.setAddress(address);
        assertEquals(descriptor.getAddress(), address);
    }
}
