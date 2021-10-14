package seedu.fast.model.person;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class AppointmentCountTest {
    private static final String INITIAL_COUNT = "0";
    private static final String VALID_COUNT = "10";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentCount(null));
    }

    @Test
    public void getCount() {
        AppointmentCount initialCount = new AppointmentCount("0");
        assertTrue(initialCount.toString().equals(INITIAL_COUNT));

        AppointmentCount count = new AppointmentCount("10");
        assertTrue(count.toString().equals(VALID_COUNT));
    }

    @Test
    public void incrementAppointmentCount() {
        AppointmentCount initialCount = new AppointmentCount("0");
        initialCount.incrementAppointmentCount();
        assertTrue(initialCount.toString().equals("1"));
    }
}
