package seedu.address.model.guest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.guest.GuestBuilder;

public class GuestPredicateTest {

    @Test
    public void equals() {
        GuestPredicate firstPredicate =
                new GuestPredicate(Optional.of("1232112SD"), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());
        GuestPredicate secondPredicate =
                new GuestPredicate(Optional.of("1231212DF"), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GuestPredicate firstPredicateCopy =
                new GuestPredicate(Optional.of("1232112SD"), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_passportNumberContainsKeywords_returnsTrue() {
        GuestPredicate predicate = new GuestPredicate(
                Optional.of("23132D"), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new GuestBuilder().withPassportNumber("23132D").build()));

        // Should be allowed
        predicate = new GuestPredicate(
                Optional.of("23132d"), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new GuestBuilder().withPassportNumber("23132D").build()));

        predicate = new GuestPredicate(
                Optional.of("   23132D   "), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new GuestBuilder().withPassportNumber("23132D").build()));

    }

    @Test
    public void test_passportNumberDoesNotContainsKeywords_returnsFalse() {
        GuestPredicate predicate = new GuestPredicate(
                Optional.of("12312D"), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty());

        // Non-matching keyword
        assertFalse(predicate.test(
                new GuestBuilder().withPassportNumber("123").build()));

        assertFalse(predicate.test(
                new GuestBuilder().withPassportNumber("312D").build()));

        // Keywords match name, email and other respective fields, but does not match passport number
        predicate = new GuestPredicate(
                Optional.of("12312D"), Optional.of("123"), Optional.of("Alice"),
                Optional.of("alice@email.com"), Optional.of(Set.of(new Tag("VIP"))));
        assertFalse(predicate.test(new GuestBuilder().withName("Alice").withRoomNumber("123")
                .withEmail("alice@email.com").withTags("VIP").withPassportNumber("123").build()));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        GuestPredicate predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.of("bill"),
                        Optional.empty(), Optional.empty());

