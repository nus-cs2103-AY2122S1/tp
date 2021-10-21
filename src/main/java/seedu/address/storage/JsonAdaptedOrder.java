package seedu.address.storage;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Order;
import seedu.address.model.product.Quantity;

public class JsonAdaptedOrder {
    private final ArrayList<Object> orderProperties = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given {@code time}, {@code productId} and {@code quantity}.
     */
    @JsonCreator
    public JsonAdaptedOrder(LocalDate time, int productId, Quantity quantity) {
        orderProperties.add(productId);
        orderProperties.add(quantity.value);
        orderProperties.add(time);
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        orderProperties.add(source.productId);
        orderProperties.add(source.quantity.value);
        orderProperties.add(source.time);
    }

    @JsonValue
    public ArrayList<Object> getOrderProperties() {
        return orderProperties;
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     */
    public Order toModelType() throws IllegalValueException {
        int productId = (Integer) orderProperties.get(0);
        String quantityVal = (String) orderProperties.get(1);
        LocalDate time = (LocalDate) orderProperties.get(2);
        Quantity quantity = new Quantity(quantityVal);
        return new Order(time, productId, quantity);
    }
}
