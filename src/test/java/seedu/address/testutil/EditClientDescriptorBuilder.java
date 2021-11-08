package seedu.address.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditClientCommand.EditClientDescriptor;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.Name;
import seedu.address.model.order.Order;
import seedu.address.model.product.Quantity;

/**
 * A utility class to help with building EditClientDescriptor objects.
 */
public class EditClientDescriptorBuilder {
    private final EditClientDescriptor descriptor;

    public EditClientDescriptorBuilder() {
        descriptor = new EditClientDescriptor();
    }

    public EditClientDescriptorBuilder(EditClientDescriptor descriptor) {
        this.descriptor = new EditClientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditClientDescriptor} with fields containing {@code client}'s details
     */
    public EditClientDescriptorBuilder(Client client) {
        descriptor = new EditClientDescriptor();
        descriptor.setName(client.getName());
        descriptor.setPhoneNumber(client.getPhoneNumber());
        descriptor.setEmail(client.getEmail());
        descriptor.setAddress(client.getAddress());
        descriptor.setOrders(client.getOrders());
    }

    /**
     * Sets the {@code Name} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code PhoneNumber} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withPhoneNumber(String phone) {
        descriptor.setPhoneNumber(new PhoneNumber(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Order} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withOrders(String... orders) {
        Set<Order> orderSet = Stream.of(orders)
                .map(order -> {
                    String[] args = order.split(" ");
                    if (args[2].length() <= 5) {
                        int year = Calendar.getInstance().get(Calendar.YEAR);
                        args[2] = String.format("%d/%s", year, args[2]);
                    }

                    Name productName = new Name(args[0]);
                    Quantity quantity = new Quantity(args[1]);
                    LocalDate time = LocalDate.parse(args[2], DateTimeFormatter.ofPattern("yyyy/M/d"));

                    return new Order(productName, quantity, time);
                }).collect(Collectors.toSet());
        descriptor.setOrders(orderSet);
        return this;
    }

    public EditClientDescriptor build() {
        return descriptor;
    }
}
