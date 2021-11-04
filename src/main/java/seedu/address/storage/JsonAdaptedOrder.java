package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Name;
import seedu.address.model.order.Order;
import seedu.address.model.product.Quantity;

public class JsonAdaptedOrder {
    private final String productName;
    private final String quantity;
    private final LocalDate time;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given {@code id} and {@code quantity}, {@code time}.
     */
    @JsonCreator
    private JsonAdaptedOrder(@JsonProperty("productName") String productName,
                             @JsonProperty("quantity") String quantity,
                             @JsonProperty("time") LocalDate time) {
        this.productName = productName;
        this.quantity = quantity;
        this.time = time;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        this(source.getProductName().fullName, source.getQuantity().value, source.getTime());
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     */
    public Order toModelType() throws IllegalValueException {
        return new Order(new Name(productName), new Quantity(quantity), time);
    }
}
