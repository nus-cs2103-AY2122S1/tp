package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.model.Category;
import seedu.address.model.commons.ID;
import seedu.address.model.commons.Name;
import seedu.address.model.order.Order;

/**
 * Represents a Client in the app.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client implements Category {
    // Identity fields
    private final ID id;

    // Data fields
    private final Name name;
    private final PhoneNumber phoneNumber;
    private final Email email;
    private final Address address;
    private final Set<Order> orders;

    public Client(Name name, PhoneNumber phoneNumber, Email email, Address address, Set<Order> orders) {
        this(ID.getNewClientID(), name, phoneNumber, email, address, orders);
    }

    private Client(ID id, Name name, PhoneNumber phoneNumber, Email email, Address address, Set<Order> orders) {
        requireAllNonNull(id, name, phoneNumber);

        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.orders = new HashSet<>();
        if (orders != null) {
            this.orders.addAll(orders);
        }
        Logger logger = Logger.getLogger("create client object");
        logger.info("new client created");
    }

    public ID getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    /**
     * Adds an order into the client's set of orders.
     *
     * @param orderToAdd The order to be added.
     */
    public void addOrder(Order orderToAdd) {
        orders.add(orderToAdd);
    }

    /**
     * Checks if the product name appears in the client's set of orders.
     *
     * @param productName The name of the product.
     * @return True if it does appear; false otherwise.
     */
    public boolean hasOrder(Name productName) {
        return orders.stream().anyMatch(order -> order.getProductName().equals(productName));
    }

    /**
     * Removes the order with the same product name from the client's set of orders.
     *
     * @param productName The name of the product of the order to be removed.
     * @return The order removed if it exists; null otherwise.
     */
    public Order removeOrder(Name productName) {
        Optional<Order> optionalOrder = orders.stream()
                .filter(order -> order.getProductName().equals(productName))
                .findAny();

        if (optionalOrder.isPresent()) {
            Order orderToRemove = optionalOrder.get();
            orders.remove(orderToRemove);
            return orderToRemove;
        }

        return null;
    }

    /**
     * Returns a new copy of the {@code Client} with the same ID but the supplied data fields. <br>
     * The only way to copy the ID of a {@code Client} over to another {@code Client}.
     *
     * @param client ID of the original client.
     * @param name New name for the client.
     * @param phoneNumber New phone number for the client.
     * @param email New email for the client.
     * @param address New address for the client.
     */
    public static Client updateClient(Client client, Name name, PhoneNumber phoneNumber, Email email, Address address,
                                      Set<Order> orders) {
        return new Client(client.getId(), name, phoneNumber, email, address, orders);
    }

    /**
     * @see #updateClient(Client, Client)
     */
    public static Client updateClient(Client copyTo, Client copyFrom) {
        return new Client(copyTo.getId(), copyFrom.name, copyFrom.phoneNumber, copyFrom.email, copyFrom.address,
                copyFrom.orders);
    }

    /**
     * Returns true if both clients have the same id.
     * This defines a weaker notion of equality between two clients.
     *
     * @param otherClient Another Client being compared to.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null && otherClient.getId().equals(this.getId());
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     *
     * @param other Any Object being compared to. May or may not be a instance of Client.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return id.equals(otherClient.id)
                && name.equals(otherClient.name)
                && phoneNumber.equals(otherClient.phoneNumber)
                && email.equals(otherClient.email)
                && address.equals(otherClient.address)
                && orders.containsAll(otherClient.orders)
                && otherClient.orders.containsAll(orders);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, name, phoneNumber, email, address, orders);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ID: ").append(id)
                .append("; Name: ").append(name)
                .append("; Phone Number: ").append(phoneNumber);

        if (email != null) {
            builder.append("; Email: ").append(email);
        }

        if (address != null) {
            builder.append("; Address: ").append(address);
        }

        if (!orders.isEmpty()) {
            builder.append("\nOrders: ");
            orders.forEach(order -> builder.append(order).append(", "));
            builder.deleteCharAt(builder.length() - 2);
        }

        return builder.toString();
    }
}
