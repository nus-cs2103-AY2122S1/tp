package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalOrders.getTypicalTransaction;

import org.junit.jupiter.api.Test;

public class JsonAdaptedOrderTest {

    @Test
    public void toModelType_validOrderDetails_returnsItem() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(getTypicalTransaction());
        boolean test = getTypicalTransaction().isSameTransactionInfo(order.toModelType().getOrderItems());
        assertTrue(test);
    }

}
