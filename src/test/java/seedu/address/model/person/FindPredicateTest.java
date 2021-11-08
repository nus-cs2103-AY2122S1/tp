package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;


public class FindPredicateTest {

    @Test
    public void equals() {
        List<Name> firstNameList = List.of(new Name("Alice"), new Name("Bob"));
        List<Tag> firstTagList = List.of(new Tag("friends"), new Tag("colleagues"));
        List<Name> secondNameList = List.of(new Name("Carl"), new Name("Denzel"));
        List<Tag> secondTagList = List.of(new Tag("classmates"), new Tag("project"));

        FindPredicate firstPredicateCaseInsensitive =
                new FindPredicate(firstNameList, firstTagList, false);
        FindPredicate secondPredicateCaseInsensitive =
                new FindPredicate(secondNameList, secondTagList, false);
        FindPredicate firstPredicateCaseSensitive =
                new FindPredicate(firstNameList, firstTagList, true);
        FindPredicate secondPredicateCaseSensitive =
                new FindPredicate(secondNameList, secondTagList, true);


        // same object -> returns true
        assertTrue(firstPredicateCaseInsensitive.equals(firstPredicateCaseInsensitive));
        assertTrue(firstPredicateCaseSensitive.equals(firstPredicateCaseSensitive));

        // same values -> returns true
        FindAnyPredicate firstPredicateCaseInsensitiveCopy =
                new FindAnyPredicate(firstNameList, firstTagList, false);
        assertTrue(firstPredicateCaseInsensitive.equals(firstPredicateCaseInsensitive));

        // different types -> returns false
        assertFalse(firstPredicateCaseInsensitive.equals(1));

        // null -> returns false
        assertFalse(firstPredicateCaseInsensitive.equals(null));

        // different person -> returns false
        assertFalse(firstPredicateCaseInsensitive.equals(secondPredicateCaseInsensitive));
        assertFalse(firstPredicateCaseSensitive.equals(secondPredicateCaseSensitive));
    }

    @Test
    public void test_oneMatchingName_returnsTrue() {
        FindPredicate predicate =
                new FindPredicate(List.of(new Name("Alice")), List.of(), false);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_oneNonMatchingName_returnsFalse() {
        FindPredicate predicate =
                new FindPredicate(List.of(new Name("Alice")), List.of(), false);
        assertFalse(predicate.test(new PersonBuilder().withName("Bob").build()));
    }

    @Test
    public void test_multipleMatchingName_returnsTrue() {
        FindPredicate predicate =
                new FindPredicate(List.of(new Name("Alice"), new Name("Bob")), List.of(), false);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_multipleNonMatchingName_returnsFalse() {
        FindPredicate predicate =
                new FindPredicate(List.of(new Name("Alice"), new Name("Bob")), List.of(), false);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Carol").build()));
    }

    @Test
    public void test_oneMatchingNameAndOneMatchingTag_returnsTrue() {
        FindPredicate predicate =
                new FindPredicate(List.of(new Name("Bob")), List.of(new Tag("friends")),
                        false);
        assertTrue(predicate.test(new PersonBuilder().withName("bob").withTags("friends").build()));
    }

    @Test
    public void test_oneMatchingNameAndOneNonMatchingTag_returnsFalse() {
        FindPredicate predicate =
                new FindPredicate(List.of(new Name("Bob")), List.of(new Tag("friend")),
                        false);
        assertFalse(predicate.test(new PersonBuilder().withName("bob").withTags("chef").build()));

        predicate = new FindPredicate(List.of(new Name("Bob")), List.of(new Tag("friend")),
                true);
        // returns false because both name or tag needs to match
        assertFalse(predicate.test(new PersonBuilder().withName("bob").withTags("chef").build()));
    }

    @Test
    public void test_oneNonMatchingNameAndOneMatchingTag_returnsTrue() {
        FindPredicate predicate =
                new FindPredicate(List.of(new Name("Bob")), List.of(new Tag("friend")),
                        false);
        assertFalse(predicate.test(new PersonBuilder().withName("Charlie").withTags("friend").build()));

        predicate = new FindPredicate(List.of(new Name("Bob")), List.of(new Tag("FRIEND")),
                true);
        assertFalse(predicate.test(new PersonBuilder().withName("Charlie").withTags("friend").build()));

        predicate = new FindPredicate(List.of(new Name("Bob")), List.of(new Tag("friend")),
                true);
        assertFalse(predicate.test(new PersonBuilder().withName("Charlie").withTags("friend").build()));
    }
}
