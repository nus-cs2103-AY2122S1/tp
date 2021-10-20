package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Order;
import seedu.address.model.product.Quantity;
import seedu.address.model.tag.Tag;

public class JsonAdaptedOrder {
    private final LocalDate time;
    private final int productId;
    private final Quantity quantity;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given {@code time}, {@code productId} and {@code quantity}.
     */
    @JsonCreator
    public JsonAdaptedOrder(LocalDate time, int productId, Quantity quantity) {
        this.time = time;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        time = source.time;
        productId = source.productId;
        quantity = source.quantity;
    }

    @JsonValue
    public LocalDate getTime() {
        return time;
    }

    @JsonValue
    public int getProductId() {
        return productId;
    }

    @JsonValue
    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     */
    public Order toModelType() throws IllegalValueException {
        return new Order(time, productId, quantity);
    }
}
