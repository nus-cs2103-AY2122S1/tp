package seedu.address.testutil;

import seedu.address.model.SalesOrderBook;
import seedu.address.model.order.Order;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class SalesOrderBookBuilder {

    private SalesOrderBook salesOrderBook;

    public SalesOrderBookBuilder() {
        salesOrderBook = new SalesOrderBook();
    }

    public SalesOrderBookBuilder(SalesOrderBook salesOrderBook) {
        this.salesOrderBook = salesOrderBook;
    }

    /**
     * Adds a new {@code Task} to the {@code TaskListManager} that we are building.
     */
    public SalesOrderBookBuilder withOrder(Order order) {
        salesOrderBook.addOrder(order);
        return this;
    }

    public SalesOrderBook build() {
        return salesOrderBook;
    }
}
