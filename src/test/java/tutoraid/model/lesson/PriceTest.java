package tutoraid.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.model.lesson.Price.isValidPrice;

import org.junit.jupiter.api.Test;

class PriceTest {

    @Test
    void isValidPrice_validDollars() {
        assertTrue(isValidPrice("10000"));
    }

    @Test
    void isValidPrice_validDollarsAndCents() {
        assertTrue(isValidPrice("1.08"));
    }

    @Test
    void isValidPrice_invalidSingleCent() {
        assertFalse(isValidPrice("3.1"));
    }

    @Test
    void isValidPrice_invalidCentsButNoDollars() {
        assertFalse(isValidPrice(".99"));
    }

    @Test
    void isValidPrice_invalidPointButNoCents() {
        assertFalse(isValidPrice("9."));
    }

    @Test
    void isValidPrice_invalidNotAnAmount() {
        assertFalse(isValidPrice("not an amount"));
    }

    @Test
    void isValidPrice_invalidNegativeAmount() {
        assertFalse(isValidPrice("-3"));
    }

    @Test
    void formatPrice_smallNumber() {
        Price p = new Price("1");
        assertEquals("$1", p.formatPrice());
    }

    @Test
    void formatPrice_largeNumber() {
        Price p = new Price("1234567");
        assertEquals("$1,234,567", p.formatPrice());
    }

    @Test
    void formatPrice_cents() {
        Price p = new Price("0.12");
        assertEquals("$0.12", p.formatPrice());
    }

    @Test
    void toString_priceObject() {
        String s = "1.23";
        Price p = new Price(s);
        assertEquals(p.toString(), s);
    }

    @Test
    void equals_identical() {
        Price p1 = new Price("1");
        Price p2 = p1;
        assertTrue(p1.equals(p2));
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void equals_equivalent() {
        Price p1 = new Price("1");
        Price p2 = new Price("1");
        assertTrue(p1.equals(p2));
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void equals_null() {
        Price p1 = new Price("1");
        assertNotEquals(p1, null);
    }

    @Test
    void equals_differentObjectSameType() {
        Price p1 = new Price("1");
        Price p2 = new Price("2");
        assertNotEquals(p1, p2);
        assertNotEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void equals_differentType() {
        Price p1 = new Price("1");
        String p2 = "2";
        assertNotEquals(p1.hashCode(), p2.hashCode());
    }
}