        // Matching keywords (case-insensitive)
        assertTrue(predicate.test(
                new GuestBuilder().withName("Bill Nye").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.of("BiLl"),
                        Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new GuestBuilder().withName("Bill Nye").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.of("ill"),
                        Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new GuestBuilder().withName("Bill Nye").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.of("ill n"),
                        Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new GuestBuilder().withName("Bill Nye").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.of("nye"),
                        Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new GuestBuilder().withName("Bill Nye").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.of("   nye    "),
                        Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new GuestBuilder().withName("Bill Nye").build()));


    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        GuestPredicate predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.of("bill"),
                        Optional.empty(), Optional.empty());

        // Non-matching keyword
        assertFalse(predicate.test(new GuestBuilder().withName("Bing Cheng").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.of("cheng bing"),
                        Optional.empty(), Optional.empty());

        assertFalse(predicate.test(new GuestBuilder().withName("Bing Cheng").build()));

        // Keywords match passport number, email and other respective fields, but does not match name
        predicate = new GuestPredicate(
                Optional.of("12312D"), Optional.of("123"), Optional.of("Alice"),
                Optional.of("alice@email.com"), Optional.of(Set.of(new Tag("VIP"))));
        assertFalse(predicate.test(new GuestBuilder().withName("Bob").withRoomNumber("123")
                .withEmail("alice@email.com").withTags("VIP").withPassportNumber("12312D").build()));
    }

    @Test
    public void test_roomNumberContainsKeywords_returnsTrue() {
        GuestPredicate predicate =
                new GuestPredicate(Optional.empty(), Optional.of("1"), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new GuestBuilder().withRoomNumber("123").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.of("12"), Optional.empty(),
                        Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new GuestBuilder().withRoomNumber("123").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.of("123"), Optional.empty(),
                        Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new GuestBuilder().withRoomNumber("123").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.of("  123  "), Optional.empty(),
                        Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new GuestBuilder().withRoomNumber("123").build()));
    }

    @Test
    public void test_roomNumberDoesNotContainKeywords_returnsFalse() {
        GuestPredicate predicate =
                new GuestPredicate(Optional.empty(), Optional.of("1"), Optional.empty(),
                        Optional.empty(), Optional.empty());

        // Non-matching keyword
        assertFalse(predicate.test(
                new GuestBuilder().withRoomNumber("20").build()));

        // Non-level 1 room but contains 1
        assertFalse(predicate.test(
                new GuestBuilder().withRoomNumber("201").build()));

        // Keywords match passport number, email and other respective fields, but does not match room number
        predicate = new GuestPredicate(
                Optional.of("12312D"), Optional.of("123"), Optional.of("Alice"),
                Optional.of("alice@email.com"), Optional.of(Set.of(new Tag("VIP"))));
        assertFalse(predicate.test(new GuestBuilder().withName("Alice").withRoomNumber("456")
                .withEmail("alice@email.com").withTags("VIP").withPassportNumber("12312D").build()));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        GuestPredicate predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of("@email.com"), Optional.empty());

        assertTrue(predicate.test(
                new GuestBuilder().withEmail("alice@email.com").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of("alice"), Optional.empty());
        assertTrue(predicate.test(
                new GuestBuilder().withEmail("alice@email.com").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of("ce@em"), Optional.empty());
        assertTrue(predicate.test(
                new GuestBuilder().withEmail("alice@email.com").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of("AlICe"), Optional.empty());
        assertTrue(predicate.test(
                new GuestBuilder().withEmail("alice@email.com").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of("  lice  "), Optional.empty());
        assertTrue(predicate.test(
                new GuestBuilder().withEmail("alice@email.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        GuestPredicate predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of("ace"), Optional.empty());

        // Non-matching keyword
        assertFalse(predicate.test(
                new GuestBuilder().withEmail("alice@email.com").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of("emailalice"), Optional.empty());
        assertFalse(predicate.test(
                new GuestBuilder().withEmail("alice@email.com").build()));


        // Keywords match passport number, name and other respective fields, but does not match email
        predicate = new GuestPredicate(
                Optional.of("12312D"), Optional.of("123"), Optional.of("Alice"),
                Optional.of("alice@email.com"), Optional.of(Set.of(new Tag("VIP"))));
        assertFalse(predicate.test(new GuestBuilder().withName("Alice").withRoomNumber("123")
                .withEmail("bob@email.com").withTags("VIP").withPassportNumber("12312D").build()));
    }

    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        GuestPredicate predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.of(Set.of(new Tag("VIP"))));

        assertTrue(predicate.test(
                new GuestBuilder().withTags("Vip").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.of(Set.of(new Tag("ViP"))));

        assertTrue(predicate.test(
                new GuestBuilder().withTags("Vip").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.of(Set.of(new Tag("  vip  "))));

        assertTrue(predicate.test(
                new GuestBuilder().withTags("Vip").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.of(Set.of(new Tag("vip"), new Tag("second"))));

        assertTrue(predicate.test(
                new GuestBuilder().withTags("Vip").build()));

        assertTrue(predicate.test(
                new GuestBuilder().withTags("Second").build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        GuestPredicate predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.of(Set.of(new Tag("qwert"))));

        assertFalse(predicate.test(
                new GuestBuilder().withTags("Vip").build()));

        predicate =
                new GuestPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.of(Set.of(new Tag("ip"))));
        assertFalse(predicate.test(
                new GuestBuilder().withTags("Vip").build()));

        // Keywords match passport number, name and other respective fields, but does not match tags
        predicate = new GuestPredicate(
                Optional.of("12312D"), Optional.of("123"), Optional.of("Alice"),
                Optional.of("alice@email.com"), Optional.of(Set.of(new Tag("Deluxe"))));
        assertFalse(predicate.test(new GuestBuilder().withName("Alice").withRoomNumber("123")
                .withEmail("alice@email.com").withTags("Suite").withPassportNumber("12312D").build()));
    }

}
