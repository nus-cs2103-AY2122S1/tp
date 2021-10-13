package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACAD_LEVEL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACAD_STREAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARENT_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARENT_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FORGETFUL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CARELESS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand.FindCondition;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonMatchesKeywordsPredicateBuilder;

public class PersonMatchesKeywordsPredicateTest {

    @Test
    public void test_oneFieldMatchesKeywords_returnsTrue() {
        // One keyword
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder()
                .withName("Alice").build();
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Match all keywords
        predicate = new PersonMatchesKeywordsPredicateBuilder().withName("Alice Bob").build();
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Mixed-case keywords
        predicate = new PersonMatchesKeywordsPredicateBuilder().withName("aLIce bOB").build();
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keyword is substring of field word
        predicate = new PersonMatchesKeywordsPredicateBuilder().withName("lee").build();
        assertTrue(predicate.test(new PersonBuilder().withName("Aileen").build()));
    }

    @Test
    public void test_oneFieldMatchesKeywords_returnsFalse() {

        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder().withName("Alice Tan")
                .build();

        // Only one matching keyword
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Lee").build()));

        // Field is empty
        predicate = new PersonMatchesKeywordsPredicateBuilder().withParentEmail("gmail").build();
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Lee").withParentEmail().build()));
    }

    @Test
    public void test_tagsMatchKeywords_returnsTrue() {
        // Match tag name exactly
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder().withTags("Math").build();
        assertTrue(predicate.test(new PersonBuilder().withTags("Math", "paid").build()));

        // Mixed-case keywords
        predicate = new PersonMatchesKeywordsPredicateBuilder().withTags("MaTh").build();
        assertTrue(predicate.test(new PersonBuilder().withTags("math").build()));

        // Match all tag keywords
        predicate = new PersonMatchesKeywordsPredicateBuilder().withTags("Math", "paid")
                .withCondition(FindCondition.ALL).build();
        assertTrue(predicate.test(new PersonBuilder().withTags("Math", "paid").build()));

        // Match one tag keyword
        predicate = new PersonMatchesKeywordsPredicateBuilder().withTags("Math", "paid")
                .withCondition(FindCondition.ANY).build();
        assertTrue(predicate.test(new PersonBuilder().withTags("Math", "science").build()));

        // Match none tag keywords
        predicate = new PersonMatchesKeywordsPredicateBuilder().withTags("Math", "paid")
                .withCondition(FindCondition.NONE).build();
        assertTrue(predicate.test(new PersonBuilder().withTags("unpaid", "science").build()));
    }

    @Test
    public void test_tagsMatchKeywords_returnsFalse() {
        // Keyword does not match tag name exactly
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder().withTags("Math").build();
        assertFalse(predicate.test(new PersonBuilder().withTags("Mathematics").build()));

        // Does not match all tag keywords
        predicate = new PersonMatchesKeywordsPredicateBuilder().withTags("Math", "paid")
                .withCondition(FindCondition.ALL).build();
        assertFalse(predicate.test(new PersonBuilder().withTags("Math", "unpaid").build()));

        // Does not match any tag keyword
        predicate = new PersonMatchesKeywordsPredicateBuilder().withTags("Math", "paid")
                .withCondition(FindCondition.ANY).build();
        assertFalse(predicate.test(new PersonBuilder().withTags("science").build()));

        // Does not match none tag keywords
        predicate = new PersonMatchesKeywordsPredicateBuilder().withTags("Math", "paid")
                .withCondition(FindCondition.NONE).build();
        assertFalse(predicate.test(new PersonBuilder().withTags("Math").build()));
    }

    @Test
    public void test_multipleFieldsAllMatchKeywords_returnsTrue() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder().withName("Alice")
                .withAddress("clementi").withPhone("9876").withEmail("gmail").withParentPhone("5555")
                .withParentEmail("john").withSchool("Dunman").withAcadStream("ip").withAcadLevel("y2")
                .withTags("Math", "paid").build();

