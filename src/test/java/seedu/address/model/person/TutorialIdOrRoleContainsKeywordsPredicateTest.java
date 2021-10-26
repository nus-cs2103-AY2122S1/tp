package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TutorialIdOrRoleContainsKeywordsPredicateTest {

    public static final String TUTORIAL_ID_TYPE = "T/";
    public static final String ROLE_TYPE = "r/";

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TutorialIdOrRoleContainsKeywordsPredicate firstPredicate =
                new TutorialIdOrRoleContainsKeywordsPredicate(firstPredicateKeywordList, TUTORIAL_ID_TYPE);
        TutorialIdOrRoleContainsKeywordsPredicate secondPredicate =
                new TutorialIdOrRoleContainsKeywordsPredicate(secondPredicateKeywordList, TUTORIAL_ID_TYPE);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TutorialIdOrRoleContainsKeywordsPredicate firstPredicateCopy =
                new TutorialIdOrRoleContainsKeywordsPredicate(firstPredicateKeywordList, TUTORIAL_ID_TYPE);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tutorialIdContainsKeywords_returnsTrue() {
        // Full Tutorial ID
        TutorialIdOrRoleContainsKeywordsPredicate predicate =
                new TutorialIdOrRoleContainsKeywordsPredicate(Arrays.asList("10"), TUTORIAL_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withTutorialId("10").build()));
    }

    @Test
    public void test_tutorialIdDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        TutorialIdOrRoleContainsKeywordsPredicate predicate =
                new TutorialIdOrRoleContainsKeywordsPredicate(Collections.emptyList(), TUTORIAL_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withTutorialId("10").build()));

        // Non-Matching Tutorial ID
        predicate =
                new TutorialIdOrRoleContainsKeywordsPredicate(Arrays.asList("11"), TUTORIAL_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withTutorialId("10").build()));

        // Keywords match phone, email and address, but does not match Tutorial ID
        predicate = new TutorialIdOrRoleContainsKeywordsPredicate(Arrays
                .asList("12345", "e1234567@u.nus.edu", "Main", "Street", "11"), TUTORIAL_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("e1234567@u.nus.edu").withAddress("Main Street").withTutorialId("10").build()));
    }

    @Test
    public void test_roleContainsKeywords_returnsTrue() {
        // Full Role name
        TutorialIdOrRoleContainsKeywordsPredicate predicate =
                new TutorialIdOrRoleContainsKeywordsPredicate(Arrays.asList("student"), ROLE_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withType("student").build()));
    }

    @Test
    public void test_roleDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        TutorialIdOrRoleContainsKeywordsPredicate predicate =
                new TutorialIdOrRoleContainsKeywordsPredicate(Collections.emptyList(), ROLE_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withType("student").build()));

        // Non-Matching Role
        predicate =
                new TutorialIdOrRoleContainsKeywordsPredicate(Arrays.asList("tutor"), ROLE_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withType("student").build()));

        // Keywords match phone, email and address, but does not match Role
        predicate = new TutorialIdOrRoleContainsKeywordsPredicate(Arrays
                .asList("12345", "e1234567@u.nus.edu", "Main", "Street", "11"), ROLE_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("e1234567@u.nus.edu").withAddress("Main Street").withType("student").build()));
    }
}
