package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

class ClassMemberPredicateTest {

    private final ClassCode test1 = new ClassCode("G01");
    private final ClassCode test2 = new ClassCode("G02");

    @Test
    public void test_classCodeContainsKeywords_returnsTrue() {
        // matching classcode
        ClassMemberPredicate predicate = new ClassMemberPredicate(test1);
        assertTrue(predicate.test(new StudentBuilder().withClassCode("G01").build()));

        predicate = new ClassMemberPredicate(test2);
        assertTrue(predicate.test(new StudentBuilder().withClassCode("G02").build()));
    }

    @Test
    public void test_classCodeDoesNotContainKeywords_returnsFalse() {
        // non-matching classCode
        ClassMemberPredicate predicate = new ClassMemberPredicate(test1);
        assertFalse(predicate.test(new StudentBuilder().withClassCode("G02").build()));

        // class code of tutorial class match phone, name and address, but does not match class code of student
        assertFalse(predicate.test(new StudentBuilder().withName("G01").withPhone("101")
                .withAddress("G01").build()));

    }

    @Test
    public void equals() {
        ClassMemberPredicate firstPredicate = new ClassMemberPredicate(test1);
        ClassMemberPredicate secondPredicate = new ClassMemberPredicate(test2);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ClassMemberPredicate firstPredicateCopy = new ClassMemberPredicate(test1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
