package socialite.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class RemarkTest {
    @Test
    public void equals() {
        Remark remark = new Remark("remark");
        Remark nullRemark = new Remark(null);
        Remark emptyRemark = new Remark("");

        // same object -> returns true
        assertEquals(remark, remark);

        // same values -> returns true
        Remark remarkCopy = new Remark(remark.get());
        assertEquals(remark, remarkCopy);

        // different types -> returns false
        assertNotEquals(1, remark);

        // null -> returns false
        assertNotEquals(null, remark);

        // different remark -> returns false
        Remark differentRemark = new Remark("different remark");
        assertNotEquals(remark, differentRemark);

        // null and empty remark should not be the same
        assertNotEquals(emptyRemark, nullRemark);
    }

    @Test
    public void testGet() {
        Remark remark = new Remark("remark");
        Remark nullRemark = new Remark(null);
        Remark emptyRemark = new Remark("");

        assertEquals("remark", remark.get());
        assertEquals("", emptyRemark.get());

        // null remark should give null upon calling `get()`
        assertEquals(null, nullRemark.get());
    }

    @Test
    public void testToString() {
        Remark remark = new Remark("remark");
        Remark nullRemark = new Remark(null);
        Remark emptyRemark = new Remark("");

        assertEquals("remark", remark.toString());
        assertEquals("", emptyRemark.toString());

        // null remark should give empty string upon calling `get()`
        assertEquals("", nullRemark.toString());
    }

    @Test
    void testHashCode() {
        Remark remark = new Remark("remark");
        Remark differentRemark = new Remark("different remark");
        Remark nullRemark = new Remark(null);
        Remark emptyRemark = new Remark("");

        // same remark -> same hashcode
        assertEquals(remark.hashCode(), remark.hashCode());
        assertEquals(nullRemark.hashCode(), nullRemark.hashCode());

        // different remark -> different hashcode
        assertNotEquals(remark.hashCode(), differentRemark.hashCode());
        assertNotEquals(remark.hashCode(), nullRemark.hashCode());
        assertNotEquals(emptyRemark.hashCode(), differentRemark.hashCode());
    }
}
