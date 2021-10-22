package seedu.address.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import seedu.address.model.order.Amount;
import seedu.address.model.person.Person;

/**
 * Represents the total orders of a client ({@code Person}).
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ClientTotalOrders {
    private final SimpleStringProperty clientName;
    private final SimpleDoubleProperty totalOrders;

    /**
     * Constructs a {@code ClientTotalOrders}
     * 
     * @param clientName the name of the client
     * @param totalOrders the total amount of orders by this client.
     */
    public ClientTotalOrders(String clientName, double totalOrders) {
        this.clientName = new SimpleStringProperty(clientName);
        this.totalOrders = new SimpleDoubleProperty(totalOrders);
    }
}
