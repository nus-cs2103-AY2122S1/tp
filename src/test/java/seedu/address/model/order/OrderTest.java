package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalProducts.AIRPODS;
import static seedu.address.testutil.TypicalProducts.IPHONE;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.product.Quantity;

public class OrderTest {
    LocalDate now = LocalDate.now();
    Order oneIphone = new Order(IPHONE.getId(), new Quantity("1"), now);
    Order oneIphoneCopy = new Order(IPHONE.getId(), new Quantity("1"), now);
    Order twoAirpods = new Order(AIRPODS.getId(), new Quantity("2"), LocalDate.MAX);

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(oneIphone.equals(oneIphone));

        // order compares to an object that is not an order
        assertTrue(!oneIphone.equals(IPHONE));

        // different objects with the same values
        assertTrue(oneIphone.equals(oneIphoneCopy));

        // two different orders
        assertTrue(!oneIphone.equals(twoAirpods));
    }
}
