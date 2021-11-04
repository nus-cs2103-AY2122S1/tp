package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.commons.Name;
import seedu.address.model.order.Order;
import seedu.address.model.product.Quantity;

/**
 * Jackson-friendly version of {@link Order}.
 */
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
    public Order toModelType() {
        assert(productName != null && Name.isValidName(productName));
        assert(quantity != null && Quantity.isValidQuantity(quantity));
        assert(time != null);

        Name modelProductName = new Name(productName);
        Quantity modelQuantity = new Quantity(quantity);
        return new Order(modelProductName, modelQuantity, time);
    }
}
