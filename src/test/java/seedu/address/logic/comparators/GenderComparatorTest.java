package seedu.address.logic.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Test for PersonComparator which is used to compare Persons' gender.
 */
class GenderComparatorTest {
    @Test
    public void compare_sameGender_returnsZero() {
        Person a = new PersonBuilder().withName("Kent").withGender("M")
                .withPhone("88886666").withEmail("nameComparator@test.com").build();
        Person b = new PersonBuilder().withName("Mike").withGender("M")
                .withPhone("99997777").withEmail("nameComparator@test.org").build();
        assertEquals(0, new GenderComparator().compare(a, b));
    }

    @Test
    public void compare_differentGender_success() {
        // Persons will be shown in this order: Female, Male, N.A
        // Person a is Female
        Person a = new PersonBuilder().withName("Acorn").withGender("F")
                .withPhone("88886666").withEmail("nameComparator@test.com").build();
        Person b = new PersonBuilder().withName("Kent").withGender("M")
                .withPhone("99997777").withEmail("nameComparator@test.org").build();
        assertTrue(new GenderComparator().compare(a, b) < 0);

        // Person b is Female
        a = new PersonBuilder().withName("Kent").withGender("M")
                .withPhone("88886666").withEmail("nameComparator@test.com").build();
        b = new PersonBuilder().withName("Acorn").withGender("F")
                .withPhone("99997777").withEmail("nameComparator@test.org").build();
        assertTrue(new GenderComparator().compare(a, b) > 0);

        // Person a is N.A
        a = new PersonBuilder().withName("Kent").withGender("N.A")
                .withPhone("88886666").withEmail("nameComparator@test.com").build();
        b = new PersonBuilder().withName("Acorn").withGender("F")
                .withPhone("99997777").withEmail("nameComparator@test.org").build();
        assertTrue(new GenderComparator().compare(a, b) > 0);
    }

    @Test
    public void compare_nullPerson_throwNullPointerException() {
        Person a = new PersonBuilder().withName("Acorn").withGender("F")
                .withPhone("88886666").withEmail("nameComparator@test.com").build();
        Person b = new PersonBuilder().withName("Kent").withGender("M")
                .withPhone("99997777").withEmail("nameComparator@test.org").build();

        assertThrows(NullPointerException.class, () -> new GenderComparator().compare(null, b));
        assertThrows(NullPointerException.class, () -> new GenderComparator().compare(a, null));
        assertThrows(NullPointerException.class, () -> new GenderComparator().compare(null, null));
    }

    @Test
    public void equals() {
        final GenderComparator standardComparator = new GenderComparator();

        // same object -> returns true
        assertTrue(standardComparator.equals(standardComparator));

        // null -> returns false
        assertFalse(standardComparator.equals(null));

        // different types -> returns false
        assertFalse(standardComparator.equals(new CalledComparator()));
    }
}