        // Default condition is set to match all
        Person person = new PersonBuilder().withName("Alice Tan").withAddress("Clementi Road").withPhone("98765432")
                .withEmail("alice@gmail.com").withParentPhone("99995555").withParentEmail("john.tan@gmail.com")
                .withSchool("Dunman High School").withAcadStream("IP").withAcadLevel("Y2")
                .withTags("Math", "paid").build();
        assertTrue(predicate.test(person));

        // Explicitly set condition to match all
        predicate.setCondition(FindCondition.ALL);
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_multipleFieldsAllMatchKeywords_returnsFalse() {
        // Does not match at least one keyword
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder()
                .withEmail("alice") // match
                .withTags("Math", "paid") // match
                .withAcadLevel("Y3") // not match
                .withCondition(FindCondition.ALL).build();

        Person person = new PersonBuilder().withName("Alice Tan")
                .withEmail("alice@gmail.com") // match
                .withTags("Math", "paid") // match
                .withAcadLevel("Y2") // not match
                .build();
        assertFalse(predicate.test(person));
    }

    @Test
    public void test_multipleFieldsAnyMatchKeywords_returnsTrue() {
        // Match at least one keyword
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder()
                .withName("Alice Tan") // not match
                .withAddress("clementi") // match
                .withCondition(FindCondition.ANY).build();

        Person person = new PersonBuilder().withName("John").withAddress("Clementi Road").build();
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_multipleFieldsAnyMatchKeywords_returnsFalse() {
        // Does not match any keyword
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder()
                .withName("Alice Tan") // not match
                .withParentPhone("98765432") // not match
                .withCondition(FindCondition.ANY).build();

        Person person = new PersonBuilder().withName("John").withPhone("99887766").build();
        assertFalse(predicate.test(person));
    }

    @Test
    public void test_multipleFieldsNoneMatchKeywords_returnsTrue() {
        // Match none keywords
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder()
                .withName("Alice Tan").withAddress("clementi").withPhone("9876").withEmail("gmail")
                .withParentPhone("5555").withParentEmail("john")
                .withSchool("Dunman High School").withAcadStream("IP").withAcadLevel("Y2")
                .withTags("Math", "paid").withCondition(FindCondition.NONE).build();

        Person person = new PersonBuilder().withName("John").withAddress("Yishun ave").withPhone("99998888")
                .withEmail("john@nus.edu.sg").withParentPhone("988665544").withParentEmail("benny@gmail.com")
                .withSchool("Yishun Secondary").withAcadStream("express").withAcadLevel("s4")
                .withTags("Science", "unpaid").build();

        assertTrue(predicate.test(person));
    }

    @Test
    public void test_multipleFieldsNoneMatchKeywords_returnsFalse() {
        // Match at least one keyword
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder()
                .withName("Alice Tan") // not match
                .withAddress("yishun") // not match
                .withSchool("town") // match
                .withCondition(FindCondition.NONE).build();

        Person person = new PersonBuilder().withName("John Tan").withAddress("Clementi ave")
                .withSchool("New Town").build();
        assertFalse(predicate.test(person));
    }

    @Test
    public void equals() {
        PersonMatchesKeywordsPredicate
                firstPredicate = new PersonMatchesKeywordsPredicateBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withParentPhone(VALID_PARENT_PHONE_AMY)
                .withParentEmail(VALID_PARENT_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withSchool(VALID_SCHOOL_AMY)
                .withAcadStream(VALID_ACAD_STREAM_AMY)
                .withAcadLevel(VALID_ACAD_LEVEL_AMY)
                .withTags(VALID_TAG_FORGETFUL, VALID_TAG_CARELESS).build();
        PersonMatchesKeywordsPredicate secondPredicate = new PersonMatchesKeywordsPredicateBuilder()
                .withName(VALID_NAME_BOB).build();

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonMatchesKeywordsPredicate firstPredicateCopy = new PersonMatchesKeywordsPredicateBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withParentPhone(VALID_PARENT_PHONE_AMY)
                .withParentEmail(VALID_PARENT_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withSchool(VALID_SCHOOL_AMY)
                .withAcadStream(VALID_ACAD_STREAM_AMY)
                .withAcadLevel(VALID_ACAD_LEVEL_AMY)
                .withTags(VALID_TAG_FORGETFUL, VALID_TAG_CARELESS).build();
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
