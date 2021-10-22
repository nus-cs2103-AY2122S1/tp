package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_SALE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_SALE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CUSTOMER_SALE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CUSTOMER_SALE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OCT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SEPT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.SalesOrderBook;
import seedu.address.model.order.Order;

public class TypicalOrders {

    public static final Order SALESORDER1 = new OrderBuilder().withCustomer("Josh")
            .withDate("18th of September 2021").withAmount("10").build();
    public static final Order SALESORDER2 = new OrderBuilder().withCustomer("Mac")
            .withDate("19th of September 2021").withAmount("15").build();
    public static final Order SALESORDER3 = new OrderBuilder().withCustomer("Clark")
            .withDate("20th of September 2021").withAmount("20").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Order ORDER = new OrderBuilder().withCustomer(VALID_CUSTOMER_SALE1)
            .withDate(VALID_DATE_SEPT).withAmount(VALID_AMOUNT_SALE1).build();
    public static final Order ORDER2 = new OrderBuilder().withCustomer(VALID_CUSTOMER_SALE2)
            .withDate(VALID_DATE_OCT).withAmount(VALID_AMOUNT_SALE2).build();

    /**
     * Returns an {@code AddressBook} with all the typical tasks added.
     */
    public static SalesOrderBook getTypicalSalesOrderBook() {
        SalesOrderBook sb = new SalesOrderBook();
        for (Order order : getTypicalOrder()) {
            sb.addOrder(order);
        }
        return sb;
    }

    public static List<Order> getTypicalOrder() {
        return new ArrayList<>(Arrays.asList(SALESORDER1, SALESORDER2, SALESORDER3));
    }

}
