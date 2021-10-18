package seedu.address.model.order;

import java.time.LocalDateTime;

import seedu.address.model.commons.ID;

/**
 * Represents an Order in Sellah.
 */
public class Order {
    private final LocalDateTime time;
    private final ID productId;
    private final int quantity;

    public Order(LocalDateTime time, ID productId, int quantity) {
        this.time = time;
        this.productId = productId;
        this.quantity = quantity;
    }
}