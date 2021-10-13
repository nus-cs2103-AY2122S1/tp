package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTagsContainsCaseSensitiveTagsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateTags = Collections.singletonList("first");
        List<String> secondPredicateTags = Arrays.asList("first", "second");
        List<Tag> firstPredicateTagList = firstPredicateTags.stream().map(Tag::new).collect(Collectors.toList());
        List<Tag> secondPredicateTagList = secondPredicateTags.stream().map(Tag::new).collect(Collectors.toList());
        PersonTagsContainsCaseSensitiveTagsPredicate firstPredicate = new PersonTagsContainsCaseSensitiveTagsPredicate(firstPredicateTagList);
        PersonTagsContainsCaseSensitiveTagsPredicate secondPredicate = new PersonTagsContainsCaseSensitiveTagsPredicate(secondPredicateTagList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonTagsContainsCaseSensitiveTagsPredicate firstPredicateCopy = new PersonTagsContainsCaseSensitiveTagsPredicate(firstPredicateTagList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainOneTag_returnsTrue() {
        // One tag
        List<Tag> firstTags = new ArrayList<>();
        firstTags.add(new Tag("Alice"));
        PersonTagsContainsCaseSensitiveTagsPredicate firstPredicate = new PersonTagsContainsCaseSensitiveTagsPredicate(firstTags);
        Person personToTest = createsPersonWithTags("Alice Bob", "Alice");
        assertTrue(firstPredicate.test(personToTest));
    }

    @Test
    public void test_tagsContainMultipleTags_returnsTrue() {

        // Multiple tag
        List<Tag> secondTags = new ArrayList<>();
        secondTags.add(new Tag("Alice"));
        secondTags.add(new Tag("Bob"));
        PersonTagsContainsCaseSensitiveTagsPredicate secondPredicate = new PersonTagsContainsCaseSensitiveTagsPredicate(secondTags);
        Person personToTest = createsPersonWithTags("Alice Bob", "Alice", "Bob");
        assertTrue(secondPredicate.test(personToTest));
    }
    @Test
    public void test_tagsContainOnlyOneTag_returnsFalse() {
        // Only one matching tag
        List<Tag> thirdTags = new ArrayList<>();
        thirdTags.add(new Tag("Bob"));
        thirdTags.add(new Tag("Carol"));
        PersonTagsContainsCaseSensitiveTagsPredicate thirdPredicate = new PersonTagsContainsCaseSensitiveTagsPredicate(thirdTags);
        Person personToTest = createsPersonWithTags("Alice Carol", "Friend", "Bob");
        assertFalse(thirdPredicate.test(personToTest));
    }

    @Test
    public void test_tagsContainCaseInsensitiveTags_returnsTrue() {
        // Mixed-case tag
        List<Tag> fourthTags = new ArrayList<>();
        fourthTags.add(new Tag("BOb"));
        fourthTags.add(new Tag("cAroL"));
        PersonTagsContainsCaseSensitiveTagsPredicate fourthPredicate = new PersonTagsContainsCaseSensitiveTagsPredicate(fourthTags);
        Person personToTest = createsPersonWithTags("Alice Bob", "Carol", "Bob");
        assertFalse(fourthPredicate.test(personToTest));
    }

    @Test
    public void test_tagsContainSubsetTags_returnsFalse() {
        // Subset tag
        List<Tag> fifthTags = new ArrayList<>();
        fifthTags.add(new Tag("BOb"));
        fifthTags.add(new Tag("cAroL"));
        PersonTagsContainsCaseSensitiveTagsPredicate fifthPredicate =
                new PersonTagsContainsCaseSensitiveTagsPredicate(fifthTags);
        Person personToTest = createsPersonWithTags("Alice Bob", "Carol", "Bobs");
        assertFalse(fifthPredicate.test(personToTest));
    }

    @Test
    public void test_personOtherAttributeHasTagString_returnsFalse() {
        String[] tagString = new String[]{"12345", "Main", "Street"};
        List<Tag> secondTagList = Arrays.stream(tagString).map(Tag::new).collect(Collectors.toList());
        PersonTagsContainsCaseSensitiveTagsPredicate predicate =
                new PersonTagsContainsCaseSensitiveTagsPredicate(secondTagList);
        Person personToTest = new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build();
        assertFalse(predicate.test(personToTest));
    }

    @Test
    public void test_noTags_returnsTrue() {
        PersonTagsContainsCaseSensitiveTagsPredicate predicate =
                new PersonTagsContainsCaseSensitiveTagsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));
    }

    @Test
    public void test_tagDoesNotMatch_returnsFalse() {
        // Non-matching tag
        List<Tag> firstTagList = new ArrayList<>();
        firstTagList.add(new Tag("Carol"));
        PersonTagsContainsCaseSensitiveTagsPredicate predicate =
                new PersonTagsContainsCaseSensitiveTagsPredicate(firstTagList);
        Person personToTest = createsPersonWithTags("Alice Bob", "Alice", "Bob");
        assertFalse(predicate.test(personToTest));
    }

    private Person createsPersonWithTags(String name, String ... tags) {
        PersonBuilder builder = new PersonBuilder().withName(name);
        builder.withTags(tags);
        return builder.build();
    }
}
