package seedu.address.model.comparator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class PhoneComparatorTest {

    @Test
    public void equals() {
        Comparator<Person> firstComparator = new PhoneComparator();
        Comparator<Person> secondComparator = new PhoneComparator();

        // same object -> returns true
        assertTrue(firstComparator.equals(firstComparator));

        // same comparators -> returns true
        assertTrue(firstComparator.equals(secondComparator));

        // different types -> returns false
        assertFalse(firstComparator.equals(1));

        // null -> returns false
        assertFalse(firstComparator.equals(null));
    }

    @Test
    public void test_comparePhoneComparator_returnsTrue() {
        Comparator<Person> comparator = new PhoneComparator();

        Person p1 = new PersonBuilder().withPhone("12345678").build();
        Person p2 = new PersonBuilder().withPhone("12345678").build();
        Person p3 = new PersonBuilder().withPhone("23456789").build();
        Person p4 = new PersonBuilder().withPhone("34567890").build();
        Person p5 = new PersonBuilder().withPhone("").build();
        Person p6 = new PersonBuilder().withPhone("").build();

        // If either person has null Phone
        assertTrue(comparator.compare(p1, p6) < 0);
        assertTrue(comparator.compare(p6, p1) > 0);

        // If both have Phone, sorted alphabetically
        assertTrue(comparator.compare(p1, p3) < 0);
        assertTrue(comparator.compare(p1, p4) < 0);

        assertTrue(comparator.compare(p3, p1) > 0);
        assertTrue(comparator.compare(p3, p4) < 0);

        assertTrue(comparator.compare(p4, p1) > 0);
        assertTrue(comparator.compare(p4, p3) > 0);

        // If both person have null Phone
        assertTrue(comparator.compare(p6, p5) == 0);
        assertTrue(comparator.compare(p5, p6) == 0);

        // If both person have same Phone
        assertTrue(comparator.compare(p1, p2) == 0);
    }

}
