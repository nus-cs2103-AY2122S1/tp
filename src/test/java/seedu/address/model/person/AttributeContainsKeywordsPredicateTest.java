package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class AttributeContainsKeywordsPredicateTest {

    public static final String NAME_TYPE = "n/";
    public static final String STUDENT_ID_TYPE = "s/";
    public static final String NUSNET_ID_TYPE = "N/";
    public static final String EMAIL_TYPE = "e/";
    public static final String GITHUB_ID_TYPE = "g/";
    public static final String TUTORIAL_ID_TYPE = "T/";
    public static final String ROLE_TYPE = "r/";
    public static final String PHONE_TYPE = "p/";
    public static final String ADDRESS_TYPE = "a/";
    public static final String TAG_TYPE = "t/";

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AttributeContainsKeywordsPredicate firstPredicate =
                new AttributeContainsKeywordsPredicate(firstPredicateKeywordList, NAME_TYPE);
        AttributeContainsKeywordsPredicate secondPredicate =
                new AttributeContainsKeywordsPredicate(secondPredicateKeywordList, NAME_TYPE);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AttributeContainsKeywordsPredicate firstPredicateCopy =
                new AttributeContainsKeywordsPredicate(firstPredicateKeywordList, NAME_TYPE);
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
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Collections.singletonList("Alice"), NAME_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"), NAME_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords with different order
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("Bob", "Alice"), NAME_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"), NAME_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"), NAME_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Partial keywords
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("ali", "bo"), NAME_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Collections.emptyList(), NAME_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("Carol"), NAME_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Partial key that does not start from the first index of each word
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("arol"), NAME_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Carol").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new AttributeContainsKeywordsPredicate(Arrays
                .asList("12345", "e1234567@u.nus.edu", "Main", "Street"), NAME_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("e1234567@u.nus.edu").withAddress("Main Street").build()));
    }

    @Test
    public void test_StudentIdContainsKeywords_returnsTrue() {
        // Full Student ID
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Arrays.asList("A0226588N"), STUDENT_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withStudentId("A0226588N").build()));

        // Full Student ID with lowercase letters
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("a0226588n"), STUDENT_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withStudentId("A0226588N").build()));

        // Partial Student ID
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("A022"), STUDENT_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withStudentId("A0226588N").build()));
    }

    @Test
    public void test_StudentIdDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Collections.emptyList(), STUDENT_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withStudentId("A0226588N").build()));

        // Non-Matching Student ID
        predicate =
                new AttributeContainsKeywordsPredicate(Arrays.asList("A0226588O"), STUDENT_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withStudentId("A0226588N").build()));

        // Student ID that does not start with 'A' or 'a'
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("022"), STUDENT_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withStudentId("A0226588N").build()));

        // Keywords match phone, email and address, but does not match Student ID
        predicate = new AttributeContainsKeywordsPredicate(Arrays
                .asList("12345", "e1234567@u.nus.edu", "Main", "Street", "A0226588O"), STUDENT_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("e1234567@u.nus.edu").withAddress("Main Street").withStudentId("A0226588N").build()));
    }

    @Test
    public void test_NusNetIdContainsKeywords_returnsTrue() {
        // Full NUSNet ID
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Arrays.asList("E0638874"), NUSNET_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withNusNetworkId("E0638874").build()));

        // Full NUSNet ID with lowercase letters
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("e0638874"), NUSNET_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withNusNetworkId("E0638874").build()));

        // Partial NUSNet ID
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("E06"), NUSNET_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withNusNetworkId("E0638874").build()));
    }

    @Test
    public void test_NusNetIdDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Collections.emptyList(), NUSNET_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withNusNetworkId("E0638874").build()));

        // Non-Matching NUSNet ID
        predicate =
                new AttributeContainsKeywordsPredicate(Arrays.asList("E0638875"), NUSNET_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withNusNetworkId("E0638874").build()));

        // NUSNet ID that does not start with 'E' or 'e'
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("063"), NUSNET_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withNusNetworkId("E0638874").build()));

        // Keywords match phone, email and address, but does not match NUSNet ID
        predicate = new AttributeContainsKeywordsPredicate(Arrays
                .asList("12345", "e1234567@u.nus.edu", "Main", "Street", "E0638875"), NUSNET_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("e1234567@u.nus.edu").withAddress("Main Street").withNusNetworkId("E0638874").build()));
    }

    @Test
    public void test_EmailContainsKeywords_returnsTrue() {
        // Full Email
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Arrays.asList("e1234567@u.nus.edu"), EMAIL_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withEmail("e1234567@u.nus.edu").build()));

        // Full NUSNet ID with mixed-case letters
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("E1234567@U.nUs.Edu"), EMAIL_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withEmail("e1234567@u.nus.edu").build()));

        // Partial Email
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("e1234"), EMAIL_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withEmail("e1234567@u.nus.edu").build()));
    }

    @Test
    public void test_EmailDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Collections.emptyList(), EMAIL_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withEmail("e1234567@u.nus.edu").build()));

        // Non-Matching email
        predicate =
                new AttributeContainsKeywordsPredicate(Arrays.asList("e1234568@u.nus.edu"), EMAIL_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withEmail("e1234567@u.nus.edu").build()));

        // Keywords match phone, email and address, but does not match NUSNet ID
        predicate = new AttributeContainsKeywordsPredicate(Arrays
                .asList("12345", "e1234560@u.nus.edu", "Main", "Street", "Alice"), EMAIL_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("e1234567@u.nus.edu").withAddress("Main Street").build()));
    }

    @Test
    public void test_GithubIdContainsKeywords_returnsTrue() {
        // Full Github ID
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Arrays.asList("RachelCheah"), GITHUB_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withGitHubId("RachelCheah").build()));

        // Full Github ID with mixed-case
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("rAcHeLcHeAh"), GITHUB_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withGitHubId("RachelCheah").build()));

        // Partial Github ID
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("rac"), GITHUB_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withGitHubId("RachelCheah").build()));
    }

    @Test
    public void test_GithubIdDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Collections.emptyList(), GITHUB_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withGitHubId("RachelCheah").build()));

        // Non-Matching Github ID
        predicate =
                new AttributeContainsKeywordsPredicate(Arrays.asList("Siddharth-Sid"), GITHUB_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withGitHubId("RachelCheah").build()));

        // Keywords match phone, email and address, but does not match Github ID
        predicate = new AttributeContainsKeywordsPredicate(Arrays
                .asList("12345", "e1234567@u.nus.edu", "Main", "Street", "Siddharth-Sid"), GITHUB_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("e1234567@u.nus.edu").withAddress("Main Street").withGitHubId("RachelCheah").build()));
    }

    @Test
    public void test_TutorialIdContainsKeywords_returnsTrue() {
        // Full Tutorial ID
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Arrays.asList("10"), TUTORIAL_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withTutorialId("10").build()));
    }

    @Test
    public void test_TutorialIdDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Collections.emptyList(), TUTORIAL_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withTutorialId("10").build()));

        // Non-Matching Tutorial ID
        predicate =
                new AttributeContainsKeywordsPredicate(Arrays.asList("11"), TUTORIAL_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withTutorialId("10").build()));

        // Keywords match phone, email and address, but does not match Tutorial ID
        predicate = new AttributeContainsKeywordsPredicate(Arrays
                .asList("12345", "e1234567@u.nus.edu", "Main", "Street", "11"), TUTORIAL_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("e1234567@u.nus.edu").withAddress("Main Street").withTutorialId("10").build()));
    }

    @Test
    public void test_RoleContainsKeywords_returnsTrue() {
        // Full Role name
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Arrays.asList("student"), ROLE_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withType("student").build()));
    }

    @Test
    public void test_RoleDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Collections.emptyList(), ROLE_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withType("student").build()));

        // Non-Matching Role
        predicate =
                new AttributeContainsKeywordsPredicate(Arrays.asList("tutor"), ROLE_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withType("student").build()));

        // Keywords match phone, email and address, but does not match Role
        predicate = new AttributeContainsKeywordsPredicate(Arrays
                .asList("12345", "e1234567@u.nus.edu", "Main", "Street", "11"), ROLE_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("e1234567@u.nus.edu").withAddress("Main Street").withType("student").build()));
    }

    @Test
    public void test_PhoneContainsKeywords_returnsTrue() {
        // Full Phone number
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Arrays.asList("98765432"), PHONE_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withPhone("98765432").build()));

        // Partial Phone
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("987"), PHONE_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withPhone("98765432").build()));
    }

    @Test
    public void test_PhoneDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Collections.emptyList(), PHONE_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withPhone("98765432").build()));

        // Non-Matching Phone
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("98765430"), PHONE_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withPhone("98765432").build()));

        // Keywords match name, email and address, but does not match phone
        predicate = new AttributeContainsKeywordsPredicate(Arrays
                .asList("98765430", "e1234567@u.nus.edu", "Main", "Street", "11"), PHONE_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice")
                .withEmail("e1234567@u.nus.edu").withAddress("Main Street").withPhone("98765432").build()));
    }

    @Test
    public void test_AddressContainsKeywords_returnsTrue() {
        // One keyword
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Collections.singletonList("John"), ADDRESS_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));

        // Multiple keywords
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("John", "street"), ADDRESS_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));

        // Keywords with different order
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("block", "John"), ADDRESS_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));

        // Only one matching keyword
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("John", "blk"), ADDRESS_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));

        // Mixed-case keywords
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("joHn", "sTrEeT"), ADDRESS_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));

        // Partial keywords
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("Jo", "str"), ADDRESS_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));
    }

    @Test
    public void test_AddressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Collections.emptyList(), ADDRESS_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));

        // Non-matching keyword
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("main"), ADDRESS_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));

        // Partial key that does not start from the first index of each word
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("ohn"), ADDRESS_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));

        // Keywords match phone, email and name, but does not match address
        predicate = new AttributeContainsKeywordsPredicate(Arrays
                .asList("12345", "e1234567@u.nus.edu", "Main", "Alice"), ADDRESS_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("e1234567@u.nus.edu").withAddress("John street, block 123").build()));
    }

    @Test
    public void test_TagContainsKeywords_returnsTrue() {
        // Full Tag
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Arrays.asList("t/", "friends"), TAG_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));
    }

    @Test
    public void test_TagDoesNotContainKeywords_returnsTrue() {
        // Full Tag with mixed-case
        AttributeContainsKeywordsPredicate predicate =
                new AttributeContainsKeywordsPredicate(Arrays.asList("t/", "frIends"), TAG_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Partial Tag
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("t/", "fri"), TAG_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Non-Matching Tag
        predicate =
                new AttributeContainsKeywordsPredicate(Arrays.asList("t/", "colleagues"), TAG_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withTags("friends").build()));
    }
}
