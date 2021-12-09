package seedu.times.model.tuitionclass;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rate(null));
    }

    @Test
    public void constructor_negativeRate_throwsIllegalArgumentException() {
        String negativeRate = "-32";
        assertThrows(IllegalArgumentException.class, () -> new Rate(negativeRate));
    }

    @Test
    public void constructor_emptyRate_throwsIllegalArgumentException() {
        String emptyRate1 = "";
        String emptyRate2 = " ";
        assertThrows(IllegalArgumentException.class, () -> new Rate(emptyRate1));
        assertThrows(IllegalArgumentException.class, () -> new Rate(emptyRate2));
    }

    @Test
    public void isValidRate() {
        //null rate
        assertThrows(NullPointerException.class, () -> Rate.isValidRate(null));

        //invalid rate
        assertFalse(Rate.isValidRate(""));
        assertFalse(Rate.isValidRate(" "));
        assertFalse(Rate.isValidRate("-42"));
        assertFalse(Rate.isValidRate("32.3232"));
        assertFalse(Rate.isValidRate(".32"));
        assertFalse(Rate.isValidRate(".3232"));
        assertFalse(Rate.isValidRate("1000000"));
        assertFalse(Rate.isValidRate("9999000"));

        //valid rate
        assertTrue(Rate.isValidRate("50"));
        assertTrue(Rate.isValidRate("50.3"));
        assertTrue(Rate.isValidRate("40.40"));
        assertTrue(Rate.isValidRate("0"));
        assertTrue(Rate.isValidRate("999999.99"));
    }
}

