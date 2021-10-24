package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalProducts.AIRPODS;
import static seedu.address.testutil.TypicalProducts.IPHONE;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.product.Quantity;

public class OrderTest {
    private final LocalDate now = LocalDate.now();
    private final Order oneIphone = new Order(IPHONE.getId(), new Quantity("1"), now);
    private final Order oneIphoneCopy = new Order(IPHONE.getId(), new Quantity("1"), now);
    private final Order twoAirpods = new Order(AIRPODS.getId(), new Quantity("2"), LocalDate.MAX);

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(oneIphone, oneIphone);

        // order compares to an object that is not an order
        assertNotEquals(oneIphone, IPHONE);

        // different objects with the same values
        assertEquals(oneIphone, oneIphoneCopy);

        // two different orders
        assertNotEquals(oneIphone, twoAirpods);
    }
}
