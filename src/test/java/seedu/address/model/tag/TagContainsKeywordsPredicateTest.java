package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("favourite");
        List<String> secondPredicateKeywordList = Arrays.asList("young", "favourite");

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {

        // One keyword, one tag
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.singletonList("young"));
        assertTrue(predicate.test(new PersonBuilder().withTags("young").build()));

        // Multiple keywords, one tag
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("young", "favourite"));
        assertTrue(predicate.test(new PersonBuilder().withTags("favourite").build()));

        // One keyword, multiple tags
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("favourite"));
        assertTrue(predicate.test(new PersonBuilder().withTags("young", "favourite").build()));

        // Mixed-case keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("yOunG"));
        assertTrue(predicate.test(new PersonBuilder().withTags("young").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("young").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("old"));
        assertFalse(predicate.test(new PersonBuilder().withTags("young").build()));

        // Keywords match name, phone, email, applied role, employment type, expected salary,
        // level of education, and years of experience, but does not match tags
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com",
                "Engineer", "Temporary", "4000", "PhD", "5", "old"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withRole("Engineer").withEmploymentType("Temporary")
                .withExpectedSalary("4000").withLevelOfEducation("PhD").withExperience("5").build()));
    }
}
