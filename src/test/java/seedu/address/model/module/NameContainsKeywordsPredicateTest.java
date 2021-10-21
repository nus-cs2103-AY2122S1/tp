package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.member.Member;
import seedu.address.testutil.MemberBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate<Member> firstPredicate =
                new NameContainsKeywordsPredicate<Member>(firstPredicateKeywordList);
        NameContainsKeywordsPredicate<Member> secondPredicate =
                new NameContainsKeywordsPredicate<Member>(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate<Member> firstPredicateCopy =
                new NameContainsKeywordsPredicate<Member>(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different member -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate<Member> predicate =
                new NameContainsKeywordsPredicate<Member>(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new MemberBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate<Member>(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new MemberBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate<Member>(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new MemberBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate<Member>(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new MemberBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate<Member> predicate =
                new NameContainsKeywordsPredicate<Member>(Collections.emptyList());
        assertFalse(predicate.test(new MemberBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate<Member>(Arrays.asList("Carol"));
        assertFalse(predicate.test(new MemberBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameContainsKeywordsPredicate<Member>(Arrays.asList("12345",
                "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new MemberBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
