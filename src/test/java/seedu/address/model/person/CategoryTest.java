package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class CategoryTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CategoryCode(null));
    }

    @Test
    public void constructor_invalidRating_throwsIllegalArgumentException() {
        String invalidCategory = "abc";
        assertThrows(IllegalArgumentException.class, () -> new CategoryCode(invalidCategory));
    }

    @Test
    public void isValidCategoryTest() {
        // null category
        assertThrows(NullPointerException.class, () -> CategoryCode.isValidCategory(null));

        // invalid categories
        assertFalse(CategoryCode.isValidCategory("10")); // number
        assertFalse(CategoryCode.isValidCategory("other")); // not category code
        assertFalse(CategoryCode.isValidCategory("ATTraction")); // not category code
        assertFalse(CategoryCode.isValidCategory(" ")); // spaces

        // valid categories
        assertTrue(CategoryCode.isValidCategory("att")); // attraction category
        assertTrue(CategoryCode.isValidCategory("oth")); // others category
        assertTrue(CategoryCode.isValidCategory("tpt")); // transport category
        assertTrue(CategoryCode.isValidCategory("acc")); // accommodations category
        assertTrue(CategoryCode.isValidCategory("com")); // commerce category
        assertTrue(CategoryCode.isValidCategory("fnb")); // fnb category
        assertTrue(CategoryCode.isValidCategory("ATT")); // full upper case
        assertTrue(CategoryCode.isValidCategory("oTh")); // mixed case

    }
}
