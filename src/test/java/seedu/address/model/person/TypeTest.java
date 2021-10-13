package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Type(null));
    }

    @Test
    public void constructor_invalidType_throwsIllegalArgumentException() {
        String invalidType = "";
        assertThrows(IllegalArgumentException.class, () -> new Type(invalidType));
    }

    @Test
    public void isValidType() {
        // null Type
        assertThrows(NullPointerException.class, () -> Type.isValidType(null));

        // blank Type
        assertFalse(Type.isValidType("")); // empty string
        assertFalse(Type.isValidType(" ")); // spaces only

        // invalid Type
        assertFalse(Type.isValidType("teacher")); // invalid because not equal "student" or "tutor"
        assertFalse(Type.isValidType("professor")); // invalid because not equal "student" or "tutor"
        assertFalse(Type.isValidType("StUdEnT")); // invalid because not equal "student" or "tutor" ie wrong case
        assertFalse(Type.isValidType("STUDENT")); // invalid because not equal "student" or "tutor" ie wrong case
        assertFalse(Type.isValidType("TuToR")); // invalid because not equal "student" or "tutor" ie wrong case
        assertFalse(Type.isValidType("TUTOR")); // invalid because not equal "student" or "tutor" ie wrong case

        // valid Type
        assertTrue(Type.isValidType("student"));
        assertTrue(Type.isValidType("tutor"));
    }

    @Test
    public void isEqualType() {
        Type type = new Type("student");
        Type different_type = new Type("tutor");
        Type same_type = new Type("student");

        // Different Type
        assertFalse(type.equals(different_type));

        // Same Object
        assertTrue(type.equals(type));

        // Different Objects Same Type
        assertTrue(type.equals(same_type));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(VALID_TYPE_AMY.hashCode(), VALID_TYPE_AMY.hashCode());
        assertNotEquals(VALID_TYPE_AMY.hashCode(), VALID_TYPE_BOB.hashCode());
    }
}
