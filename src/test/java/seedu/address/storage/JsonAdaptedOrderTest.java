package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalOrders.getTypicalTransaction;

import org.junit.jupiter.api.Test;

public class JsonAdaptedOrderTest {

    @Test
    public void toModelType_validOrderDetails_returnsItem() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(getTypicalTransaction());
        assertEquals(getTypicalTransaction(), order.toModelType());
    }

}
