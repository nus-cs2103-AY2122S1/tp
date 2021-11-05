package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE_NUMBER;

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
        sb.append(client.getName().fullName).append(" ")
                .append(PREFIX_PHONE_NUMBER).append(client.getPhoneNumber().value).append(" ")
                .append(PREFIX_EMAIL).append(client.getEmail().value).append(" ")
                .append(PREFIX_ADDRESS).append(client.getAddress().value).append(" ");
        client.getOrders().forEach(s -> sb.append(PREFIX_ORDER)
                .append(s.getProductName()).append(" ")
                .append(s.getQuantity()).append(" ")
                .append(s.getTime()).append(" "));

        return sb.toString();
    }

    private static String orderToString(Order order) {
        return PREFIX_ORDER.toString() + order.getProductName() + " " + order.getQuantity() + " "
                       + order.getTime() + " ";
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
        descriptor.getOrders().ifPresent(orders -> orders.forEach(s -> sb.append(orderToString(s)).append(" ")));

        return sb.toString();
    }
}
