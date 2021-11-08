package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PartialKeyContainsKeywordsPredicateTest {

    public static final String NAME_TYPE = "n/";
    public static final String STUDENT_ID_TYPE = "s/";
    public static final String NUSNET_ID_TYPE = "N/";
    public static final String EMAIL_TYPE = "e/";
    public static final String GITHUB_ID_TYPE = "g/";
    public static final String PHONE_TYPE = "p/";
    public static final String ADDRESS_TYPE = "a/";

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PartialKeyContainsKeywordsPredicate firstPredicate =
                new PartialKeyContainsKeywordsPredicate(firstPredicateKeywordList, NAME_TYPE);
        PartialKeyContainsKeywordsPredicate secondPredicate =
                new PartialKeyContainsKeywordsPredicate(secondPredicateKeywordList, NAME_TYPE);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PartialKeyContainsKeywordsPredicate firstPredicateCopy =
                new PartialKeyContainsKeywordsPredicate(firstPredicateKeywordList, NAME_TYPE);
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
        PartialKeyContainsKeywordsPredicate predicate =
                new PartialKeyContainsKeywordsPredicate(Collections.singletonList("Alice"), NAME_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"), NAME_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords with different order
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("Bob", "Alice"), NAME_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"), NAME_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"), NAME_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Partial keywords
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("ali", "bo"), NAME_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PartialKeyContainsKeywordsPredicate predicate =
                new PartialKeyContainsKeywordsPredicate(Collections.emptyList(), NAME_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("Carol"), NAME_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Partial key that does not start from the first index of each word
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("arol"), NAME_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Carol").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays
                .asList("12345", "e1234567@u.nus.edu", "Main", "Street"), NAME_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("e1234567@u.nus.edu").withAddress("Main Street").build()));
    }

    @Test
    public void test_studentIdContainsKeywords_returnsTrue() {
        // Full Student ID
        PartialKeyContainsKeywordsPredicate predicate =
                new PartialKeyContainsKeywordsPredicate(Arrays.asList("A0226588N"), STUDENT_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withStudentId("A0226588N").build()));

        // Full Student ID with lowercase letters
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("a0226588n"), STUDENT_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withStudentId("A0226588N").build()));

        // Partial Student ID
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("A022"), STUDENT_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withStudentId("A0226588N").build()));
    }

    @Test
    public void test_studentIdDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        PartialKeyContainsKeywordsPredicate predicate =
                new PartialKeyContainsKeywordsPredicate(Collections.emptyList(), STUDENT_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withStudentId("A0226588N").build()));

        // Non-Matching Student ID
        predicate =
                new PartialKeyContainsKeywordsPredicate(Arrays.asList("A0226588O"), STUDENT_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withStudentId("A0226588N").build()));

        // Student ID that does not start with 'A' or 'a'
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("022"), STUDENT_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withStudentId("A0226588N").build()));

        // Keywords match phone, email and address, but does not match Student ID
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays
                .asList("12345", "e1234567@u.nus.edu", "Main", "Street", "A0226588O"), STUDENT_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("e1234567@u.nus.edu").withAddress("Main Street").withStudentId("A0226588N").build()));
    }

    @Test
    public void test_nusNetIdContainsKeywords_returnsTrue() {
        // Full NUSNet ID
        PartialKeyContainsKeywordsPredicate predicate =
                new PartialKeyContainsKeywordsPredicate(Arrays.asList("E0638874"), NUSNET_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withNusNetworkId("E0638874").build()));

        // Full NUSNet ID with lowercase letters
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("e0638874"), NUSNET_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withNusNetworkId("E0638874").build()));

        // Partial NUSNet ID
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("E06"), NUSNET_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withNusNetworkId("E0638874").build()));
    }

    @Test
    public void test_nusNetIdDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        PartialKeyContainsKeywordsPredicate predicate =
                new PartialKeyContainsKeywordsPredicate(Collections.emptyList(), NUSNET_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withNusNetworkId("E0638874").build()));

        // Non-Matching NUSNet ID
        predicate =
                new PartialKeyContainsKeywordsPredicate(Arrays.asList("E0638875"), NUSNET_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withNusNetworkId("E0638874").build()));

        // NUSNet ID that does not start with 'E' or 'e'
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("063"), NUSNET_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withNusNetworkId("E0638874").build()));

        // Keywords match phone, email and address, but does not match NUSNet ID
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays
                .asList("12345", "e1234567@u.nus.edu", "Main", "Street", "E0638875"), NUSNET_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("e1234567@u.nus.edu").withAddress("Main Street").withNusNetworkId("E0638874").build()));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // Full Email
        PartialKeyContainsKeywordsPredicate predicate =
                new PartialKeyContainsKeywordsPredicate(Arrays.asList("e1234567@u.nus.edu"), EMAIL_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withEmail("e1234567@u.nus.edu").build()));

        // Full NUSNet ID with mixed-case letters
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("E1234567@U.nUs.Edu"), EMAIL_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withEmail("e1234567@u.nus.edu").build()));

        // Partial Email
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("e1234"), EMAIL_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withEmail("e1234567@u.nus.edu").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        PartialKeyContainsKeywordsPredicate predicate =
                new PartialKeyContainsKeywordsPredicate(Collections.emptyList(), EMAIL_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withEmail("e1234567@u.nus.edu").build()));

        // Non-Matching email
        predicate =
                new PartialKeyContainsKeywordsPredicate(Arrays.asList("e1234568@u.nus.edu"), EMAIL_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withEmail("e1234567@u.nus.edu").build()));

        // Keywords match phone, email and address, but does not match NUSNet ID
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays
                .asList("12345", "e1234560@u.nus.edu", "Main", "Street", "Alice"), EMAIL_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("e1234567@u.nus.edu").withAddress("Main Street").build()));
    }

    @Test
    public void test_githubIdContainsKeywords_returnsTrue() {
        // Full Github ID
        PartialKeyContainsKeywordsPredicate predicate =
                new PartialKeyContainsKeywordsPredicate(Arrays.asList("RachelCheah"), GITHUB_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withGitHubId("RachelCheah").build()));

        // Full Github ID with mixed-case
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("rAcHeLcHeAh"), GITHUB_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withGitHubId("RachelCheah").build()));

        // Partial Github ID
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("rac"), GITHUB_ID_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withGitHubId("RachelCheah").build()));
    }

    @Test
    public void test_githubIdDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        PartialKeyContainsKeywordsPredicate predicate =
                new PartialKeyContainsKeywordsPredicate(Collections.emptyList(), GITHUB_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withGitHubId("RachelCheah").build()));

        // Non-Matching Github ID
        predicate =
                new PartialKeyContainsKeywordsPredicate(Arrays.asList("Siddharth-Sid"), GITHUB_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withGitHubId("RachelCheah").build()));

        // Keywords match phone, email and address, but does not match Github ID
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays
                .asList("12345", "e1234567@u.nus.edu", "Main", "Street", "Siddharth-Sid"), GITHUB_ID_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("e1234567@u.nus.edu").withAddress("Main Street").withGitHubId("RachelCheah").build()));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // Full Phone number
        PartialKeyContainsKeywordsPredicate predicate =
                new PartialKeyContainsKeywordsPredicate(Arrays.asList("98765432"), PHONE_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withPhone("98765432").build()));

        // Partial Phone
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("987"), PHONE_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withPhone("98765432").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsTrue() {
        // Zero keywords
        PartialKeyContainsKeywordsPredicate predicate =
                new PartialKeyContainsKeywordsPredicate(Collections.emptyList(), PHONE_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withPhone("98765432").build()));

        // Non-Matching Phone
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("98765430"), PHONE_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withPhone("98765432").build()));

        // Keywords match name, email and address, but does not match phone
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays
                .asList("98765430", "e1234567@u.nus.edu", "Main", "Street", "11"), PHONE_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice")
                .withEmail("e1234567@u.nus.edu").withAddress("Main Street").withPhone("98765432").build()));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        // One keyword
        PartialKeyContainsKeywordsPredicate predicate =
                new PartialKeyContainsKeywordsPredicate(Collections.singletonList("John"), ADDRESS_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));

        // Multiple keywords
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("John", "street"), ADDRESS_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));

        // Keywords with different order
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("block", "John"), ADDRESS_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));

        // Only one matching keyword
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("John", "blk"), ADDRESS_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));

        // Mixed-case keywords
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("joHn", "sTrEeT"), ADDRESS_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));

        // Partial keywords
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("Jo", "str"), ADDRESS_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PartialKeyContainsKeywordsPredicate predicate =
                new PartialKeyContainsKeywordsPredicate(Collections.emptyList(), ADDRESS_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));

        // Non-matching keyword
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("main"), ADDRESS_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));

        // Partial key that does not start from the first index of each word
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays.asList("ohn"), ADDRESS_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withAddress("John street, block 123").build()));

        // Keywords match phone, email and name, but does not match address
        predicate = new PartialKeyContainsKeywordsPredicate(Arrays
                .asList("12345", "e1234567@u.nus.edu", "Main", "Alice"), ADDRESS_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("e1234567@u.nus.edu").withAddress("John street, block 123").build()));
    }
}
