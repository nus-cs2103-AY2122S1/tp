package seedu.address.model.vendor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.vendor.VendorBuilder;

public class VendorPredicateTest {

    @Test
    public void equals() {
        VendorPredicate firstPredicate =
                new VendorPredicate(Optional.of("001"), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());
        VendorPredicate secondPredicate =
                new VendorPredicate(Optional.of("002"), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        VendorPredicate firstPredicateCopy =
                new VendorPredicate(Optional.of("001"), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
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
    public void test_vendorIdContainsKeywords_returnsTrue() {
        VendorPredicate predicate = new VendorPredicate(Optional.of("123"), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new VendorBuilder().withVendorId("123").build()));

        // Should be allowed
        predicate = new VendorPredicate(Optional.of("s123"), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new VendorBuilder().withVendorId("S123").build()));

        predicate = new VendorPredicate(Optional.of("   123   "), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new VendorBuilder().withVendorId("123").build()));

    }

    @Test
    public void test_vendorIdDoesNotContainsKeywords_returnsFalse() {
        VendorPredicate predicate = new VendorPredicate(Optional.of("1"), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty());

        // Non-matching keyword
        assertFalse(predicate.test(
                new VendorBuilder().withVendorId("123").build()));

        predicate = new VendorPredicate(Optional.of("23"), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty());

        assertFalse(predicate.test(
                new VendorBuilder().withVendorId("123").build()));

        // Keywords match name, email and other respective fields, but does not match vendor id
        predicate = new VendorPredicate(Optional.of("123"), Optional.of("80958483"), Optional.of("Bill's Delivery"),
                Optional.of("bdelivery@email.com"), Optional.of(Set.of(new Tag("Halal"))),
                Optional.of("123 Clementi Rd, 123456"), Optional.of("Food"),
                Optional.of("12"), Optional.of("123 0900-1600"));
        assertFalse(predicate.test(
                new VendorBuilder().withVendorId("456").withPhone("80958483").withName("Bill's Delivery").withEmail(
                        "bdelivery@email.com").withTags("Halal").withAddress("123 Clementi Rd, 123456").withServiceName(
                        "Food").withCost("12").withOperatingHours("123 0900-1600").build()));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        VendorPredicate predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.of("bill"),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        // Matching keywords (case-insensitive)
        assertTrue(predicate.test(
                new VendorBuilder().withName("Bill's Delivery").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.of("Ill'"),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new VendorBuilder().withName("Bill's Delivery").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.of("'s deli"),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new VendorBuilder().withName("Bill's Delivery").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.of("delivery"),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new VendorBuilder().withName("Bill's Delivery").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.of("   bill    "),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());
        assertTrue(predicate.test(
                new VendorBuilder().withName("Bill's Delivery").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        VendorPredicate predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.of("bob"),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        // Non-matching keyword
        assertFalse(predicate.test(
                new VendorBuilder().withName("Bill's Delivery").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.of("delivery bill"),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertFalse(predicate.test(
                new VendorBuilder().withName("Bill's Delivery").build()));

        // Keywords match passport number, email and other respective fields, but does not match name
        predicate = new VendorPredicate(Optional.of("123"), Optional.of("80958483"), Optional.of("Bill's Delivery"),
                Optional.of("bdelivery@email.com"), Optional.of(Set.of(new Tag("Halal"))),
                Optional.of("123 Clementi Rd, 123456"), Optional.of("Food"),
                Optional.of("12"), Optional.of("123 0900-1600"));
        assertFalse(predicate.test(
                new VendorBuilder().withVendorId("123").withPhone("80958483").withName("Bob's Delivery").withEmail(
                        "bdelivery@email.com").withTags("Halal").withAddress("123 Clementi Rd, 123456").withServiceName(
                        "Food").withCost("12").withOperatingHours("123 0900-1600").build()));
    }


    @Test
    public void test_phoneNumberContainsKeywords_returnsTrue() {
        VendorPredicate predicate =
                new VendorPredicate(Optional.empty(), Optional.of("87878787"), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withPhone("87878787").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.of("878"), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withPhone("87878787").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.of(""), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withPhone("87878787").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.of("   878   "), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withPhone("87878787").build()));
    }

    @Test
    public void test_phoneNumberDoesNotContainKeywords_returnsFalse() {
        VendorPredicate predicate =
                new VendorPredicate(Optional.empty(), Optional.of("123"), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        // Non-matching keyword
        assertFalse(predicate.test(
                new VendorBuilder().withPhone("87878787").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.of("787"), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        // Contains the number, but does not start with it
        assertFalse(predicate.test(
                new VendorBuilder().withPhone("87878787").build()));

        // Keywords match passport number, email and other respective fields, but does not match phone number
        predicate = new VendorPredicate(Optional.of("123"), Optional.of("80958483"), Optional.of("Bill's Delivery"),
                Optional.of("bdelivery@email.com"), Optional.of(Set.of(new Tag("Halal"))),
                Optional.of("123 Clementi Rd, 123456"), Optional.of("Food"),
                Optional.of("12"), Optional.of("123 0900-1600"));
        assertFalse(predicate.test(
                new VendorBuilder().withVendorId("123").withPhone("80958412").withName("Bill's Delivery").withEmail(
                        "bdelivery@email.com").withTags("Halal").withAddress("123 Clementi Rd, 123456").withServiceName(
                        "Food").withCost("12").withOperatingHours("123 0900-1600").build()));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        VendorPredicate predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of("alice@email.com"), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withEmail("alice@email.com").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of("alice"), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withEmail("alice@email.com").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of("ce@em"), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withEmail("alice@email.com").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of("LiCe"), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withEmail("alice@email.com").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of("   lice   "), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withEmail("alice@email.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        VendorPredicate predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of("ace"), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        // Non-matching keyword
        assertFalse(predicate.test(
                new VendorBuilder().withEmail("alice@email.com").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of("emailalice"), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertFalse(predicate.test(
                new VendorBuilder().withEmail("alice@email.com").build()));


        // Keywords match passport number, name and other respective fields, but does not match email
        predicate = new VendorPredicate(Optional.of("123"), Optional.of("80958483"), Optional.of("Bill's Delivery"),
                Optional.of("bdelivery@email.com"), Optional.of(Set.of(new Tag("Halal"))),
                Optional.of("123 Clementi Rd, 123456"), Optional.of("Food"),
                Optional.of("12"), Optional.of("123 0900-1600"));
        assertFalse(predicate.test(
                new VendorBuilder().withVendorId("123").withPhone("80958483").withName("Bill's Delivery").withEmail(
                        "nexdelivery@email.com").withTags("Halal").withAddress(
                        "123 Clementi Rd, 123456").withServiceName(
                        "Food").withCost("12").withOperatingHours("123 0900-1600").build()));
    }

    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        VendorPredicate predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.of(Set.of(new Tag("VIP"))), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withTags("Vip").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.of(Set.of(new Tag("ViP"))), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withTags("Vip").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.of(Set.of(new Tag("   vip   "))), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withTags("Vip").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.of(Set.of(new Tag("vip"), new Tag("second"))), Optional.empty(),
                        Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withTags("Vip").build()));

        assertTrue(predicate.test(
                new VendorBuilder().withTags("Second").build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        VendorPredicate predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.of(Set.of(new Tag("qert"))), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertFalse(predicate.test(
                new VendorBuilder().withTags("Vip").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.of(Set.of(new Tag("ip"))), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertFalse(predicate.test(
                new VendorBuilder().withTags("Vip").build()));

        // Keywords match passport number, name and other respective fields, but does not match tags
        predicate = new VendorPredicate(Optional.of("123"), Optional.of("80958483"), Optional.of("Bill's Delivery"),
                Optional.of("bdelivery@email.com"), Optional.of(Set.of(new Tag("Halal"))),
                Optional.of("123 Clementi Rd, 123456"), Optional.of("Food"),
                Optional.of("12"), Optional.of("123 0900-1600"));
        assertFalse(predicate.test(
                new VendorBuilder().withVendorId("123").withPhone("80958483").withName("Bill's Delivery").withEmail(
                        "bdelivery@email.com").withTags("Non Halal").withAddress(
                        "123 Clementi Rd, 123456").withServiceName(
                        "Food").withCost("12").withOperatingHours("123 0900-1600").build()));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        VendorPredicate predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.of("123 Rd"), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withAddress("123 Rd").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.of("123 rd"), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withAddress("123 Rd").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.of("3 rd"), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withAddress("123 Rd").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.of("123 rD"), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withAddress("123 Rd").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.of("   123    "), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withAddress("123 Rd").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        VendorPredicate predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.of("13 rd"), Optional.empty(),
                        Optional.empty(), Optional.empty());

        // Non-matching keyword
        assertFalse(predicate.test(
                new VendorBuilder().withAddress("123 Rd").build()));

        predicate =
                new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.of("Rd 123"), Optional.empty(),
                        Optional.empty(), Optional.empty());

        assertFalse(predicate.test(
                new VendorBuilder().withAddress("123 Rd").build()));

        // Keywords match passport number, name and other respective fields, but does not match email
        predicate = new VendorPredicate(Optional.of("123"), Optional.of("80958483"), Optional.of("Bill's Delivery"),
                Optional.of("bdelivery@email.com"), Optional.of(Set.of(new Tag("Halal"))),
                Optional.of("123 Clementi Rd, 123456"), Optional.of("Food"),
                Optional.of("12"), Optional.of("123 0900-1600"));
        assertFalse(predicate.test(
                new VendorBuilder().withVendorId("123").withPhone("80958483").withName("Bill's Delivery").withEmail(
                        "bdelivery@email.com").withTags("Halal").withAddress("456 Clementi Rd, 123456").withServiceName(
                        "Food").withCost("12").withOperatingHours("123 0900-1600").build()));
    }

    @Test
    public void test_serviceNameContainsKeywords_returnsTrue() {
        VendorPredicate predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.of("Food"),
                Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withServiceName("Food").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.of("food"),
                Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withServiceName("Food").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.of("  food   "),
                Optional.empty(), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withServiceName("Food").build()));
    }

    @Test
    public void test_serviceNameDoesNotContainsKeywords_returnsFalse() {
        VendorPredicate predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.of("mass"),
                Optional.empty(), Optional.empty());

        // Non-matching keyword
        assertFalse(predicate.test(
                new VendorBuilder().withServiceName("Massage").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.of("age"),
                Optional.empty(), Optional.empty());

        assertFalse(predicate.test(
                new VendorBuilder().withServiceName("Massage").build()));

        // Keywords match name, email and other respective fields, but does not match service name
        predicate = new VendorPredicate(Optional.of("123"), Optional.of("80958483"), Optional.of("Bill's Delivery"),
                Optional.of("bdelivery@email.com"), Optional.of(Set.of(new Tag("Halal"))),
                Optional.of("123 Clementi Rd, 123456"), Optional.of("Food"),
                Optional.of("12"), Optional.of("123 0900-1600"));
        assertFalse(predicate.test(
                new VendorBuilder().withVendorId("123").withPhone("80958483").withName("Bill's Delivery").withEmail(
                        "bdelivery@email.com").withTags("Halal").withAddress("123 Clementi Rd, 123456").withServiceName(
                        "Massage").withCost("12").withOperatingHours("123 0900-1600").build()));
    }

    @Test
    public void test_costContainsKeywords_returnsTrue() {
        VendorPredicate predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.of("10"), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withCost("10").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.of("<10"), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withCost("9").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.of(">10"), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withCost("11").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.of("  <10   "), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withCost("9").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.of("  >10   "), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withCost("11").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.of("  10   "), Optional.empty());

        assertTrue(predicate.test(
                new VendorBuilder().withCost("10").build()));
    }

    @Test
    public void test_costDoesNotContainsKeywords_returnsFalse() {
        VendorPredicate predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.of("10"), Optional.empty());

        // Non-matching keyword
        assertFalse(predicate.test(
                new VendorBuilder().withCost("11").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.of(">10"), Optional.empty());

        assertFalse(predicate.test(
                new VendorBuilder().withCost("10").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.of("<10"), Optional.empty());

        assertFalse(predicate.test(
                new VendorBuilder().withCost("10").build()));

        // Keywords match name, email and other respective fields, but does not match cost
        predicate = new VendorPredicate(Optional.of("123"), Optional.of("80958483"), Optional.of("Bill's Delivery"),
                Optional.of("bdelivery@email.com"), Optional.of(Set.of(new Tag("Halal"))),
                Optional.of("123 Clementi Rd, 123456"), Optional.of("Food"),
                Optional.of("12"), Optional.of("123 0900-1600"));
        assertFalse(predicate.test(
                new VendorBuilder().withVendorId("123").withPhone("80958483").withName("Bill's Delivery").withEmail(
                        "bdelivery@email.com").withTags("Halal").withAddress("123 Clementi Rd, 123456").withServiceName(
                        "Food").withCost("20").withOperatingHours("123 0900-1600").build()));
    }

    @Test
    public void test_operatingHoursContainsKeywords_returnsTrue() {
        VendorPredicate predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of("123"));

        assertTrue(predicate.test(
                new VendorBuilder().withOperatingHours("123 0800-1600").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of("  123   "));

        assertTrue(predicate.test(
                new VendorBuilder().withOperatingHours("123 0800-1600").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of("  13   "));

        assertTrue(predicate.test(
                new VendorBuilder().withOperatingHours("123 0800-1600").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of("123 0800"));

        assertTrue(predicate.test(
                new VendorBuilder().withOperatingHours("123 0800-1600").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of("123 1600"));

        assertTrue(predicate.test(
                new VendorBuilder().withOperatingHours("123 0800-1600").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of("123 1000"));

        assertTrue(predicate.test(
                new VendorBuilder().withOperatingHours("123 0800-1600").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of("   123 0800   "));

        assertTrue(predicate.test(
                new VendorBuilder().withOperatingHours("123 0800-1600").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of("123 0800-1600"));

        assertTrue(predicate.test(
                new VendorBuilder().withOperatingHours("123 0800-1600").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of("123 1600-2200"));

        assertTrue(predicate.test(
                new VendorBuilder().withOperatingHours("123 0800-1600").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of("  13 0300-2200   "));

        assertTrue(predicate.test(
                new VendorBuilder().withOperatingHours("123 0800-1600").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of("  133311 0300-2200   "));

        assertTrue(predicate.test(
                new VendorBuilder().withOperatingHours("123 0800-1600").build()));
    }

    @Test
    public void test_operatingHoursDoesNotContainsKeywords_returnsFalse() {
        VendorPredicate predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of("123"));

        // Non-matching keyword
        assertFalse(predicate.test(
                new VendorBuilder().withOperatingHours("234 0800-1600").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of("23 0759"));

        assertFalse(predicate.test(
                new VendorBuilder().withOperatingHours("234 0800-1600").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of("23 1601"));

        assertFalse(predicate.test(
                new VendorBuilder().withOperatingHours("234 0800-1600").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of("23 0000-0700"));

        assertFalse(predicate.test(
                new VendorBuilder().withOperatingHours("234 0800-1600").build()));

        predicate = new VendorPredicate(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of("23 1601-2000"));

        assertFalse(predicate.test(
                new VendorBuilder().withOperatingHours("234 0800-1600").build()));

        // Keywords match name, email and other respective fields, but does not match operating hours
        predicate = new VendorPredicate(Optional.of("123"), Optional.of("80958483"), Optional.of("Bill's Delivery"),
                Optional.of("bdelivery@email.com"), Optional.of(Set.of(new Tag("Halal"))),
                Optional.of("123 Clementi Rd, 123456"), Optional.of("Food"),
                Optional.of("12"), Optional.of("123 0900-1600"));
        assertFalse(predicate.test(
                new VendorBuilder().withVendorId("123").withPhone("80958483").withName("Bill's Delivery").withEmail(
                        "bdelivery@email.com").withTags("Halal").withAddress("123 Clementi Rd, 123456").withServiceName(
                        "Food").withCost("12").withOperatingHours("345 0900-1600").build()));
    }

}
