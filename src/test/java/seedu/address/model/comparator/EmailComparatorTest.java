package seedu.address.model.comparator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class EmailComparatorTest {

    @Test
    public void equals() {
        Comparator<Person> firstComparator = new EmailComparator();
        Comparator<Person> secondComparator = new EmailComparator();

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
    public void test_compareEmailComparator_returnsTrue() {
        Comparator<Person> comparator = new EmailComparator();

        Person p1 = new PersonBuilder().withEmail("hibro@hotmail.com").build();
        Person p2 = new PersonBuilder().withEmail("hibro@hotmail.com").build();
        Person p3 = new PersonBuilder().withEmail("").build();
        Person p4 = new PersonBuilder().withEmail("").build();

        // If either person has null Email
        assertTrue(comparator.compare(p1, p4) < 0);
        assertTrue(comparator.compare(p4, p1) > 0);

        // If both person have null Email
        assertTrue(comparator.compare(p4, p3) == 0);
        assertTrue(comparator.compare(p3, p4) == 0);

        // If both person have same Email
        assertTrue(comparator.compare(p1, p2) == 0);
    }

}
