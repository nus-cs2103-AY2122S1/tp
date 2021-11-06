package dash.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import dash.model.person.Person;
import dash.testutil.PersonBuilder;
import dash.testutil.TaskBuilder;

public class PersonContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("alex");
        List<String> secondPredicateKeywordList = Arrays.asList("alex", "beatrice");

        PersonContainsKeywordsPredicate firstPredicate = new PersonContainsKeywordsPredicate(
                firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondPredicate = new PersonContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPredicateCopy = new PersonContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        // One keyword
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(Collections
                .singletonList("alex"));
        Person testPerson01 = new PersonBuilder().withName("alex").build();
        assertTrue(predicate.test(new TaskBuilder().withPeople(testPerson01).build()));

        // full name
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("alex yeoh"));
        Person testPerson02 = new PersonBuilder().withName("alex yeoh").build();
        assertTrue(predicate.test(new TaskBuilder().withPeople(testPerson02).build()));

    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(Arrays.asList("Beatrice"));
        Person testPerson01 = new PersonBuilder().withName("alex").build();
        assertFalse(predicate.test(new TaskBuilder().withPeople(testPerson01).build()));

        // Only one matching keyword, fails because of AND search
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("Alex", "Tan"));
        Person testPerson02 = new PersonBuilder().withName("alex yeoh").build();
        assertFalse(predicate.test(new TaskBuilder().withPeople(testPerson02).build()));

        // Keywords match description but not person name
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("Homework", "Urgent"));
        assertFalse(predicate.test(new TaskBuilder().withTaskDescription("Homework Urgent")
                .withPeople(testPerson02).build()));

        // keyword given is only half of the full name
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("Alex", "Tan"));
        Person testPerson03 = new PersonBuilder().withName("Alex Tan").build();
        assertFalse(predicate.test(new TaskBuilder().withPeople(testPerson02).build()));
    }
}
