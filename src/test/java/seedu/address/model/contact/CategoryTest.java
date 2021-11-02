package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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
    public void getFullCodeTest() {
        assertTrue(new CategoryCode("att").getFullCode().equals("Attractions")); // attraction category
        assertTrue(new CategoryCode("acc").getFullCode().equals("Accommodation")); // accommodation category
        assertTrue(new CategoryCode("com").getFullCode().equals("Commerce")); // commerce category
        assertTrue(new CategoryCode("fnb").getFullCode().equals("Food & Beverage")); // food and beverage category
        assertTrue(new CategoryCode("tpt").getFullCode().equals("Transport")); // transport category
        assertTrue(new CategoryCode("oth").getFullCode().equals("Others")); // others category
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
        assertFalse(CategoryCode.isValidCategory("a tt")); // spaces in between code
        assertFalse(CategoryCode.isValidCategory("oTh ")); // trailing space
        assertFalse(CategoryCode.isValidCategory(" oTh")); // leading space

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
