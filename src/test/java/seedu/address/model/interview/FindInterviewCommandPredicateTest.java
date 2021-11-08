package seedu.address.model.interview;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.InterviewBuilder;

public class FindInterviewCommandPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FindInterviewCommandPredicate firstPredicate = new FindInterviewCommandPredicate(
                firstPredicateKeywordList,
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList()
        );
        FindInterviewCommandPredicate secondPredicate = new FindInterviewCommandPredicate(
                secondPredicateKeywordList,
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList()
        );

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FindInterviewCommandPredicate firstPredicateCopy = new FindInterviewCommandPredicate(firstPredicateKeywordList,
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList());
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different interview -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_candiatesContainsKeywords_returnsTrue() {
        // One keyword
        FindInterviewCommandPredicate predicate = new FindInterviewCommandPredicate(
                Collections.singletonList("Alice"),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList()
        );
        assertTrue(predicate.test(new InterviewBuilder().withCandidates(new HashSet<Person>(Arrays.asList(ALICE)))
                .build()));

        // Multiple keywords
        predicate = new FindInterviewCommandPredicate(
                Arrays.asList("Alice", "Benson"),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList()
        );
        assertTrue(predicate.test(new InterviewBuilder().withCandidates(new HashSet<Person>(Arrays.asList(ALICE,
                BENSON))).build()));

        // Only one matching keyword
        predicate = new FindInterviewCommandPredicate(
                Arrays.asList("Alice", "Benson"),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList()
        );
        assertTrue(predicate.test(new InterviewBuilder().withCandidates(new HashSet<Person>(Arrays.asList(ALICE,
                CARL))).build()));

        // Mixed-case keywords
        predicate = new FindInterviewCommandPredicate(Arrays.asList("aLiCe", "BENSON"),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList());
        assertTrue(predicate.test(new InterviewBuilder().withCandidates(new HashSet<Person>(Arrays.asList(ALICE,
                BENSON))).build()));
    }

    @Test
    public void test_candidatesDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FindInterviewCommandPredicate predicate = new FindInterviewCommandPredicate();

        // Non-matching keyword
        predicate = new FindInterviewCommandPredicate(
                Arrays.asList("Carl"),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList()
        );
        assertFalse(predicate.test(new InterviewBuilder().withCandidates(new HashSet<Person>(Arrays.asList(ALICE,
                BENSON))).build()));

        // Keywords match candidate, time, date but not status
        predicate = new FindInterviewCommandPredicate(Arrays.asList("Carl"),
                Arrays.asList("2021-09-21"),
                Arrays.asList("Completed"),
                Arrays.asList(),
                Arrays.asList(LocalTime.of(12, 0)));
        assertFalse(predicate.test(new InterviewBuilder()
                .withCandidates(new HashSet<Person>(Arrays.asList(CARL)))
                .withDate(LocalDate.of(2021, 9, 21))
                .withStartTime(LocalTime.of(12, 0))
                .withStatus(Interview.InterviewStatus.PENDING)
                .build()));

    }

}
