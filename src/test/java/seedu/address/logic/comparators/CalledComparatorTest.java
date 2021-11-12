package seedu.address.logic.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Test for PersonComparator which is used to compare whether a Person is called.
 */
class CalledComparatorTest {
    @Test
    public void compare_sameCalled_returnsZero() {
        Person a = new PersonBuilder().withName("Kent").withCalled("true")
                .withPhone("88886666").withEmail("nameComparator@test.com").build();
        Person b = new PersonBuilder().withName("Mike").withCalled("true")
                .withPhone("99997777").withEmail("nameComparator@test.org").build();
        assertEquals(0, new CalledComparator().compare(a, b));
    }

    @Test
    public void compare_differentCalled_success() {
        // Persons will be shown in this order: false, true
        // Person a is not called
        Person a = new PersonBuilder().withName("Acorn").withCalled("false")
                .withPhone("88886666").withEmail("nameComparator@test.com").build();
        Person b = new PersonBuilder().withName("Kent").withCalled("true")
                .withPhone("99997777").withEmail("nameComparator@test.org").build();
        assertTrue(new CalledComparator().compare(a, b) < 0);

        // Person b is not called
        a = new PersonBuilder().withName("Kent").withCalled("true")
                .withPhone("88886666").withEmail("nameComparator@test.com").build();
        b = new PersonBuilder().withName("Acorn").withCalled("false")
                .withPhone("99997777").withEmail("nameComparator@test.org").build();
        assertTrue(new CalledComparator().compare(a, b) > 0);
    }

    @Test
    public void compare_nullPerson_throwNullPointerException() {
        Person a = new PersonBuilder().withName("Acorn").withCalled("true")
                .withPhone("88886666").withEmail("nameComparator@test.com").build();
        Person b = new PersonBuilder().withName("Kent").withCalled("true")
                .withPhone("99997777").withEmail("nameComparator@test.org").build();

        assertThrows(NullPointerException.class, () -> new CalledComparator().compare(null, b));
        assertThrows(NullPointerException.class, () -> new CalledComparator().compare(a, null));
        assertThrows(NullPointerException.class, () -> new CalledComparator().compare(null, null));
    }

    @Test
    public void equals() {
        final CalledComparator standardComparator = new CalledComparator();

        // same object -> returns true
        assertTrue(standardComparator.equals(standardComparator));

        // null -> returns false
        assertFalse(standardComparator.equals(null));

        // different types -> returns false
        assertFalse(standardComparator.equals(new NameComparator()));
    }
}
