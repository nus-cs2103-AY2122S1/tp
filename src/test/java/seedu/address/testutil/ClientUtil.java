package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE_NUMBER;

import java.time.LocalDate;
import java.util.Set;

import seedu.address.logic.commands.AddClientCommand;
import seedu.address.logic.commands.EditClientCommand;
import seedu.address.model.client.Client;
import seedu.address.model.order.Order;

/**
 * A utility class for Client.
 */
public class ClientUtil {

    /**
     * Returns an add client command string for adding the {@code client}.
     */
    public static String getAddClientCommand(Client client) {
        return AddClientCommand.COMMAND_WORD + " " + getClientDetails(client);
    }

    /**
     * Returns the part of command string for the given {@code client}'s details.
     */
    public static String getClientDetails(Client client) {
        StringBuilder sb = new StringBuilder();
        sb.append(client.getName().fullName + " ");
        sb.append(PREFIX_PHONE_NUMBER + client.getPhoneNumber().value + " ");
        sb.append(PREFIX_EMAIL + client.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + client.getAddress().value + " ");
        client.getOrders().stream().forEach(
                s -> sb.append(PREFIX_ORDER.toString() + s.id + " " + s.quantity + " " + s.time + " ")
        );
        return sb.toString();
    }

    private static String orderToString(Order order) {
        return PREFIX_ORDER.toString() + order.id + " " + order.quantity + " " + order.time + " ";
    }

    private static String localDateToString(LocalDate time) {
        return time.getYear() + "/" + time.getMonthValue() + "/" + time.getDayOfMonth();
    }

    /**
     * Returns the part of command string for the given {@code EditClientDescriptor}'s details.
     */
    public static String getEditClientDescriptorDetails(EditClientCommand.EditClientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhoneNumber().ifPresent(phone -> sb.append(PREFIX_PHONE_NUMBER).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getOrders().isPresent()) {
            Set<Order> orders = descriptor.getOrders().get();
            orders.forEach(s -> sb.append(orderToString(s)).append(" "));
        }
        return sb.toString();
    }
}
