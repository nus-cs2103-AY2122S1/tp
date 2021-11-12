package seedu.address.logic.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.comparators.PersonComparator.MESSAGE_INVALID_CATEGORY;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.comparators.exceptions.ComparatorException;
import seedu.address.model.category.Category;

/**
 * Test for PersonComparator which is used to compare Persons.
 */
class PersonComparatorTest {
    private static final String VALID_CATEGORY_CALLED = "Called";
    private static final String VALID_CATEGORY_GENDER = "Gender";
    private static final String INVALID_COMPARATOR_CATEGORY = "Address";

    private Category validCategoryCalled = new Category(VALID_CATEGORY_CALLED);
    private Category validCategoryGender = new Category(VALID_CATEGORY_GENDER);
    private Category invalidCategory = new Category(INVALID_COMPARATOR_CATEGORY);

    @Test
    public void getComparator_invalidCategory_throwsComparatorException() {
        assertThrows(ComparatorException.class, MESSAGE_INVALID_CATEGORY, () ->
            PersonComparator.getComparator(invalidCategory));
    }

    @Test
    public void getComparator_validCategoryCalled_success() throws ComparatorException {
        CalledComparator calledComparator = new CalledComparator();
        assertEquals(calledComparator, PersonComparator.getComparator(validCategoryCalled));
    }

    @Test
    public void getComparator_validCategoryGender_success() throws ComparatorException {
        GenderComparator genderComparator = new GenderComparator();
        assertEquals(genderComparator, PersonComparator.getComparator(validCategoryGender));
    }
}
