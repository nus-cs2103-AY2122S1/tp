package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPositions.ADMIN_ASSISTANT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class FindCandidateCommandPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FindCandidateCommandPredicate firstPredicate = new FindCandidateCommandPredicate(firstPredicateKeywordList);
        FindCandidateCommandPredicate secondPredicate = new FindCandidateCommandPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FindCandidateCommandPredicate firstPredicateCopy = new FindCandidateCommandPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        FindCandidateCommandPredicate predicate = new FindCandidateCommandPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new FindCandidateCommandPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new FindCandidateCommandPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new FindCandidateCommandPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FindCandidateCommandPredicate predicate = new FindCandidateCommandPredicate();

        // Non-matching keyword
        predicate = new FindCandidateCommandPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new FindCandidateCommandPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void test_phoneContainsKeywords() {
        //valid number
        FindCandidateCommandPredicate predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), Arrays.asList(ALICE.getPhone().value),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertTrue(predicate.test(new PersonBuilder().withPhone(ALICE.getPhone().value).build()));

        //invalid input non-existent number
        predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), Arrays.asList("12345678"),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertFalse(predicate.test(new PersonBuilder().withPhone(ALICE.getPhone().value).build()));

        //invalid input not all numbers
        predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), Arrays.asList("1a345678"),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertFalse(predicate.test(new PersonBuilder().withPhone(ALICE.getPhone().value).build()));
    }

    @Test
    public void test_emailContainsKeywords() {
        //valid email
        FindCandidateCommandPredicate predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), new ArrayList<>(), Arrays.asList(ALICE.getEmail().value),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertTrue(predicate.test(new PersonBuilder().withEmail(ALICE.getEmail().value).build()));

        //invalid input non-existent email
        predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), new ArrayList<>(), Arrays.asList("Alice@gmail.com"),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertFalse(predicate.test(new PersonBuilder().withEmail(ALICE.getEmail().value).build()));

        //invalid input not an email
        predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), new ArrayList<>(), Arrays.asList("blah"),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertFalse(predicate.test(new PersonBuilder().withEmail(ALICE.getEmail().value).build()));
    }

    @Test
    public void test_addressContainsKeywords() {
        //valid address 1 keyword
        FindCandidateCommandPredicate predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                Arrays.asList("jurong"), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertTrue(predicate.test(new PersonBuilder().withAddress(ALICE.getAddress().value).build()));

        //valid address 2 keywords and mixed case
        predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                Arrays.asList("JuRong", "WeSt"), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertTrue(predicate.test(new PersonBuilder().withAddress(ALICE.getAddress().value).build()));


        //invalid input non-existent address
        predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                Arrays.asList("washington"), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertFalse(predicate.test(new PersonBuilder().withAddress(ALICE.getAddress().value).build()));

        //invalid input not an address
        predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                Arrays.asList(" ."), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        assertFalse(predicate.test(new PersonBuilder().withAddress(ALICE.getAddress().value).build()));
    }

    @Test
    public void test_tagsContainsKeywords() {
        //valid tag
        FindCandidateCommandPredicate predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), Arrays.asList(ALICE.getTagsString()), new ArrayList<>(), new ArrayList<>());
        assertTrue(predicate.test(new PersonBuilder().withTags(ALICE.getTagsString()).build()));

        //invalid input non-existent tag
        predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), Arrays.asList("bestfriend"), new ArrayList<>(), new ArrayList<>());
        assertFalse(predicate.test(new PersonBuilder().withTags(ALICE.getTagsString()).build()));
    }

    @Test
    public void test_statusContainsKeywords() {
        //valid status
        FindCandidateCommandPredicate predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), Arrays.asList(ALICE.getStatus().toString()), new ArrayList<>());
        assertTrue(predicate.test(new PersonBuilder().withStatus(ALICE.getStatus().toString()).build()));

        //valid status non-matching
        predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), Arrays.asList(Status.NONE.toString()), new ArrayList<>());
        assertFalse(predicate.test(new PersonBuilder().withStatus(ALICE.getStatus().toString()).build()));

        //invalid status not a status
        predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), Arrays.asList("withdrew"), new ArrayList<>());
        assertFalse(predicate.test(new PersonBuilder().withStatus(ALICE.getStatus().toString()).build()));
    }

    @Test
    public void test_positionContainsKeywords() {
        //valid position
        FindCandidateCommandPredicate predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), Arrays.asList(
                        BENSON.getPositionsString().split(" ")));
        assertTrue(predicate.test(new PersonBuilder().withPositions(BENSON.getPositionsString()).build()));


        //valid position not applied
        predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), Arrays.asList(
                ADMIN_ASSISTANT.getTitle().fullTitle.split(" ")));
        assertFalse(predicate.test(new PersonBuilder().withPositions(BENSON.getPositionsString()).build()));

        //invalid position
        predicate = new FindCandidateCommandPredicate(
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), Arrays.asList("HR-Manager"));
        assertFalse(predicate.test(new PersonBuilder().withPositions(BENSON.getPositionsString()).build()));
    }
}
