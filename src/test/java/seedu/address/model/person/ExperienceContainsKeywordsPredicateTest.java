package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ExperienceContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ExperienceContainsKeywordsPredicate firstPredicate =
                new ExperienceContainsKeywordsPredicate(firstPredicateKeywordList);
        ExperienceContainsKeywordsPredicate secondPredicate =
                new ExperienceContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ExperienceContainsKeywordsPredicate firstPredicateCopy =
                new ExperienceContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_experienceContainsKeywords_returnsTrue() {
        // One keyword
        ExperienceContainsKeywordsPredicate predicate =
                new ExperienceContainsKeywordsPredicate(Collections.singletonList("2"));
        assertTrue(predicate.test(new PersonBuilder().withExperience("2").build()));
    }

    @Test
    public void test_experienceDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ExperienceContainsKeywordsPredicate predicate =
                new ExperienceContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withExperience("10").build()));

        // entry less than keyword
        predicate = new ExperienceContainsKeywordsPredicate(Arrays.asList("3"));
        assertFalse(predicate.test(new PersonBuilder().withExperience("1").build()));


        // Keywords match every other category except experience
        predicate = new ExperienceContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com",
                "Programmer", "Full", "time", "3000", "High", "School", "3", "friend"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withRole("Cat Whisperer")
                .withEmploymentType("Full time").withExpectedSalary("3000")
                .withLevelOfEducation("High School").withExperience("1").withTags("friend")
                .build()));
    }
}
