package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Order;
import seedu.address.model.product.Quantity;

public class JsonAdaptedOrder {
    private final LocalDate time;
    private final int productId;
    private final String quantity;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given {@code time}, {@code productId} and {@code quantity}.
     */
    @JsonCreator
    private JsonAdaptedOrder(@JsonProperty("time") LocalDate time, @JsonProperty("productId") int productId,
                             @JsonProperty("quantity") String quantity) {
        this.time = time;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        this(source.time, source.productId, source.quantity.value);
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     */
    public Order toModelType() throws IllegalValueException {
        Quantity q = new Quantity(quantity);
        return new Order(time, productId, q);
    }
}
