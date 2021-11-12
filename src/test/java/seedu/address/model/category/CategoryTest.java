package seedu.address.model.category;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void equals() {
        Category category = new Category("name");

        // same object -> returns true
        assertTrue(category.equals(category));

        // same values -> returns true
        Category categoryCopy = new Category("name");
        assertTrue(category.equals(categoryCopy));

        // different types -> returns false
        assertFalse(category.equals(1));

        // null -> returns false
        assertFalse(category.equals(null));

        // different category -> returns false
        Category differentCategory = new Category("phone");
        assertFalse(category.equals(differentCategory));
    }
}
