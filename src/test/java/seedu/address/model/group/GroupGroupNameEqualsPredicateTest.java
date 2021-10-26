package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GroupBuilder;

public class GroupGroupNameEqualsPredicateTest {

    @Test
    public void equals() {
        GroupName firstPredicateGroupName = new GroupName("first");
        GroupName secondPredicateGroupName = new GroupName("first second");

        GroupGroupNameEqualsPredicate firstPredicate = new GroupGroupNameEqualsPredicate(firstPredicateGroupName);
        GroupGroupNameEqualsPredicate secondPredicate = new GroupGroupNameEqualsPredicate(secondPredicateGroupName);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GroupGroupNameEqualsPredicate firstPredicateCopy =
                new GroupGroupNameEqualsPredicate(firstPredicateGroupName);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_groupGroupNameEquals_returnsTrue() {
        // One word
        GroupGroupNameEqualsPredicate predicate =
                new GroupGroupNameEqualsPredicate(new GroupName("CS2103T"));
        assertTrue(predicate.test(new GroupBuilder().withGroupName("CS2103T").build()));

        // Two words
        // GroupName can have whitespace
        predicate = new GroupGroupNameEqualsPredicate(new GroupName("CS2103T CS2101"));
        assertTrue(predicate.test(new GroupBuilder().withGroupName("CS2103T CS2101").build()));

        // Numerical name
        predicate = new GroupGroupNameEqualsPredicate(new GroupName("1234"));
        assertTrue(predicate.test(new GroupBuilder().withGroupName("1234").build()));
    }

    @Test
    public void test_groupNameNotEqual_returnsFalse() {
        // GroupName only matches part of the actual group name
        GroupGroupNameEqualsPredicate predicate = new GroupGroupNameEqualsPredicate(new GroupName("CS"));
        assertFalse(predicate.test(new GroupBuilder().withGroupName("CS2103T").build()));

        // Non-matching GroupName
        predicate = new GroupGroupNameEqualsPredicate(new GroupName("CS2101"));
        assertFalse(predicate.test(new GroupBuilder().withGroupName("CS2103T").build()));

        // GroupName matches description, but does not match the actual group name
        predicate = new GroupGroupNameEqualsPredicate(new GroupName("softwareengineering"));
        assertFalse(predicate.test(new GroupBuilder().withGroupName("CS2103T")
                .withDescription("softwareengineering").build()));
    }
}
