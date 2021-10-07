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
        Person personToTest = createsPersonWithTags("Alice Bob", "Alice");
        assertTrue(firstPredicate.test(personToTest));

        // Multiple tag
        List<Tag> secondTags = new ArrayList<>();
        secondTags.add(new Tag("Alice"));
        secondTags.add(new Tag("Bob"));
        PersonTagsContainsTagsPredicate secondPredicate = new PersonTagsContainsTagsPredicate(secondTags);
        personToTest = createsPersonWithTags("Alice Bob", "Alice", "Bob");
        assertTrue(secondPredicate.test(personToTest));

        // Only one matching tag
        List<Tag> thirdTags = new ArrayList<>();
        thirdTags.add(new Tag("Bob"));
        thirdTags.add(new Tag("Carol"));
        PersonTagsContainsTagsPredicate thirdPredicate = new PersonTagsContainsTagsPredicate(thirdTags);
        personToTest = createsPersonWithTags("Alice Carol", "Friend", "Bob");
        assertFalse(thirdPredicate.test(personToTest));

        // Mixed-case tag
        List<Tag> fourthTags = new ArrayList<>();
        fourthTags.add(new Tag("BOb"));
        fourthTags.add(new Tag("cAroL"));
        PersonTagsContainsTagsPredicate fourthPredicate = new PersonTagsContainsTagsPredicate(fourthTags);
        personToTest = createsPersonWithTags("Alice Bob", "Carol", "Bob");
        assertTrue(fourthPredicate.test(personToTest));

        // Subset tag
        List<Tag> fifthTags = new ArrayList<>();
        fifthTags.add(new Tag("BOb"));
        fifthTags.add(new Tag("cAroL"));
        PersonTagsContainsTagsPredicate fifthPredicate = new PersonTagsContainsTagsPredicate(fifthTags);
        personToTest = createsPersonWithTags("Alice Bob", "Carol", "Bobs");
        assertFalse(fifthPredicate.test(personToTest));
    }
    @Test
    public void test_tagsContainOneTag_returnsTrue() {
        // One tag
        List<Tag> firstTags = new ArrayList<>();
        firstTags.add(new Tag("Alice"));
        PersonTagsContainsTagsPredicate firstPredicate = new PersonTagsContainsTagsPredicate(firstTags);
        Person personToTest = createsPersonWithTags("Alice Bob", "Alice");
        assertTrue(firstPredicate.test(personToTest));
    }

    @Test
    public void test_tagsContainMultipleTags_returnsTrue() {

        // Multiple tag
        List<Tag> secondTags = new ArrayList<>();
        secondTags.add(new Tag("Alice"));
        secondTags.add(new Tag("Bob"));
        PersonTagsContainsTagsPredicate secondPredicate = new PersonTagsContainsTagsPredicate(secondTags);
        Person personToTest = createsPersonWithTags("Alice Bob", "Alice", "Bob");
        assertTrue(secondPredicate.test(personToTest));
    }
    @Test
    public void test_tagsContainOnlyOneTag_returnsFalse() {
        // Only one matching tag
        List<Tag> thirdTags = new ArrayList<>();
        thirdTags.add(new Tag("Bob"));
        thirdTags.add(new Tag("Carol"));
        PersonTagsContainsTagsPredicate thirdPredicate = new PersonTagsContainsTagsPredicate(thirdTags);
        Person personToTest = createsPersonWithTags("Alice Carol", "Friend", "Bob");
        assertFalse(thirdPredicate.test(personToTest));
    }

    @Test
    public void test_tagsContainCaseInsensitiveTags_returnsTrue() {
        // Mixed-case tag
        List<Tag> fourthTags = new ArrayList<>();
        fourthTags.add(new Tag("BOb"));
        fourthTags.add(new Tag("cAroL"));
        PersonTagsContainsTagsPredicate fourthPredicate = new PersonTagsContainsTagsPredicate(fourthTags);
        Person personToTest = createsPersonWithTags("Alice Bob", "Carol", "Bob");
        assertTrue(fourthPredicate.test(personToTest));
    }

    @Test
    public void test_tagsContainSubsetTags_returnsFalse() {
        // Subset tag
        List<Tag> fifthTags = new ArrayList<>();
        fifthTags.add(new Tag("BOb"));
        fifthTags.add(new Tag("cAroL"));
        PersonTagsContainsTagsPredicate fifthPredicate = new PersonTagsContainsTagsPredicate(fifthTags);
        Person personToTest = createsPersonWithTags("Alice Bob", "Carol", "Bobs");
        assertFalse(fifthPredicate.test(personToTest));
    }

    @Test
    public void test_personOtherAttributeHasTagString_returnsFalse() {
        String[] tagString = new String[]{"12345", "Main", "Street"};
        List<Tag> secondTagList = Arrays.stream(tagString).map(Tag::new).collect(Collectors.toList());
        PersonTagsContainsTagsPredicate predicate = new PersonTagsContainsTagsPredicate(secondTagList);
        Person personToTest = new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build();
        assertFalse(predicate.test(personToTest));
    }

    @Test
    public void test_noTags_returnsFalse() {
        PersonTagsContainsTagsPredicate predicate = new PersonTagsContainsTagsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));
    }

    @Test
    public void test_tagDoesNotMatch_returnsFalse() {
        // Non-matching tag
        List<Tag> firstTagList = new ArrayList<>();
        firstTagList.add(new Tag("Carol"));
        PersonTagsContainsTagsPredicate predicate = new PersonTagsContainsTagsPredicate(firstTagList);
        Person personToTest = createsPersonWithTags("Alice Bob", "Alice", "Bob");
        assertFalse(predicate.test(personToTest));
    }

    private Person createsPersonWithTags(String name, String ... tags) {
        PersonBuilder builder = new PersonBuilder().withName(name);
        builder.withTags(tags);
        return builder.build();
    }
}
