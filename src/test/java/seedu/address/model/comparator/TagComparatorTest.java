package seedu.address.model.comparator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class TagComparatorTest {

    @Test
    public void equals() {
        Comparator<Person> firstComparator = new TagComparator();
        Comparator<Person> secondComparator = new TagComparator();

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
    public void test_compareTagComparator_returnsTrue() {
        Comparator<Person> comparator = new TagComparator();

        Person p1 = new PersonBuilder().withTags("Classmate").build();
        Person p2 = new PersonBuilder().withTags("Classmate").build();
        Person p3 = new PersonBuilder().withTags("Friend").build();
        Person p4 = new PersonBuilder().withTags("Neighbour").build();

        // If both have Tag, sorted alphabetically
        assertTrue(comparator.compare(p1, p3) < 0);
        assertTrue(comparator.compare(p1, p4) < 0);

        assertTrue(comparator.compare(p3, p1) > 0);
        assertTrue(comparator.compare(p3, p4) < 0);

        assertTrue(comparator.compare(p4, p1) > 0);
        assertTrue(comparator.compare(p4, p3) > 0);

        // If both person have same Tag
        assertTrue(comparator.compare(p1, p2) == 0);
    }

}
