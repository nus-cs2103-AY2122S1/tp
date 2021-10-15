package seedu.fast.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    public void equals() {
        AppointmentCount standard = new AppointmentCount("0");
        AppointmentCount countWithSameValue = new AppointmentCount("0");

        // same data
        assertTrue(standard.equals(countWithSameValue));

        // same appointment
        assertTrue(standard.equals(standard));

        // null
        assertFalse(standard.equals(null));

        // different type
        assertFalse(standard.equals("Matthew"));

        // different fields
        assertFalse(standard.equals(new AppointmentCount("11")));
    }

    @Test
    public void hashcode() {
        AppointmentCount standard = new AppointmentCount("0");
        AppointmentCount countWithSameValue = new AppointmentCount("0");
        AppointmentCount countWithDifferentValue = new AppointmentCount("11");

        assertTrue(standard.hashCode() == countWithSameValue.hashCode());
        assertTrue(standard.hashCode() == standard.hashCode());

        assertFalse(standard.hashCode() == countWithDifferentValue.hashCode());
    }

    @Test
    public void isValidCount() {
        // valid input
        assertTrue(AppointmentCount.isValidCount("0"));

        // negative input
        assertFalse(AppointmentCount.isValidCount("-10"));

        // empty input
        assertFalse(AppointmentCount.isValidCount(""));
        assertFalse(AppointmentCount.isValidCount(" "));

        // non integer input
        assertFalse(AppointmentCount.isValidCount("Matthew"));
        assertFalse(AppointmentCount.isValidCount("!"));
    }
}
