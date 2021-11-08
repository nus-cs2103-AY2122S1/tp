package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.GroupName;
import seedu.address.testutil.StudentBuilder;

class StudentGroupNameEqualsPredicateTest {

    @Test
    public void equals() {
        GroupName firstGroupName = new GroupName("CS2103T");
        GroupName secondGroupName = new GroupName("CS2101");

        StudentGroupNameEqualsPredicate firstPredicate = new StudentGroupNameEqualsPredicate(firstGroupName);
        StudentGroupNameEqualsPredicate secondPredicate = new StudentGroupNameEqualsPredicate(secondGroupName);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentGroupNameEqualsPredicate firstPredicateCopy = new StudentGroupNameEqualsPredicate(firstGroupName);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_containsGroupName_returnsTrue() {
        StudentGroupNameEqualsPredicate predicate = new StudentGroupNameEqualsPredicate(new GroupName("CS2103T"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice")
                .withGroupName("CS2103T").build()));
    }

    @Test
    public void test_doesNotContainsGroupName_returnsFalse() {
        StudentGroupNameEqualsPredicate predicate = new StudentGroupNameEqualsPredicate(new GroupName("CS2103T"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice")
                .withGroupName("GEQ1000").build()));
    }

}
