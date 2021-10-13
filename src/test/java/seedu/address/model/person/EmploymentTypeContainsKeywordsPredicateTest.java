package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class EmploymentTypeContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Full time");
        List<String> secondPredicateKeywordList = Arrays.asList("Full time", "Part time");

        EmploymentTypeContainsKeywordsPredicate firstPredicate =
                new EmploymentTypeContainsKeywordsPredicate(firstPredicateKeywordList);
        EmploymentTypeContainsKeywordsPredicate secondPredicate =
                new EmploymentTypeContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmploymentTypeContainsKeywordsPredicate firstPredicateCopy =
                new EmploymentTypeContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_employmentTypeContainsKeywords_returnsTrue() {
        // One matching letter that starts with
        EmploymentTypeContainsKeywordsPredicate predicate =
                new EmploymentTypeContainsKeywordsPredicate(Collections.singletonList("F"));
        assertTrue(predicate.test(new PersonBuilder().withEmploymentType("Full time").build()));

        // One keyword
        predicate = new EmploymentTypeContainsKeywordsPredicate(Collections.singletonList("Internship"));
        assertTrue(predicate.test(new PersonBuilder().withEmploymentType("Internship").build()));

        // First word of a two word category
        predicate = new EmploymentTypeContainsKeywordsPredicate(Collections.singletonList("Part"));
        assertTrue(predicate.test(new PersonBuilder().withEmploymentType("Part time").build()));

        // Both parts of a two word category
        predicate = new EmploymentTypeContainsKeywordsPredicate(Collections.singletonList("Part time"));
        assertTrue(predicate.test(new PersonBuilder().withEmploymentType("Part time").build()));

        // Mixed-case keywords
        predicate = new EmploymentTypeContainsKeywordsPredicate(Collections.singletonList("iNteRnShiP"));
        assertTrue(predicate.test(new PersonBuilder().withEmploymentType("Internship").build()));
    }

    @Test
    public void test_employmentTypeDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EmploymentTypeContainsKeywordsPredicate predicate =
                new EmploymentTypeContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withEmploymentType("Temporary").build()));

        // Keyword that is part of an employment type but does not start with it
        predicate = new EmploymentTypeContainsKeywordsPredicate(Arrays.asList("time"));
        assertFalse(predicate.test(new PersonBuilder().withEmploymentType("Part time").build()));

        // Non-matching keyword
        predicate = new EmploymentTypeContainsKeywordsPredicate(Arrays.asList("Temporary"));
        assertFalse(predicate.test(new PersonBuilder().withEmploymentType("Internship").build()));

        // Keywords match name, phone, email, address, applied role, employment type, expected salary,
        // level of education, years of experience and tags, but does not match employment type
        predicate = new EmploymentTypeContainsKeywordsPredicate(
                Arrays.asList("Alice", "12345", "alice@email.com",
                        "Software", "Engineer", "Full time", "4000", "PhD", "5", "young"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withRole("Software Engineer")
                .withEmploymentType("Part time").withExpectedSalary("4000").withLevelOfEducation("PhD")
                .withExperience("5").withTags("young").build()));
    }

}
