package seedu.address.model.comparator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class RemarkComparatorTest {

    @Test
    public void equals() {
        Comparator<Person> firstComparator = new RemarkComparator();
        Comparator<Person> secondComparator = new RemarkComparator();

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
    public void test_compareRemarkComparator_returnsTrue() {
        Comparator<Person> comparator = new RemarkComparator();

        Person p1 = new PersonBuilder().withRemark("I like to eat").build();
        Person p2 = new PersonBuilder().withRemark("I like to eat").build();
        Person p3 = new PersonBuilder().withRemark("You like to drink").build();
        Person p4 = new PersonBuilder().withRemark("Zennie likes to sleep").build();
        Person p5 = new PersonBuilder().withRemark("").build();
        Person p6 = new PersonBuilder().withRemark("").build();

        // If either person has null Remark
        assertTrue(comparator.compare(p1, p6) < 0);
        assertTrue(comparator.compare(p6, p1) > 0);

        // If both have Remark, sorted alphabetically
        assertTrue(comparator.compare(p1, p3) < 0);
        assertTrue(comparator.compare(p1, p4) < 0);

        assertTrue(comparator.compare(p3, p1) > 0);
        assertTrue(comparator.compare(p3, p4) < 0);

        assertTrue(comparator.compare(p4, p1) > 0);
        assertTrue(comparator.compare(p4, p3) > 0);

        // If both person have null Remark
        assertTrue(comparator.compare(p6, p5) == 0);
        assertTrue(comparator.compare(p5, p6) == 0);

        // If both person have same Remark
        assertTrue(comparator.compare(p1, p2) == 0);
    }

}
