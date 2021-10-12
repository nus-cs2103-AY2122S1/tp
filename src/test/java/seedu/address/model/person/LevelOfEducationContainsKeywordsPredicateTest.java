package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class LevelOfEducationContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        LevelOfEducationContainsKeywordsPredicate firstPredicate =
                new LevelOfEducationContainsKeywordsPredicate(firstPredicateKeywordList);
        LevelOfEducationContainsKeywordsPredicate secondPredicate =
                new LevelOfEducationContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LevelOfEducationContainsKeywordsPredicate firstPredicateCopy =
                new LevelOfEducationContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_levelOfEducationContainsKeywords_returnsTrue() {
        // One matching letter
        LevelOfEducationContainsKeywordsPredicate predicate =
                new LevelOfEducationContainsKeywordsPredicate(Collections.singletonList("M"));
        assertTrue(predicate.test(new PersonBuilder().withLevelOfEducation("Masters").build()));

        // One keyword
        predicate = new LevelOfEducationContainsKeywordsPredicate(Collections.singletonList("Masters"));
        assertTrue(predicate.test(new PersonBuilder().withLevelOfEducation("Masters").build()));

        // One portion of a two word category
        predicate = new LevelOfEducationContainsKeywordsPredicate(Collections.singletonList("School"));
        assertTrue(predicate.test(new PersonBuilder().withLevelOfEducation("High School").build()));

        // Both parts of a two word category
        predicate = new LevelOfEducationContainsKeywordsPredicate(Collections.singletonList("High School"));
        assertTrue(predicate.test(new PersonBuilder().withLevelOfEducation("High School").build()));

        // Mixed-case keywords
        predicate = new LevelOfEducationContainsKeywordsPredicate(Collections.singletonList("maSteRS"));
        assertTrue(predicate.test(new PersonBuilder().withLevelOfEducation("Masters").build()));
    }

    @Test
    public void test_levelOfEducationDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        LevelOfEducationContainsKeywordsPredicate predicate =
                new LevelOfEducationContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withLevelOfEducation("PhD").build()));

        // Non-matching keyword
        predicate = new LevelOfEducationContainsKeywordsPredicate(Arrays.asList("Masters"));
        assertFalse(predicate.test(new PersonBuilder().withLevelOfEducation("PhD").build()));

        // Keywords match name, phone, and email, but does not match level of education
        predicate = new LevelOfEducationContainsKeywordsPredicate(Arrays
                .asList("Alice", "12345", "alice@email.com", "Engineer", "Temporary", "4000", "5", "old"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withRole("Engineer").withEmploymentType("Temporary")
                .withExpectedSalary("4000").withLevelOfEducation("PhD").withExperience("5").build()));
    }
}
