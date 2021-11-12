package seedu.address.logic.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Test for PersonComparator which is used to compare Persons' names.
 */
class NameComparatorTest {
    @Test
    public void compare_sameName_returnsZero() {
        Person a = new PersonBuilder().withName("Kent")
                .withPhone("88886666").withEmail("nameComparator@test.com").build();
        Person b = new PersonBuilder().withName("Kent")
                .withPhone("99997777").withEmail("nameComparator@test.org").build();
        assertEquals(0, new NameComparator().compare(a, b));
    }

    @Test
    public void compare_differentName_success() {
        // Person a's name appears first
        Person a = new PersonBuilder().withName("Acorn")
                .withPhone("88886666").withEmail("nameComparator@test.com").build();
        Person b = new PersonBuilder().withName("Kent")
                .withPhone("99997777").withEmail("nameComparator@test.org").build();
        assertTrue(new NameComparator().compare(a, b) < 0);

        // Person b's name appears first
        a = new PersonBuilder().withName("Kent")
                .withPhone("88886666").withEmail("nameComparator@test.com").build();
        b = new PersonBuilder().withName("Acorn")
                .withPhone("99997777").withEmail("nameComparator@test.org").build();
        assertTrue(new NameComparator().compare(a, b) > 0);
    }

    @Test
    public void compare_nullPerson_throwNullPointerException() {
        Person a = new PersonBuilder().withName("Acorn")
                .withPhone("88886666").withEmail("nameComparator@test.com").build();
        Person b = new PersonBuilder().withName("Kent")
                .withPhone("99997777").withEmail("nameComparator@test.org").build();

        assertThrows(NullPointerException.class, () -> new NameComparator().compare(null, b));
        assertThrows(NullPointerException.class, () -> new NameComparator().compare(a, null));
        assertThrows(NullPointerException.class, () -> new NameComparator().compare(null, null));
    }

    @Test
    public void equals() {
        final NameComparator standardComparator = new NameComparator();

        // same object -> returns true
        assertTrue(standardComparator.equals(standardComparator));

        // null -> returns false
        assertFalse(standardComparator.equals(null));

        // different types -> returns false
        assertFalse(standardComparator.equals(new GenderComparator()));
    }
}
