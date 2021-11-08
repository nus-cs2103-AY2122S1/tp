package seedu.address.testutil;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.Name;
import seedu.address.model.order.Order;
import seedu.address.model.product.Quantity;

/**
 * A utility class to help with building Client objects.
 */
public class ClientBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE_NUMBER = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Client client;
    private Name name;
    private PhoneNumber phoneNumber;
    private Email email;
    private Address address;
    private final Set<Order> orders;

    /**
     * Creates a {@code ClientBuilder} with the default details.
     */
    public ClientBuilder() {
        client = null;
        name = new Name(DEFAULT_NAME);
        phoneNumber = new PhoneNumber(DEFAULT_PHONE_NUMBER);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        orders = new HashSet<>();
    }

    /**
     * Initializes the ClientBuilder with the data of {@code clientToCopy}.
     */
    public ClientBuilder(Client clientToCopy) {
        client = clientToCopy;
        name = clientToCopy.getName();
        phoneNumber = clientToCopy.getPhoneNumber();
        email = clientToCopy.getEmail();
        address = clientToCopy.getAddress();
        orders = clientToCopy.getOrders();
    }

    /**
     * Sets the {@code ID} of the {@code Client} that we are building.
     */
    public ClientBuilder withId(Client client) {
        this.client = client;
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Client} that we are building.
     */
    public ClientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code PhoneNumber} of the {@code Client} that we are building.
     */
    public ClientBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = new PhoneNumber(phoneNumber);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Client} that we are building.
     */
    public ClientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Add an order to {@code Orders} of the {@code Client} that we are building.
     */
    public ClientBuilder withOrder(Name name, Quantity quantity, LocalDate time) {
        this.orders.add(new Order(name, quantity, time));
        return this;
    }

    /**
     * Builds the {@code Client}.
     */
    public Client build() {
        return client == null
               ? new Client(name, phoneNumber, email, address, orders)
               : Client.updateClient(client, name, phoneNumber, email, address, orders);
    }
}
