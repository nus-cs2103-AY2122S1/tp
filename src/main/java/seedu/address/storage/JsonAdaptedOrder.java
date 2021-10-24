package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.ID;
import seedu.address.model.order.Order;
import seedu.address.model.product.Quantity;

public class JsonAdaptedOrder {
    private final int id;
    private final String quantity;
    private final LocalDate time;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given {@code id} and {@code quantity}, {@code time}.
     */
    @JsonCreator
    private JsonAdaptedOrder(@JsonProperty("id") int id, @JsonProperty("quantity") String quantity,
                             @JsonProperty("time") LocalDate time) {
        this.id = id;
        this.quantity = quantity;
        this.time = time;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        this(source.id.getId(), source.quantity.value, source.time);
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     */
    public Order toModelType() throws IllegalValueException {
        return new Order(new ID(id), new Quantity(quantity), time);
    }
}
