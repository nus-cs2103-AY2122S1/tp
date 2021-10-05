package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class PersonTagsContainsTagsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateTags = Collections.singletonList("first");
        List<String> secondPredicateTags = Arrays.asList("first", "second");
        List<Tag> firstPredicateTagList = firstPredicateTags.stream().map(Tag::new).collect(Collectors.toList());
        List<Tag> secondPredicateTagList = secondPredicateTags.stream().map(Tag::new).collect(Collectors.toList());
        PersonTagsContainsTagsPredicate firstPredicate = new PersonTagsContainsTagsPredicate(firstPredicateTagList);
        PersonTagsContainsTagsPredicate secondPredicate = new PersonTagsContainsTagsPredicate(secondPredicateTagList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonTagsContainsTagsPredicate firstPredicateCopy = new PersonTagsContainsTagsPredicate(firstPredicateTagList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainTags_returnsTrue() {
        // One tag
        List<Tag> firstTags = new ArrayList<>();
        firstTags.add(new Tag("Alice"));
        PersonTagsContainsTagsPredicate firstPredicate = new PersonTagsContainsTagsPredicate(firstTags);
        assertTrue(firstPredicate.test(new PersonBuilder().withName("Alice Bob").withTags("Alice").build()));

        // Multiple tag
        List<Tag> secondTags = new ArrayList<>();
        secondTags.add(new Tag("Alice"));
        secondTags.add(new Tag("Bob"));
        PersonTagsContainsTagsPredicate secondPredicate = new PersonTagsContainsTagsPredicate(secondTags);
        assertTrue(secondPredicate.test(new PersonBuilder().withName("Alice Bob").withTags("Alice", "Bob").build()));

        // Only one matching tag
        List<Tag> thirdTags = new ArrayList<>();
        thirdTags.add(new Tag("Bob"));
        thirdTags.add(new Tag("Carol"));
        PersonTagsContainsTagsPredicate thirdPredicate = new PersonTagsContainsTagsPredicate(thirdTags);
        assertFalse(thirdPredicate.test(new PersonBuilder().withName("Alice Carol").withTags("Bob", "Friend").build()));

        // Mixed-case tag
        List<Tag> fourthTags = new ArrayList<>();
        fourthTags.add(new Tag("BOb"));
        fourthTags.add(new Tag("cAroL"));
        PersonTagsContainsTagsPredicate fourthPredicate = new PersonTagsContainsTagsPredicate(fourthTags);
        assertTrue(fourthPredicate.test(new PersonBuilder().withName("Alice Bob").withTags("Bob", "Carol").build()));

        // Subset tag
        List<Tag> fifthTags = new ArrayList<>();
        fifthTags.add(new Tag("BOb"));
        fifthTags.add(new Tag("cAroL"));
        PersonTagsContainsTagsPredicate fifthPredicate = new PersonTagsContainsTagsPredicate(fifthTags);
        assertFalse(fifthPredicate.test(new PersonBuilder().withName("Alice Bob").withTags("Bobs", "Carol").build()));
    }

    @Test
    public void test_tagsDoesNotContainTags_returnsFalse() {
        // Zero tags
        PersonTagsContainsTagsPredicate predicate = new PersonTagsContainsTagsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching tag
        List<Tag> firstTagList = new ArrayList<>();
        firstTagList.add(new Tag("Carol"));
        predicate = new PersonTagsContainsTagsPredicate(firstTagList);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("Alice", "Bob").build()));

        // Tag match phone, email and address, but does not match tags of person
        String[] tagString = new String[]{"12345", "Main", "Street"};
        List<Tag> secondTagList = Arrays.stream(tagString).map(Tag::new).collect(Collectors.toList());
        predicate = new PersonTagsContainsTagsPredicate(secondTagList);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
