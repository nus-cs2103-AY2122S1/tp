package seedu.address.model.chargeable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.commonattributes.Name;
import seedu.address.model.vendor.Cost;
import seedu.address.model.vendor.ServiceName;
import seedu.address.model.vendor.VendorId;



public class ChargeableTest {
    @Test
    public void constructor_allNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Chargeable(null, null,
                null, null, null));
    }

    @Test
    public void constructor_vendorIdNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Chargeable(null, new Name("Bob food service"),
                new ServiceName("delivery"), new Cost(12.99), new Quantity(2)));
    }

    @Test
    public void constructor_nameNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Chargeable(new VendorId("1"), null,
                new ServiceName("delivery"), new Cost(12.99), new Quantity(2)));
    }

    @Test
    public void constructor_serviceNameNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Chargeable(new VendorId("1"), new Name("Bob food service"),
                null, new Cost(12.99), new Quantity(2)));
    }

    @Test
    public void constructor_costNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Chargeable(new VendorId("1"), new Name("Bob food service"),
                new ServiceName("delivery"), null, new Quantity(2)));
    }

    @Test
    public void constructor_quantityNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Chargeable(new VendorId("1"), new Name("Bob food service"),
                new ServiceName("delivery"), new Cost(12.99), null));
    }

    @Test
    public void chargeable_identicalCheck() {
        Chargeable chargeable1 = new Chargeable(new VendorId("1"), new Name("Bob food service"),
                new ServiceName("delivery"), new Cost(12.99), new Quantity(2));
        Chargeable chargeable2 = new Chargeable(new VendorId("1"), new Name("Bob food service"),
                new ServiceName("delivery"), new Cost(12.99), new Quantity(2));
        assertEquals(chargeable1, chargeable2);
    }

    @Test
    public void constructor_ok_throwsNoException() {
        assertDoesNotThrow(() -> new Chargeable(new VendorId("1"), new Name("Bob food service"),
                new ServiceName("delivery"), new Cost(12.99), new Quantity(2)));
    }
}
