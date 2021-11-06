package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDERING;

import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.model.order.Order;

/**
 * A utility class for Order.
 */
public class OrderUtil {
    /**
     * Returns an add command string for adding the {@code order}.
     */
    public static String getAddOrderCommand(Order order) {
        return AddOrderCommand.COMMAND_WORD + " " + getOrderDetails(order);
    }

    /**
     * Returns the part of command string for the given {@code order}'s details.
     */
    public static String getOrderDetails(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_LABEL + order.getLabel().checkedLabel + " ");
        sb.append(PREFIX_DATE + order.getDate().dateString + " ");
        sb.append(PREFIX_AMOUNT + order.getAmount().amount + " ");
        sb.append(PREFIX_CUSTOMER + order.getCustomer().getName() + " ");
        System.out.println(sb);
        return sb.toString();
    }

    public static String getSortOrdersDetails(String field, String order) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_SORT_FIELD + field + " ");
        sb.append(PREFIX_SORT_ORDERING + order + " ");
        return sb.toString();
    }
}
